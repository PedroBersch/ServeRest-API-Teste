package com.vemser.tests.carrinho;

import client.CarrinhoClient;
import model.CarrinhoResponse;
import org.junit.jupiter.api.Test;
import specs.CarrinhoSpecs;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarrinhoFuncionalTest {
    CarrinhoClient carrinhoClient = new CarrinhoClient();
    @Test
    public void testBuscarCarrinhoPorIDComSUcesso(){
         CarrinhoResponse carrinhoResponse =
          carrinhoClient.buscarCarrinho("ml5139Qy1489tJIB")
            .then()
            .statusCode(200)
              .extract().as(CarrinhoResponse.class);
         assertEquals(1,carrinhoResponse.getQuantidade());
    }
}
