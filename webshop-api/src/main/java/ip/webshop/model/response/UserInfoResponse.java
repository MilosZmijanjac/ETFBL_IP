package ip.webshop.model.response;

import lombok.Data;

@Data
public class UserInfoResponse {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String avatarPath;
    private String address;
    private String city;
    private String country;
}
