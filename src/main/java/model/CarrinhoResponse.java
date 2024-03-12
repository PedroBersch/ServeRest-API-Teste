package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoResponse extends Carrinho{
    private ArrayList<Carrinho> carrinhos;
    private Integer quantidade;
}
