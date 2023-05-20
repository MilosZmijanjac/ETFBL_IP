package ip.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.Comment;
import ip.webshop.model.enumeration.CommentStatus;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByProductId(Long product_id);
    List<Comment> findByUserIdAndStatus(Long user_id,CommentStatus status);
    List<Comment> findByProductIdAndStatus(Long product_id,CommentStatus status);
}
