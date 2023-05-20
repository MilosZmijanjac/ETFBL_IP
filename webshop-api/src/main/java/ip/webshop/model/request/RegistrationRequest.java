package ip.webshop.model.request;


import lombok.Data;


@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String avatarPath;
    private String address;
    private String city;
    private String country;
}
