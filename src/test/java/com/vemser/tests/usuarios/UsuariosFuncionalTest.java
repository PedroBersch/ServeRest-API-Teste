package com.vemser.tests.usuarios;

import client.UsuarioClient;
import data.factory.UsuarioDataFactory;
import model.Usuario;
import model.UsuarioResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class UsuariosFuncionalTest {
    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testCadastrarUsuarioComSucesso() {
        Usuario usuarioCadastrar = UsuarioDataFactory.usuarioValido();

        UsuarioResponse usuarioResult =
            usuarioClient.cadastrarUsuario(usuarioCadastrar)
                .then()
                    .statusCode(201)
                    .extract().as(UsuarioResponse.class);

        Assertions.assertAll(
            "usuarioResult",
            () -> Assertions.assertEquals("Cadastro realizado com sucesso", usuarioResult.getMessage()),
            () -> Assertions.assertNotNull(usuarioResult.get_id())
        );
    }
    @Test
    public void testCadastrarUsuarioComSucesso2() {
        Usuario usuarioCadastrar = UsuarioDataFactory.usuarioValido();

        UsuarioResponse usuarioResult =
            usuarioClient.cadastrarUsuario(usuarioCadastrar)
                .then()
                .statusCode(201)
                .extract().as(UsuarioResponse.class);

        Assertions.assertAll(
            "usuarioResult",
            () -> Assertions.assertEquals("Cadastro realizado com sucesso", usuarioResult.getMessage()),
            () -> Assertions.assertNotNull(usuarioResult.get_id())
        );
    }

    @Test
    public void testCadastrarUsuarioComDadosAusentes() {
        Usuario usuariosComDadosAusentes = UsuarioDataFactory.usuarioComDadosAusentes();

        UsuarioResponse usuarioResponse =
            usuarioClient.cadastrarUsuario(usuariosComDadosAusentes)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(UsuarioResponse.class);

        Assertions.assertAll(
            "usuarioResponse",
            () -> Assertions.assertEquals("nome não pode ficar em branco", usuarioResponse.getNome()),
            () -> Assertions.assertEquals("email não pode ficar em branco", usuarioResponse.getEmail()),
            () -> Assertions.assertEquals("password não pode ficar em branco", usuarioResponse.getPassword()),
            () -> Assertions.assertEquals("administrador deve ser 'true' ou 'false'", usuarioResponse.getAdministrador())
        );
    }

    //REFACTOR
    @Test
    public void testBuscarUsuarioPorQuerryComSucesso() {
        String nomeUsuario = "Pedro";
        given()
            .when()
            .queryParam("nome", nomeUsuario)
            .log().all()
            .get("/usuarios")
            .then()
            .log().all()
            .statusCode(200)
            .assertThat()
            .body("usuarios.'nome'", hasSize(2))
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComSucesso() {
        Usuario usuario = UsuarioDataFactory.usuarioValido();

        UsuarioResponse usuarioCadastrar = usuarioClient.cadastrarUsuario(usuario).as(UsuarioResponse.class);

        UsuarioResponse usuarioResponse = usuarioClient.buscarUsuario(usuarioCadastrar.get_id())
            .then()
                .statusCode(200)
                .extract().as(UsuarioResponse.class);

        Assertions.assertAll("usuarioResponse",
            () -> Assertions.assertNotNull(usuarioResponse.get_id()),
            () -> Assertions.assertEquals(usuario.getNome(), usuarioResponse.getNome()),
            () -> Assertions.assertEquals(usuario.getEmail(), usuarioResponse.getEmail()),
            () -> Assertions.assertEquals(usuario.getPassword(), usuarioResponse.getPassword()),
            () -> Assertions.assertEquals(usuario.getAdministrador(), usuarioResponse.getAdministrador())
            );

        usuarioClient.deletarUsuario(usuarioResponse.get_id());
    }

    @Test
    public void testEditarUsuarioComSucesso() {
        Usuario usuario = UsuarioDataFactory.usuarioValido();

        UsuarioResponse usuarioCadastrado = usuarioClient.cadastrarUsuario(usuario).as(UsuarioResponse.class);

        UsuarioResponse usuarioResponse =  usuarioClient.atualizarUsuario(usuarioCadastrado.get_id(), UsuarioDataFactory.usuarioValido())
            .then()
                .statusCode(200)
                .extract().as(UsuarioResponse.class)
            ;
        UsuarioResponse usuarioAtualizado = usuarioClient.buscarUsuario(usuarioCadastrado.get_id()).as(UsuarioResponse.class);
        Assertions.assertAll("usuarioResponse",
            () -> Assertions.assertEquals("Registro alterado com sucesso", usuarioResponse.getMessage()),
            () -> Assertions.assertNotNull(usuarioAtualizado.getNome(), usuario.getNome()),
            () -> Assertions.assertNotNull(usuarioAtualizado.getEmail(), usuario.getEmail()),
            () -> Assertions.assertNotNull(usuarioAtualizado.getPassword(), usuario.getPassword()),
            () -> Assertions.assertNotNull(usuarioAtualizado.getAdministrador(), usuario.getAdministrador())
        );
        usuarioClient.deletarUsuario(usuarioCadastrado.get_id());
    }

    @Test
    public void testCadastroExcluirComSucesso() {
        UsuarioResponse usuarioCadastrado = usuarioClient.cadastrarUsuario(
            UsuarioDataFactory.usuarioValido()).as(UsuarioResponse.class);
            UsuarioResponse usuarioResponse = usuarioClient.deletarUsuario(usuarioCadastrado.get_id())
            .then()
                .statusCode(200)
                .extract().as(UsuarioResponse.class)
                ;
        Assertions.assertAll(
            "usuarioResponse",
            () -> Assertions.assertEquals("Registro excluído com sucesso", usuarioResponse.getMessage())
        );
    }

    @ParameterizedTest
    @MethodSource("com.vemser.tests.providerdata.StreamArguments#provideStringIdUsuarios")
    public void testBuscarUsuarioPorIDComSucessoParametrizado(String id) {

        UsuarioResponse usuarioResponse = usuarioClient.buscarUsuario(id)
            .then()
            .extract().as(UsuarioResponse.class);

        Assertions.assertAll("usuarioResponse",
                () -> Assertions.assertNotNull(usuarioResponse.get_id())
        );

//        usuarioClient.deletarUsuario(usuarioResponse.get_id());
    }
    @ParameterizedTest
    @MethodSource("com.vemser.data.provider.UsuarioDataProvider#usuarioDataProvider")
    public void testExemploTesteParametrizadoCadastrarUsuario(Usuario usuario, String key, String value) {

        usuarioClient.cadastrarUsuario(usuario)
            .then()
            .statusCode(400)
            .body(key, equalTo(value))
        ;
    }
}
