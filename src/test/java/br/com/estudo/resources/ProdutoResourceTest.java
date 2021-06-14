package br.com.estudo.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.net.URL;

import org.junit.jupiter.api.Test;

import br.com.estudo.database.DatabaseLifecycleTest;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(ProdutoResource.class)
@QuarkusTestResource(DatabaseLifecycleTest.class)
public class ProdutoResourceTest {
    
    @TestHTTPResource("/produtos")
    URL produtodEndpoint;

    @Test
    public void testProdutosEndpoint() {
        given()
          .when().get(produtodEndpoint)
          .then()
             .statusCode(200)
             .body("size()", is(0));
    }

}
