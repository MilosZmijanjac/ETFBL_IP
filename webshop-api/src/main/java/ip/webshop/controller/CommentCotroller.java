package ip.webshop.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentCotroller {
    
}
