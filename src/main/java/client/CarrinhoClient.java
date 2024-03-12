package client;

import io.restassured.response.Response;
import specs.CarrinhoSpecs;
import specs.ProdutoSpecs;

import static io.restassured.RestAssured.given;

public class CarrinhoClient {
    private static final String CARRINHO = "/carrinhos";
    public static CarrinhoSpecs carrinhoSpecs = new CarrinhoSpecs();
    public Response buscarCarrinho(String id){
        return
            given()
                .queryParam("_id",id)
                .spec(carrinhoSpecs.carrinhoReqSpec())
            .when()
                .get(CARRINHO)
            ;
    }

}
