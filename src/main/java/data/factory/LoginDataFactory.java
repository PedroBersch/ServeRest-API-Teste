package data.factory;

import client.LoginClient;
import model.Login;
import utils.Manipulation;

import java.util.Properties;

public class LoginDataFactory {
    public LoginDataFactory(){

    }

    public static Login dadosAdm(){
        Properties props = Manipulation.getProp();

        return new Login(props.getProperty("adminEmail"), props.getProperty("adminSenha"));
    }
    public static Login dadosConvidado(){
        Properties props = Manipulation.getProp();

        return new Login(props.getProperty("funcionarioEmail"), props.getProperty("funcionarioSenha"));
    }
    public static Login loginValido(){
        return new Login("rodrigo.dapaz@gmail.com","d2e586x05971zn09");
    }
    public static Login loginInvalido(){
        return new Login("123456@gmail.com","123Teste123");
    }
}
