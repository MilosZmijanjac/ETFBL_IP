package ip.webshop.model.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ip.webshop.model.enumeration.CommentStatus;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private Instant createdAt;
    private String body;
    private Long userId;
    private String username;
    @Enumerated(EnumType.STRING)
    private CommentStatus status;
    private Long productId;
}
