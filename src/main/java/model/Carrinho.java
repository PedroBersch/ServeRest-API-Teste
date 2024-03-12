package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {
    private ArrayList<ProdutoResponse> produtos;
    private Integer precoTotal;
    private Integer quantidadeTotal;
    private String idUsuario;
    private String _id;
}
