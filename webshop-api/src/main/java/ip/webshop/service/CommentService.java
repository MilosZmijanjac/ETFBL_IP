package ip.webshop.service;

import java.util.List;

import ip.webshop.model.entity.Comment;

public interface CommentService {
    List<Comment> getAllCommentsForProduct(Long productId);
    void readAllForProduct(Long productId);
    List<Comment> getAllUnreadForUser(Long userId);
    void addComment(Comment comment);
    List<Comment> getAllUnreadCommentsForUserProducts(Long userId);
    
}
