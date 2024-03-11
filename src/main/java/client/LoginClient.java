package client;

import data.factory.LoginDataFactory;
import io.restassured.response.Response;
import model.Login;
import specs.LoginSpecs;

import static io.restassured.RestAssured.given;

public class LoginClient {
    private static final String LOGINPATH = "/login";

    public LoginClient() {
    }

    public Response fazerLogin(Login login) {
        return
            given()
                .spec(LoginSpecs.loginReqSpec())
                .body(login)
                .when()
                .post(LOGINPATH)
            ;
    }
    public String tokenAdm(){
        Login login = LoginDataFactory.dadosAdm();
        return fazerLogin(login)
            .then()
            .extract().path("authorization")
            ;
    }
    public String tokenConvidado(){
        Login login = LoginDataFactory.dadosConvidado();
        return fazerLogin(login)
            .then()
            .extract().path("authorization")
            ;
    }
}
