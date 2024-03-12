package com.vemser.tests.carrinho;

import client.CarrinhoClient;
import client.ProdutoClient;
import data.factory.CarrinhoDataFactory;
import data.factory.ProdutoDataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.*;
import org.junit.jupiter.api.Test;
import specs.CarrinhoSpecs;

import java.util.ArrayList;

import static client.CarrinhoClient.carrinhoSpecs;
import static io.restassured.RestAssured.given;
import static model.enums.PermissaoTipoEnum.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarrinhoFuncionalTest {
    CarrinhoClient carrinhoClient = new CarrinhoClient();
    @Test
    public void testBuscarCarrinhoPorQueryComSucesso(){
         CarrinhoResponse carrinhoResponse =
          carrinhoClient.buscarCarrinhoQuery("_id","ml5139Qy1489tJIB")
            .then()
            .statusCode(200)
              .extract().as(CarrinhoResponse.class);
         assertEquals(1,carrinhoResponse.getQuantidade());
    }
    @Test
    public void testCadastrarCarrinhoComSucesso(){
        Carrinho carrinho = new Carrinho();
        carrinho.setProdutos(CarrinhoDataFactory.listaCarrinhoValido());
        carrinhoClient.cadastrarCarrinhoComSucesso(carrinho)
            .then()
            .log().all()
            ;
    }
    @Test
    public void testBuscarCarrinhoPorIDComSucesso(){
            carrinhoClient.buscarCarrinhoID("qbMqntef4iTOwWfg")
                .then()
                .statusCode(200)
                .body("precoTotal",equalTo(6180))
                ;
    }
}
