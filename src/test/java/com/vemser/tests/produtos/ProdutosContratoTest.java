package com.vemser.tests.produtos;

import client.ProdutoClient;
import data.factory.ProdutoDataFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import model.Produto;
import model.ProdutoResponse;
import model.enums.PermissaoTipoEnum;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static model.enums.PermissaoTipoEnum.ADMIN;


public class ProdutosContratoTest {

    ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testValidarContratoCadastrarProduto(){
        Produto produtoCadastrar = ProdutoDataFactory.produtoValido();

        produtoClient.cadastrarProduto(produtoCadastrar, ADMIN)
        .then()
            .statusCode(HttpStatus.SC_CREATED)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/produtosSchemas/cadastra_produto.json"))
        ;
    }
    @Test
    public void testValidarContratoBuscarProdutoPorID(){
        Produto produto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(produto,ADMIN).as(ProdutoResponse.class);

        produtoClient.atualizarProduto(produtoCadastrado.get_id(), ProdutoDataFactory.produtoValido(),ADMIN)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/produtosSchemas/busca_produto_id.json"))
        ;
    }
}
