package kappann.otavio.GestaoEstoque.controller;

import java.util.List;
import kappann.otavio.GestaoEstoque.model.Produto;
import kappann.otavio.GestaoEstoque.repository.ProdutoRepository;
import kappann.otavio.GestaoEstoque.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private LogService logService;
    
    //Buscar Todos os produtos
    @GetMapping
    public List<Produto> findAll() {return produtoRepository.findAll();}
    
    //Colocar produto no banco
    @PostMapping
    public Produto create(@RequestBody Produto produto) {
        
        Produto novoProduto = produtoRepository.save(produto);
        logService.criarLog(novoProduto, "Adicionado");
        return novoProduto;
    }
    
    //Editar um Produto do banco
    @PutMapping("/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
        Produto produto = produtoRepository.getById(id);
        
        produto.setNome(produtoAtualizado.getNome());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setFornecedor(produtoAtualizado.getFornecedor());
        produto.setValor(produtoAtualizado.getValor());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        
        logService.criarLog(produto, "Editado");

        return produtoRepository.save(produto);
    }

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {
 
    Produto produtoDeletar = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    logService.criarLog(produtoDeletar, "Deletado");
    produtoRepository.delete(produtoDeletar);
    
}

}
