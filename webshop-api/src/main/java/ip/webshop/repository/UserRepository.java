package ip.webshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.User;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);
    Boolean existsByUsername(String username);
}
