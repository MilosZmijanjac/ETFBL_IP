package ip.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.Pin;

public interface PinRepository extends JpaRepository<Pin,Long> {
    
}
