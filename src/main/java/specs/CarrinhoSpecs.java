package specs;

import client.LoginClient;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CarrinhoSpecs {
    static LoginClient loginClient = new LoginClient();

    public CarrinhoSpecs(){

    }
    public RequestSpecification carrinhoReqSpec(){
        return new RequestSpecBuilder()
            .addRequestSpecification(InicialSpecs.setup())
            .addHeader("authorization",loginClient.tokenAdm())
            .setContentType(ContentType.JSON)
            .build()
            ;
    }
}
