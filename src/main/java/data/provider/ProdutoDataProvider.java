package data.provider;

import data.factory.ProdutoDataFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static model.enums.PermissaoTipoEnum.*;
import static org.apache.http.HttpStatus.*;

public class ProdutoDataProvider {
    private ProdutoDataProvider(){

    }
    //KEY
    private static final String KEY_NOME = "nome";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_PRECO = "preco";
    private static final String KEY_QUANTIDADE = "quantidade";
    private static final String KEY_MESSAGE = "message";

    //VALUES
    private static final String VALUE_NOME_EM_BRANCO = "nome não pode ficar em branco";
    private static final String VALUE_DESCRICAO_EM_BRANCO = "descricao não pode ficar em branco";
    private static final String VALUE_PRECO_NEGATIVO = "preco deve ser um número positivo";
    private static final String VALUE_QUANTIDADE_NEGATIVA = "quantidade deve ser maior ou igual a 0";
    private static final String VALUE_CADASTRO_SUCESSO = "Cadastro realizado com sucesso";
    private static final String VALUE_NOME_REPETIDO = "Já existe produto com esse nome";
    private static final String VALUE_TOKEN_INVALIDO = "Token de acesso ausente, inválido, expirado ou usuário do token não existe mais";
    private static final String VALUE_TOKEN_CONVIDADO = "Rota exclusiva para administradores";

    public static Stream<Arguments> produtoDataProvider() {
        return Stream.of(
            Arguments.of(ProdutoDataFactory.produtoComNomeEmBranco(), ADMIN, KEY_NOME, VALUE_NOME_EM_BRANCO, SC_BAD_REQUEST),
            Arguments.of(ProdutoDataFactory.produtoComDescricaoEmBranco(), ADMIN, KEY_DESCRICAO, VALUE_DESCRICAO_EM_BRANCO, SC_BAD_REQUEST),
            Arguments.of(ProdutoDataFactory.produtoComPrecoZero(), ADMIN, KEY_PRECO, VALUE_PRECO_NEGATIVO, SC_BAD_REQUEST),
            Arguments.of(ProdutoDataFactory.produtoComPrecoNegativo(), ADMIN, KEY_PRECO, VALUE_PRECO_NEGATIVO, SC_BAD_REQUEST),
            Arguments.of(ProdutoDataFactory.produtoComQuantidadeNegativa(), ADMIN, KEY_QUANTIDADE, VALUE_QUANTIDADE_NEGATIVA, SC_BAD_REQUEST),
            Arguments.of(ProdutoDataFactory.produtoComQuantidadeNegativa(), ADMIN, KEY_QUANTIDADE, VALUE_QUANTIDADE_NEGATIVA, SC_BAD_REQUEST),
            Arguments.of(ProdutoDataFactory.produtoValido(), ADMIN, KEY_MESSAGE, VALUE_CADASTRO_SUCESSO, SC_CREATED),
            Arguments.of(ProdutoDataFactory.produtoComNomeRepetido(), ADMIN, KEY_MESSAGE, VALUE_NOME_REPETIDO, SC_BAD_REQUEST),
            Arguments.of(ProdutoDataFactory.produtoValido(), INVALIDO, KEY_MESSAGE, VALUE_TOKEN_INVALIDO, SC_UNAUTHORIZED),
            Arguments.of(ProdutoDataFactory.produtoValido(), CONVIDADO, KEY_MESSAGE, VALUE_TOKEN_CONVIDADO, SC_FORBIDDEN)
        );
    }
}
