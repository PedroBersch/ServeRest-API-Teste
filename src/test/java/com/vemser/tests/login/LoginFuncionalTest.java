package com.vemser.tests.login;

import client.LoginClient;
import data.factory.LoginDataFactory;
import model.Login;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class LoginFuncionalTest {
    LoginClient loginClient = new LoginClient();

    @Test
    public void testLogarComSucesso(){

        Login login = LoginDataFactory.loginValido();
        loginClient.fazerLogin(login)
            .then()
                .statusCode(200)
                .body("message",equalTo("Login realizado com sucesso"))
        ;
    }
    @Test
    public void testLogarFuncionarioComSucesso(){
        Login login = LoginDataFactory.loginInvalido();

        loginClient.fazerLogin(login)
            .then()
                .statusCode(401)
                .body("message",equalTo("Email e/ou senha inválidos"))
            ;
    }
    @Test
    public void testLogarComDadosProprieties(){
        Login login = LoginDataFactory.dadosAdm();

        loginClient.fazerLogin(login)
            .then()
            .statusCode(200)
            .body("message",equalTo("Login realizado com sucesso"))
        ;
    }
}
