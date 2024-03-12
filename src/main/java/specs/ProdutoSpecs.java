package specs;

import client.LoginClient;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.enums.PermissaoTipoEnum;

public class ProdutoSpecs {

     static LoginClient loginClient = new LoginClient();

    public ProdutoSpecs() {
    }

    public static RequestSpecification produtoReqSpecAdm() {
        return new RequestSpecBuilder()
            .addRequestSpecification(InicialSpecs.setup())
            .addHeader("authorization", loginClient.tokenAdm())
            .setContentType(ContentType.JSON)
            .build()
            ;
    }

    public static RequestSpecification produtoReqSpecFuncionario() {
        return new RequestSpecBuilder()
            .addRequestSpecification(InicialSpecs.setup())
            .addHeader("authorization", loginClient.tokenConvidado())
            .setContentType(ContentType.JSON)
            .build()
            ;
    }

    public static RequestSpecification produtoReqSpecTokenInvalido() {
        return new RequestSpecBuilder()
            .addRequestSpecification(InicialSpecs.setup())
            .addHeader("authorization", "Invalido")
            .setContentType(ContentType.JSON)
            .build()
            ;
    }
    public RequestSpecification produtoReqSpec() {
        return produtoReqSpecFuncionario();
    }
    public RequestSpecification produtoReqSpec(PermissaoTipoEnum permissao) {
            switch (permissao) {
                case ADMIN:
                    return ProdutoSpecs.produtoReqSpecAdm();
                case CONVIDADO:
                    return ProdutoSpecs.produtoReqSpecFuncionario();
                default:
                    return ProdutoSpecs.produtoReqSpecTokenInvalido();
            }
    }
}
