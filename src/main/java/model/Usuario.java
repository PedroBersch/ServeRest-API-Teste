package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String administrador;
    private String nome;
    private String email;
    private String password;
}
