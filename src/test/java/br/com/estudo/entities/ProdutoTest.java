package br.com.estudo.entities;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.estudo.database.DatabaseLifecycleTest;
import br.com.estudo.repositories.ProdutoRepository;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(DatabaseLifecycleTest.class)
public class ProdutoTest {
    
    @Inject
    private ProdutoRepository repository;

    @Test
    @Transactional
    public void tets_novo_produto() {
        Produto p = Produto.builder().nome("Produto test").valor(new BigDecimal(121L)).build();
        repository.persist(p);
        Assert.assertNotNull(p.getId());
    }

    @Test
    public void test_repositorio_count_produtos_sucesso() {
        Assert.assertEquals(0, repository.count());
    }

}
