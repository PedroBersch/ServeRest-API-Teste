package model;

import lombok.Data;

@Data
public class UsuarioResponse extends Usuario{

    private String message;
    private String _id;
}
