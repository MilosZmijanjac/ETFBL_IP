package ip.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ip.webshop.model.entity.Pin;
import ip.webshop.service.PinService;
import ip.webshop.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/pin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PinController {

    @Autowired
    PinService pinService;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> validatePin(@RequestBody Pin pin) {
        if (pinService.validatePin(pin.getId(), pin.getPinCode())) {
            userService.activateUser(pin.getId());
            return ResponseEntity.ok("Success");
        } else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Incorect pin code");
    }
    
}
