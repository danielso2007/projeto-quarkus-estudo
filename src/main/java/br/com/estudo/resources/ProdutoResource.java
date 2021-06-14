package br.com.estudo.resources;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.estudo.dto.CadastrarProdutoDTO;
import br.com.estudo.entities.Produto;
import br.com.estudo.repositories.ProdutoRepository;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    
    @Inject
    private ProdutoRepository repository;

    @GET
    public List<Produto> buscarTodosProdutos() {
        return repository.listAll();
    }

    @GET
    @Path("{id}")
    public Produto buscarProduto(@PathParam("id") Long id) {
        Optional<Produto> produtoOp = repository.findByIdOptional(id);
        if (produtoOp.isPresent()) {
            return produtoOp.get();
        }
        throw new NotFoundException();
    }

    @POST
    @Transactional
    public void cadastrarProduto(CadastrarProdutoDTO dto) {
        Produto p = Produto.builder().nome(dto.getNome()).valor(dto.getValor()).build();
        repository.persist(p);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizarProduto(@PathParam("id") Long id, CadastrarProdutoDTO dto) {
        Optional<Produto> produtoOp = repository.findByIdOptional(id);

        if (produtoOp.isPresent()) {
            Produto p = produtoOp.get();
            p.setNome(dto.getNome());
            p.setValor(dto.getValor());
            repository.persist(p);
        } else {
            throw new NotFoundException(String.format("Produto com ID %d não encontrado.", id));
        }
        
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarProduto(@PathParam("id") Long id) {
        Optional<Produto> produtoOp = repository.findByIdOptional(id);
        produtoOp.ifPresentOrElse(repository::delete, () -> {throw new NotFoundException();});
    }
}