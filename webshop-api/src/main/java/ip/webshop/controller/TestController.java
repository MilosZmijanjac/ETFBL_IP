package ip.webshop.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ip.webshop.model.entity.Attribute;
import ip.webshop.model.entity.Category;
import ip.webshop.model.entity.Log;
import ip.webshop.model.entity.User;
import ip.webshop.model.enumeration.UserType;
import ip.webshop.repository.CategoryRepository;
import ip.webshop.repository.UserRepository;
import ip.webshop.service.LogService;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @Autowired
    LogService logservice;
    @Autowired
    UserRepository catRep;
    @Autowired
    PasswordEncoder encoder;
    @GetMapping("/log")
	public ResponseEntity<Log> userAccess() {
		//logservice.writeLog(Long.valueOf(0), "marko", LogType.INFO, "null", "null", Instant.now());
        User u=new User();
        u.setUsername("Marko");
        u.setPassword(encoder.encode("pass123"));
        u.setType(UserType.WEBSHOP);
        catRep.save(u);
       // cb=catRep.getReferenceById(Long.valueOf(1));
       // System.out.println(cb.getSpecialAttributes().get(0).getUnit());
        return ResponseEntity.ok(null);
	}
}
