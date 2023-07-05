package ip.webshop.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ip.webshop.model.entity.Comment;
import ip.webshop.model.enumeration.LogType;
import ip.webshop.model.request.CommentRequest;
import ip.webshop.model.response.CommentResponse;
import ip.webshop.service.CommentService;
import ip.webshop.service.LogService;
import ip.webshop.service.UserService;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentCotroller {
    @Autowired
    CommentService commentService ;
    @Autowired
    UserService userService ;
    @Autowired
    private LogService logService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getCommentsForProduct(@PathVariable Long productId){
    CommentResponse[] comments=commentService.getAllCommentsForProduct(productId).stream()
                                    .map(comment->new CommentResponse(
                                         comment.getId().toString(),
                                         comment.getBody(),
                                         comment.getUsername(),
                                         comment.getUserId()==null?null: comment.getUserId().toString(),
                                         comment.getParentId()==null?null: comment.getParentId().toString(),
                                         comment.getCreatedAt().toString()
                                    )).toArray(size->new CommentResponse[size]);
      logService.writeLog(LogType.INFO,"/api/comment/"+productId , "Request all comments for product", Instant.now());
      return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<?>createComment(@RequestBody CommentRequest request ){
      Comment comment=new Comment();
      comment.setBody(request.getBody());
      comment.setParentId( request.getParentId()==null?null:Long.parseLong(request.getParentId()));
      comment.setCreatedAt(Instant.now());
      comment.setProductId(Long.parseLong(request.getProductId()));
      comment.setUsername(request.getUsername());
      comment.setUserId(this.userService.getUserByUsername(request.getUsername()).getId());
      comment=this.commentService.addComment(comment);
      logService.writeLog(LogType.INFO,"/api/comment" , "Creating new comment", Instant.now());
         return ResponseEntity.ok(new CommentResponse(
            comment.getId().toString(),
            comment.getBody(),
            comment.getUsername(),
            comment.getUserId()==null?null: comment.getUserId().toString(),
            comment.getParentId()==null?null: comment.getParentId().toString(),
            comment.getCreatedAt().toString()
       ));
    }
}
