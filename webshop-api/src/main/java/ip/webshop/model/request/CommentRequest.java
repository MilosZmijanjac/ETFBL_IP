package ip.webshop.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequest {
    private String body;
    private String username;
    private String parentId;
    private String productId;
}
