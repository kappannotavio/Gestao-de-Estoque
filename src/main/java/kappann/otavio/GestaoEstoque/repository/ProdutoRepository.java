package kappann.otavio.GestaoEstoque.repository;

import kappann.otavio.GestaoEstoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
