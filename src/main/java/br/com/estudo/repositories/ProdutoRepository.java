package br.com.estudo.repositories;

import javax.enterprise.context.ApplicationScoped;

import br.com.estudo.entities.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepositoryBase<Produto, Long> {
    
}
