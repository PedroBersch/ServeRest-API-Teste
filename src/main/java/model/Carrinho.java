package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {

    private ArrayList<CarrinhoProduto> produtos = new ArrayList<>();

}
