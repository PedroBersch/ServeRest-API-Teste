package client;

import io.restassured.response.Response;
import model.Produto;
import model.enums.PermissaoTipoEnum;
import specs.ProdutoSpecs;

import static io.restassured.RestAssured.given;

public class ProdutoClient {
    private static final String PRODUTOS = "/produtos";
    private static final String PRODUTOS_ID= "/produtos/{_id}";
    public static ProdutoSpecs produtoSpecs = new ProdutoSpecs();

    public ProdutoClient() {
    }

    public Response cadastrarProduto(Produto produto, PermissaoTipoEnum permissao){
        return
            given()
                .spec(produtoSpecs.produtoReqSpec(permissao))
                .body(produto)
                .when()
                .post(PRODUTOS)
            ;
    }

    public Response atualizarProduto(String id, Produto produto, PermissaoTipoEnum permissao){
        return
            given()
                    .spec(produtoSpecs.produtoReqSpec(permissao))
                    .pathParams("_id", id)
                    .body(produto)
                .when()
                    .put(PRODUTOS_ID)
            ;
    }
    public Response buscarProduto(String id){
        return
            given()
                    .spec(produtoSpecs.produtoReqSpec())
                    .pathParams("_id", id)
                .when()
                    .get(PRODUTOS_ID)
            ;
    }
    public Response excluirProduto(String id, PermissaoTipoEnum permissao){
        return
            given()
                    .spec(produtoSpecs.produtoReqSpec(permissao))
                    .pathParams("_id", id)
                .when()
                    .delete(PRODUTOS_ID)
            ;
    }
    public Response listarProdutos(String key,String value){
       return
        given()
            .queryParam(key,value)
            .spec(produtoSpecs.produtoReqSpec())
            .when()
                .get(PRODUTOS)
           ;
    }

}
