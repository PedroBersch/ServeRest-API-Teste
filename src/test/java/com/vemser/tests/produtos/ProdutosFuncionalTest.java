package com.vemser.tests.produtos;

import client.ProdutoClient;
import data.factory.ProdutoDataFactory;
import model.Produto;
import model.ProdutoResponse;
import model.enums.PermissaoTipoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static model.enums.PermissaoTipoEnum.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutosFuncionalTest {

    final static String ID_PADRAO = "BeeJh5lz3k6kSIzA";
    static final String INEXISTENTE_ID = "IDINEXISTENTE";
    static final String NOME_REPETIDO = "Produto Test Repetido";

    ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testCadastrarProdutoComSucesso() {
        Produto produtoCadastrar = ProdutoDataFactory.produtoValido();
        ProdutoResponse produtoResult =
            produtoClient.cadastrarProduto(produtoCadastrar, ADMIN)
                .then()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);
        assertEquals("Cadastro realizado com sucesso", produtoResult.getMessage());
    }

    @Test
    public void testEditarProdutoComSucesso() {
        Produto produto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(produto, ADMIN).as(ProdutoResponse.class);

        ProdutoResponse produtoResponse =
            produtoClient.atualizarProduto(produtoCadastrado.get_id(), ProdutoDataFactory.produtoValido(), ADMIN)
                .then()
                .statusCode(200)
                .extract().as(ProdutoResponse.class);
        assertEquals("Registro alterado com sucesso", produtoResponse.getMessage());
    }

    @Test
    public void testExcluirProdutoCadastradoComSucesso() {
        Produto produto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoExcluir = produtoClient.cadastrarProduto(produto, ADMIN).as(ProdutoResponse.class);

        ProdutoResponse produtoResponse =
            produtoClient.excluirProduto(produtoExcluir.get_id(), ADMIN)
                .then()
                .statusCode(200)
                .extract().as(ProdutoResponse.class);
        assertEquals("Registro excluído com sucesso", produtoResponse.getMessage());
    }

    @Test
    public void testBuscarProdutoPorIDComSucesso() {
        Produto produto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoBuscar = produtoClient.cadastrarProduto(produto, ADMIN).as(ProdutoResponse.class);

        ProdutoResponse produtoBuscado = produtoClient.buscarProduto(produtoBuscar.get_id())
            .then()
            .statusCode(200)
            .extract().as(ProdutoResponse.class);
        Assertions.assertNotNull(produtoBuscado.get_id());
    }

    //Caminho Infeliz - Cadastro

    @Test
    public void testCadastrarProdutoComTokenInvalido() {
        Produto produtoCadastrar = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoResult =
            produtoClient.cadastrarProduto(produtoCadastrar, INVALIDO)
                .then()
                .statusCode(401)
                .extract().as(ProdutoResponse.class);
        assertEquals("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais", produtoResult.getMessage());
    }

    @Test
    public void testCadastrarProdutoComNomeRepetido() {
        Produto produtoCadastrar = ProdutoDataFactory.produtoValido();
        produtoCadastrar.setNome(NOME_REPETIDO);

        ProdutoResponse produtoResult =
            produtoClient.cadastrarProduto(produtoCadastrar, ADMIN)
                .then()
                .statusCode(400)
                .extract().as(ProdutoResponse.class);
        assertEquals("Já existe produto com esse nome", produtoResult.getMessage());
    }

    @Test
    public void testCadastrarProdutoComPermissaoNegada() {
        Produto produtoCadastrar = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoResult =
            produtoClient.cadastrarProduto(produtoCadastrar, CONVIDADO)
                .then()
                .statusCode(403)
                .extract().as(ProdutoResponse.class);
        assertEquals("Rota exclusiva para administradores", produtoResult.getMessage());
    }

    //Caminho Infeliz - Busca
    @Test
    public void testBuscarProdutoPorIdNaoExistente() {
        ProdutoResponse produtoResponse = produtoClient.buscarProduto(INEXISTENTE_ID)
            .then()
            .statusCode(400)
            .extract().as(ProdutoResponse.class);
        Assertions.assertAll("produtoResponse",
            () -> assertEquals("Produto não encontrado", produtoResponse.getMessage()),
            () -> Assertions.assertNull(produtoResponse.get_id())
        );
    }

    // Caminho Infeliz - Editar produto
    @Test
    public void testEditarProdutoComNomeRepetido() {
        Produto produtoCadastrar = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(produtoCadastrar, ADMIN).as(ProdutoResponse.class);
        produtoCadastrar.setNome("Logitech MX Vertical");

        ProdutoResponse produtoResponse =
            produtoClient.atualizarProduto(produtoCadastrado.get_id(), produtoCadastrar, ADMIN)
                .then()
                .statusCode(400)
                .extract().as(ProdutoResponse.class);
        Assertions.assertEquals("Já existe produto com esse nome", produtoResponse.getMessage());
    }


    @Test
    public void testEditarProdutoComIdNaoCadastrado() {
        ProdutoResponse produtoResponse =
            produtoClient.atualizarProduto(INEXISTENTE_ID, ProdutoDataFactory.produtoValido(), ADMIN)
                .then()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);

        assertEquals("Cadastro realizado com sucesso", produtoResponse.getMessage());

    }

    @Test
    public void testEditarProdutoSemPermissaoDeAcao() {
        ProdutoResponse produtoResponse =
            produtoClient.atualizarProduto(ID_PADRAO, ProdutoDataFactory.produtoValido(), CONVIDADO)
                .then()
                .statusCode(403)
                .extract().as(ProdutoResponse.class);
        assertEquals("Rota exclusiva para administradores", produtoResponse.getMessage());
    }

    @Test
    public void testEditarProdutoComTokenInvalido() {
        ProdutoResponse produtoResponse =
            produtoClient.atualizarProduto(ID_PADRAO, ProdutoDataFactory.produtoValido(), INVALIDO)
                .then()
                .statusCode(401)
                .extract().as(ProdutoResponse.class);

        assertEquals("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais", produtoResponse.getMessage());

    }

    @Test
    public void testDeletarProdutoComTokenInvalido() {

        ProdutoResponse produtoResponse =
            produtoClient.excluirProduto(ID_PADRAO, INVALIDO)
                .then()
                .statusCode(401)
                .extract().as(ProdutoResponse.class);
        assertEquals("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais", produtoResponse.getMessage());
    }

    @Test
    public void testDeletarProdutoEmCarrinho() {
        produtoClient.excluirProduto(ID_PADRAO, ADMIN)
            .then()
            .statusCode(400)
            .body("message", equalTo("Não é permitido excluir produto que faz parte de carrinho"))
        ;
    }

    @Test
    public void testDeletarProdutoComPermissaoInvalida() {

        ProdutoResponse produtoResponse =
            produtoClient.excluirProduto(ID_PADRAO, CONVIDADO)
                .then()
                .statusCode(403)
                .extract().as(ProdutoResponse.class);
        assertEquals("Rota exclusiva para administradores", produtoResponse.getMessage());
    }

    @ParameterizedTest
    @MethodSource("data.provider.ProdutoDataProvider#produtoDataProvider")
    public void testCadastrarProduto1(Produto produto, PermissaoTipoEnum permissao, String key, String value, int statusCode) {
        produtoClient.cadastrarProduto(produto, permissao)
            .then()
            .statusCode(statusCode)
            .body(key, equalTo(value))
        ;
    }
}
