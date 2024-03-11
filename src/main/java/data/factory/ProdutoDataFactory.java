package data.factory;

import model.Produto;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class ProdutoDataFactory {
    private static final Faker geradorFaker = new Faker(new Locale("PT-BR"));

    public ProdutoDataFactory() {
    }

    public static Produto produtoValido() {
        return novoProduto();
    }

    public static Produto produtoComDadosAusentes(){
        Produto produtoSemDados = novoProduto();
        produtoSemDados.setNome(geradorFaker.commerce().productName());
        produtoSemDados.setPreco(geradorFaker.number().numberBetween(1, 1000));
        produtoSemDados.setDescricao(geradorFaker.commerce().material());
        produtoSemDados.setQuantidade(geradorFaker.number().numberBetween(1, 1000));
        return produtoSemDados;
    }

    private static Produto novoProduto() {
        Produto produtoNovo = new Produto();
        produtoNovo.setNome(geradorFaker.commerce().productName());
        produtoNovo.setPreco(geradorFaker.number().numberBetween(1, 1000));
        produtoNovo.setDescricao(geradorFaker.commerce().material());
        produtoNovo.setQuantidade(geradorFaker.number().numberBetween(1, 1000));
        return produtoNovo;
    }
    public static Produto produtoComNomeEmBranco(){
        Produto produto = novoProduto();
        produto.setNome(StringUtils.EMPTY);

        return produto;
    }
    public static Produto produtoComDescricaoEmBranco(){
        Produto produto = novoProduto();
        produto.setDescricao(StringUtils.EMPTY);

        return produto;
    }
    public static Produto produtoComPrecoNegativo(){
        Produto produto = novoProduto();
        produto.setPreco(-1);

        return produto;
    }
    public static Produto produtoComPrecoZero(){
        Produto produto = novoProduto();
        produto.setPreco(0);

        return produto;
    }
    public static Produto produtoComQuantidadeNegativa(){
        Produto produto = novoProduto();
        produto.setQuantidade(-1);

        return produto;
    }
    public static Produto produtoComNomeRepetido(){
        Produto produto = novoProduto();
        produto.setNome("Produto Test Repetido");

        return produto;
    }
}
