package kappann.otavio.GestaoEstoque.repository;

import kappann.otavio.GestaoEstoque.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long>{
    
}
