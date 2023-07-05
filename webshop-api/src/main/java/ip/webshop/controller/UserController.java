package ip.webshop.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import ip.webshop.model.entity.Product;
import ip.webshop.model.entity.User;
import ip.webshop.model.enumeration.LogType;
import ip.webshop.model.request.PinRequest;
import ip.webshop.model.request.RegistrationRequest;
import ip.webshop.model.response.PinResponse;
import ip.webshop.model.response.UserInfoResponse;
import ip.webshop.service.LogService;
import ip.webshop.service.PinService;
import ip.webshop.service.ProductService;
import ip.webshop.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PinService pinService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LogService logService;

    @GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
	}

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        User user=userService.getUserById(id);
        if(user==null)
        return ResponseEntity.notFound().build();
        else
        return ResponseEntity.ok(user);
    }
    @GetMapping("/info/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        System.out.println(username);
        User user=userService.getUserByUsername(username);
        UserInfoResponse response=new UserInfoResponse();
        if(user==null){
        logService.writeLog(LogType.WARNING, "/api/users/info/" + username, "Request user info failed", Instant.now());
        return ResponseEntity.notFound().build();
        }
        else{
            response.setId(user.getId());
            response.setAddress(user.getAddress());
            response.setAvatarPath(user.getAvatarPath());
            response.setCity(user.getCity());
            response.setCountry(user.getCountry());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setPhone(user.getPhone());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            logService.writeLog(LogType.INFO, "/api/users/info/" + username, "Request user info", Instant.now());
            return ResponseEntity.ok(response);
        }
        
    }
    @GetMapping("/seller/{id}")
    public ResponseEntity<?> getSellerById(@PathVariable Long id){
        System.out.println(id);
        Product prod=productService.getProductById(id);
        User user=userService.getUserById(prod.getUserId());
        UserInfoResponse response=new UserInfoResponse();
        if(user==null){
            logService.writeLog(LogType.WARNING, "/api/users/seller/" + id, "Request seller info failed", Instant.now());
            return ResponseEntity.notFound().build();
        }        
        else{
            response.setId(user.getId());
            response.setAddress(user.getAddress());
            response.setAvatarPath(user.getAvatarPath());
            response.setCity(user.getCity());
            response.setCountry(user.getCountry());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setPhone(user.getPhone());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            logService.writeLog(LogType.INFO, "/api/users/seller/" + id, "Request seller info", Instant.now());
            return ResponseEntity.ok(response);
        }
        
    }
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegistrationRequest request){
      if(userService.usernameExists(request.getUsername())){
        logService.writeLog(LogType.WARNING, "/api/users/register/" , "Username already exists: "+request.getUsername(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username already exists");
      }
       
       request.setPassword(passwordEncoder.encode(request.getPassword()));
       logService.writeLog(LogType.INFO, "/api/users/register/" , "User created: "+request.getUsername(), Instant.now());
       return ResponseEntity.ok(userService.addPendingUser(request));
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody RegistrationRequest request){
      
      if(!request.getPassword().equals(""))
       request.setPassword(passwordEncoder.encode(request.getPassword()));
       
       User user=userService.updateUser(request);
       UserInfoResponse response=new UserInfoResponse();
        if(user==null){
            logService.writeLog(LogType.WARNING, "/api/users/update/" , "Update failed, user not found: "+request.getUsername(), Instant.now());
            return ResponseEntity.notFound().build();
        }        
        else{
            response.setAddress(user.getAddress());
            response.setAvatarPath(user.getAvatarPath());
            response.setCity(user.getCity());
            response.setCountry(user.getCountry());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setPhone(user.getPhone());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            logService.writeLog(LogType.INFO, "/api/users/update/" , "Update succees: "+request.getUsername(), Instant.now());
       return ResponseEntity.ok(response);
    }
    }
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUserByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getType().name())
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @PostMapping(value="/pin")
    public ResponseEntity<?> validatePin(@RequestBody PinRequest request) {
        User user=userService.getUserByUsername(request.getUsername());
        if (pinService.validatePin(user.getId(), Integer.parseInt(request.getPinCode()))) {
            userService.activateUser(user.getId());
            PinResponse response=new PinResponse();
            response.setActivated(true);
            return ResponseEntity.ok(response);
        } else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Incorect pin code");
    }


}
