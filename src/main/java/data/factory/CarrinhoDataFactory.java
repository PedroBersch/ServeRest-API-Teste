package data.factory;

import client.ProdutoClient;
import model.CarrinhoProduto;
import model.Produto;
import model.ProdutoResponse;

import java.util.ArrayList;

import static model.enums.PermissaoTipoEnum.ADMIN;

public class CarrinhoDataFactory {
    public static ProdutoClient produtoClient = new ProdutoClient();
    public CarrinhoDataFactory(){
    }
    public static ArrayList<CarrinhoProduto> listaCarrinhoValido(){
        Produto produto = ProdutoDataFactory.produtoValido();
        ProdutoResponse produtoResponse = produtoClient.cadastrarProduto(produto, ADMIN).as(ProdutoResponse.class);
        CarrinhoProduto carrinhoProduto = new CarrinhoProduto(produtoResponse.get_id(), produto.getQuantidade());
        ArrayList<CarrinhoProduto> listaCarrinho = new ArrayList<>();
        listaCarrinho.add(carrinhoProduto);
        return listaCarrinho;
    }
}
