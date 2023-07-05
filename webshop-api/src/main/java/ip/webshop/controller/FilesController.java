package ip.webshop.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import ip.webshop.model.entity.FileInfo;
import ip.webshop.model.enumeration.LogType;
import ip.webshop.model.response.ResponseMessage;
import ip.webshop.service.FilesStorageService;
import ip.webshop.service.LogService;


@RestController
@RequestMapping("/api/files")
@CrossOrigin("*")
public class FilesController {

  @Autowired
  FilesStorageService storageService;
  @Autowired
  LogService logService;

  @PostMapping("/upload/{username}")
  public ResponseEntity<ResponseMessage> uploadProfilePic(@RequestParam("files") MultipartFile[] files,@PathVariable String username) {
    String message = "";
    try {
      List<String> fileNames = new ArrayList<>();

      Arrays.asList(files).stream().forEach(file -> {
        storageService.saveProfilePic(file,username);
        fileNames.add(file.getOriginalFilename());
      });

      message = "Uploaded the files successfully: " + fileNames;
      logService.writeLog(LogType.INFO,"/api/files/upload/"+username , "Uploading file "+fileNames, Instant.now());
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Fail to upload files!";
      logService.writeLog(LogType.ERROR,"/api/files/upload/"+username , "uploading file failed", Instant.now());
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }
  @PostMapping("/upload/{username}/{id}")
  public ResponseEntity<ResponseMessage> uploadProductPic(@RequestParam("files") MultipartFile[] files,@PathVariable String username,@PathVariable Long id) {
    String message = "";
    try {
      List<String> fileNames = new ArrayList<>();

      Arrays.asList(files).stream().forEach(file -> {
        storageService.saveProductPic(file,username,id);
        fileNames.add(file.getOriginalFilename());
      });

      message = "Uploaded the files successfully: " + fileNames;
      logService.writeLog(LogType.INFO,"/api/files/upload/"+username , "uploading file "+fileNames, Instant.now());
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Fail to upload files!";
      logService.writeLog(LogType.ERROR,"/api/files/upload/"+username , "uploading file failed", Instant.now());
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}