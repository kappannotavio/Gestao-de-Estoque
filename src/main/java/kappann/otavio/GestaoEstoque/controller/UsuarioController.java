package kappann.otavio.GestaoEstoque.controller;

import kappann.otavio.GestaoEstoque.model.Usuario;
import kappann.otavio.GestaoEstoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Usuario usuarioLogin) {
        return usuarioRepository.findByUsuario(usuarioLogin.getUsuario())
                .map(usuario -> {
                    if (usuario.getSenha().equals(usuarioLogin.getSenha())) {
                        return ResponseEntity.ok("Login bem-sucedido!");
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body("Senha incorreta!");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuário não encontrado!"));
    }
}
