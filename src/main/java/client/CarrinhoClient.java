package client;

import data.factory.ProdutoDataFactory;
import io.restassured.response.Response;
import model.Carrinho;
import model.CarrinhoProduto;
import model.Produto;
import model.ProdutoResponse;
import model.enums.PermissaoTipoEnum;
import specs.CarrinhoSpecs;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static model.enums.PermissaoTipoEnum.ADMIN;

public class CarrinhoClient {
    private static final String CARRINHO = "/carrinhos";
    private static final String CARRINHO_ID = "/carrinhos/{_id}";
    public static CarrinhoSpecs carrinhoSpecs = new CarrinhoSpecs();
    public ProdutoClient produtoClient = new ProdutoClient();
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
}
