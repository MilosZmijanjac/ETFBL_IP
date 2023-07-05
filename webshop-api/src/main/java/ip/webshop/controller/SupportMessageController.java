package ip.webshop.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ip.webshop.model.enumeration.LogType;
import ip.webshop.model.request.SupportMessageRequest;
import ip.webshop.service.LogService;
import ip.webshop.service.SupportMessageService;

@RestController
@RequestMapping("/api/support")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SupportMessageController {
    @Autowired
    SupportMessageService supportMessageService;
    @Autowired
    LogService logService;
    @PostMapping
    public ResponseEntity<?>addMessage(@RequestBody SupportMessageRequest message){
      supportMessageService.addMessage(message);
      logService.writeLog(LogType.INFO, "/api/support/" , "Request support message send ", Instant.now());
      return ResponseEntity.ok(message);
    }
}
