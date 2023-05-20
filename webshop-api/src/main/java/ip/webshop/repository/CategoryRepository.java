package ip.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    
}
