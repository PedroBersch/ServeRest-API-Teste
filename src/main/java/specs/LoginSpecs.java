package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class LoginSpecs {

    private LoginSpecs() {
    }
    public static RequestSpecification loginReqSpec(){
        return new RequestSpecBuilder()
            .addRequestSpecification(InicialSpecs.setup())
            .setContentType(ContentType.JSON)
            .build();
    }

}
