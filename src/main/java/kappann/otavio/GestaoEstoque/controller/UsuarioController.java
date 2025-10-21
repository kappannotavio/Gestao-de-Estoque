package kappann.otavio.GestaoEstoque.controller;

import kappann.otavio.GestaoEstoque.model.Usuario;
import kappann.otavio.GestaoEstoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    //Buscar usuario
    @PostMapping
    public String login(@RequestBody Usuario usuarioLogin) {
        return usuarioRepository.findByUsuario(usuarioLogin.getUsuario())
                .map(usuario -> {
                    if (usuario.getSenha().equals(usuarioLogin.getSenha())) {
                        return "Login bem-sucedido!";
                    } else {
                        return "Senha incorreta!";
                    }
                })
                .orElse("Usuário não encontrado!");
    }
    
}
