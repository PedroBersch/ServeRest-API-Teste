package client;

import io.restassured.response.Response;
import model.Carrinho;
import specs.CarrinhoSpecs;

import static io.restassured.RestAssured.given;

public class CarrinhoClient {
    private static final String CARRINHO = "/carrinhos";
    private static final String CARRINHO_ID = "/carrinhos/{_id}";
    private static final String CONCLUIR_COMPRA = "/carrinhos/concluir-compra";
    private static final String CANCELAR_COMPRA = "/carrinhos/cancelar-compra";
    public static CarrinhoSpecs carrinhoSpecs = new CarrinhoSpecs();
    public Response buscarCarrinhoQuery(String key, String id){
        return
            given()
                .queryParam(key,id)
                .spec(carrinhoSpecs.carrinhoReqSpec())
            .when()
                .get(CARRINHO)
            ;
    }
    public Response cadastrarCarrinhoComSucesso(Carrinho listaCarrinho){
        return
            given()
                .spec(carrinhoSpecs.carrinhoReqSpec())
                .body(listaCarrinho)
            .when()
                .post(CARRINHO);
    }
    public Response buscarCarrinhoID(String id){
        return
            given()
                .pathParams("_id",id)
                .spec(carrinhoSpecs.carrinhoReqSpec())
            .when()
                .get(CARRINHO_ID)
            ;
    }
    public Response concluirCompra(Carrinho listaCarrinho){
         String token =
             cadastrarCarrinhoComSucesso(listaCarrinho)
                 .then()
                .extract().response().jsonPath().getString("authorization");
         return
             given()
                 .spec(carrinhoSpecs.carrinhoReqSpec())
                 .header("authorization", "Bearer " + token)
             .when()
                 .delete(CONCLUIR_COMPRA);
    }
    public Response cancelarCompra(Carrinho listaCarrinho){
        String token =
            cadastrarCarrinhoComSucesso(listaCarrinho)
                .then()
                .extract().response().jsonPath().getString("authorization");
        return
            given()
                .spec(carrinhoSpecs.carrinhoReqSpec())
                .header("authorization", "Bearer " + token)
                .when()
                .delete(CANCELAR_COMPRA);
    }
}
