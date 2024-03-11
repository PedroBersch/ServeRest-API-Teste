package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class WiremockSpecs {
    private WiremockSpecs(){

    }
    public static RequestSpecification wiremockReqSpec() {
        return new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .build();
    }
}
