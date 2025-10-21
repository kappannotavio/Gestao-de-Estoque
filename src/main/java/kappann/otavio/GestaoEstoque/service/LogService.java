package kappann.otavio.GestaoEstoque.service;

import java.time.LocalDateTime;
import kappann.otavio.GestaoEstoque.model.Log;
import kappann.otavio.GestaoEstoque.model.Produto;
import kappann.otavio.GestaoEstoque.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    
    @Autowired
    private LogRepository logRepository;
    
    public Log criarLog(Produto produto, String acao){
        
        Log log = new Log();
        log.setNomeProduto(produto.getNome());                
        log.setAcao(acao);              
        log.setDataHora(LocalDateTime.now());
        
        return logRepository.save(log);
    }
    
}
