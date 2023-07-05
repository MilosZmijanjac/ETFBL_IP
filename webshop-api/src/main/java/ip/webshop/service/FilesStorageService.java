package ip.webshop.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();

    public void saveProfilePic(MultipartFile file, String username);

    public void saveProductPic(MultipartFile file, String username, Long id);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
