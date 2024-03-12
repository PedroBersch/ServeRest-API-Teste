package model;

import lombok.Data;

@Data
public class ProdutoResponse extends Produto{
    private String message;
    private String _id;

}
