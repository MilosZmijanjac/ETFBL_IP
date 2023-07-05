package ip.webshop.model.request;

import lombok.Data;

@Data
public class SupportMessageRequest {
    private String text;
    private String username;
    private String userMail;
}
