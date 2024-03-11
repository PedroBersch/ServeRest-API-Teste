package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;

public class InicialSpecs {

    private InicialSpecs() {
    }

    public static RequestSpecification setup() {
        return new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(3000)
            .setConfig(config().logConfig(
                logConfig().enableLoggingOfRequestAndResponseIfValidationFails()
            ))
            .build();
    }
}
