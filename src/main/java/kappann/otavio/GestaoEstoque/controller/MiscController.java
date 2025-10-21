package kappann.otavio.GestaoEstoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MiscController {
    
    
    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }
    
    @GetMapping("/index")
    public String paginaInicial() {
        return "index";
    }
    
    @GetMapping("/telaLogs")
    public String paginaLogs() {
        return "logs";
    }
    
    
}
