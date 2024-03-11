package com.vemser.tests.usuarios;

import client.UsuarioClient;
import data.factory.UsuarioDataFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import model.Usuario;
import model.UsuarioResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class UsuariosContratoTest {

    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testValidarContratoBuscaPorID() {
        Usuario usuario = UsuarioDataFactory.usuarioValido();

        UsuarioResponse usuarioCadastrar = usuarioClient.cadastrarUsuario(usuario).as(UsuarioResponse.class);

        usuarioClient.buscarUsuario(usuarioCadastrar.get_id())
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/busca_usuario_id.json"))
        ;
    }

    @Test
    public void testValidarContratoCadastrarUsuario() {
        Usuario usuarioCadastrar = UsuarioDataFactory.usuarioValido();

        usuarioClient.cadastrarUsuario(usuarioCadastrar)
        .then()
        .statusCode(HttpStatus.SC_CREATED)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/cadastra_usuario.json"))
        ;
    }
}
