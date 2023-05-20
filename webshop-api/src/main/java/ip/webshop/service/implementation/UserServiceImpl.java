package ip.webshop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import ip.webshop.model.entity.User;
import ip.webshop.model.enumeration.UserStatus;
import ip.webshop.model.enumeration.UserType;
import ip.webshop.model.request.RegistrationRequest;
import ip.webshop.repository.UserRepository;
import ip.webshop.service.PinService;
import ip.webshop.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PinService pinService;
    // private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            return null;
        else
            return user.get();
    }

    @Override
    public User addPendingUser(RegistrationRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        // user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPassword((request.getPassword()));
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAvatarPath(request.getAvatarPath());
        user.setCreated(Instant.now());
        user.setStatus(UserStatus.PENDING);
        user.setType(UserType.WEBSHOP);
        return userRepository.save(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            if (user.getStatus() == UserStatus.BLOCKED) {
                throw new UsernameNotFoundException("User blocked!");
            }

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (user.getStatus() == UserStatus.PENDING) {
                pinService.sendPin(pinService.createPin(user.getId()), user.getEmail());
                authorities.add(new SimpleGrantedAuthority(user.getType().name() + "_" + user.getStatus().name()));
            } else {
                authorities.add(new SimpleGrantedAuthority(user.getType().name()));
            }

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    authorities);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public void activateUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }
}
