package ip.webshop.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
    private String id;
    private String body;
    private String username;
    private String userId;
    private String parentId;
    private String createdAt;
}
