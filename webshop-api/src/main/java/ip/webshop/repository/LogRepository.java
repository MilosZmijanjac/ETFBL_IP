package ip.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ip.webshop.model.entity.Log;
public interface LogRepository extends JpaRepository<Log,Long>{
    List<Log> findByOrderByTimestampDesc();
}
