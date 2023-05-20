package ip.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.SupportMessage;

public interface SupportMessageRepository extends JpaRepository<SupportMessage,Long>{
    
}
