package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoResponse extends Carrinho{
    private Integer quantidade;
    private ArrayList<Carrinho> carrinhos;
    private Integer precoTotal;
    private Integer quantidadeTotal;
    private String idUsuario;
    private String _id;
    private String message;
}
