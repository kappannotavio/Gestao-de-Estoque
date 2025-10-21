package kappann.otavio.GestaoEstoque.controller;

import java.util.List;
import kappann.otavio.GestaoEstoque.model.Log;
import kappann.otavio.GestaoEstoque.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LogController {
    
    @Autowired
    private LogRepository logRepository;
    
    
    //Buscar Todos os Logs
    @GetMapping
    public List<Log> findALl() {return logRepository.findAll();}
    
    
}
