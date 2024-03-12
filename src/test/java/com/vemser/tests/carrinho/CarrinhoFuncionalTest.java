package com.vemser.tests.carrinho;

import client.CarrinhoClient;
import data.factory.CarrinhoDataFactory;
import model.Carrinho;
import model.CarrinhoResponse;
import org.junit.jupiter.api.Test;

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
    @Test
    public void testConcluirCompraComSucesso(){
        Carrinho carrinho = new Carrinho();
        carrinho.setProdutos(CarrinhoDataFactory.listaCarrinhoValido());
        carrinhoClient.concluirCompra(carrinho)
            .then()
            .statusCode(200)
            .body("message",equalTo("Registro excluído com sucesso"))
        ;
    }
    @Test
    public void testCancelarCompraComSucesso(){
        Carrinho carrinho = new Carrinho();
        carrinho.setProdutos(CarrinhoDataFactory.listaCarrinhoValido());
        carrinhoClient.cancelarCompra(carrinho)
            .then()
            .statusCode(200)
            .body("message",equalTo("Registro excluído com sucesso. Estoque dos produtos reabastecido"))
        ;
    }
}
