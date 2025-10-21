package kappann.otavio.GestaoEstoque.repository;

import java.util.Optional;
import kappann.otavio.GestaoEstoque.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
}
