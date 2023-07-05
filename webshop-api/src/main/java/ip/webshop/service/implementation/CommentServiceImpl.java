package ip.webshop.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ip.webshop.model.entity.Comment;
import ip.webshop.model.entity.Product;
import ip.webshop.model.enumeration.CommentStatus;
import ip.webshop.repository.CommentRepository;
import ip.webshop.repository.ProductRepository;
import ip.webshop.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Comment> getAllCommentsForProduct(Long productId) {

        return commentRepository.findByProductId(productId);
    }

    @Override
    public void readAllForProduct(Long productId) {
        List<Comment> comments = commentRepository.findByProductId(productId);
        for (Comment comment : comments) {
            comment.setStatus(CommentStatus.READ);
        }
        commentRepository.saveAll(comments);
    }

    @Override
    public List<Comment> getAllUnreadForUser(Long userId) {
        return commentRepository.findByUserIdAndStatus(userId, CommentStatus.UNREAD);
    }

    @Override
    public Comment addComment(Comment comment) {
       return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllUnreadCommentsForUserProducts(Long userId) {
        List<Comment> comments=new ArrayList<Comment>();
        List<Product> products=productRepository.findByUserId(userId);
        for(Product product:products){
            comments.addAll(commentRepository.findByProductIdAndStatus(product.getId(), CommentStatus.UNREAD));
        }
        return comments;
    }

}
