package ip.webshop.service;

import ip.webshop.model.entity.User;
import ip.webshop.model.request.RegistrationRequest;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User addPendingUser(RegistrationRequest request);
    boolean usernameExists(String username);
    void activateUser(Long id);
}
