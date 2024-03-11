package data.factory;

import model.Usuario;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Random;

public class UsuarioDataFactory {
    // Classe fabrica de dados
    private static Faker geradorFaker = new Faker(new Locale("PT-BR"));
    private static Random geradorRandomBollean = new Random();

    private UsuarioDataFactory() {
    }

    public static Usuario usuarioValido() {
        return novoUsuario();
    }

    public static Usuario usuarioComDadosAusentes() {
        Usuario usuarioSemDados = novoUsuario();
        usuarioSemDados.setNome(StringUtils.EMPTY);
        usuarioSemDados.setEmail(StringUtils.EMPTY);
        usuarioSemDados.setPassword(StringUtils.EMPTY);
        usuarioSemDados.setAdministrador(StringUtils.EMPTY);
        return usuarioSemDados;
    }

    private static Usuario novoUsuario() {
        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setNome(geradorFaker.name().firstName() + " " + geradorFaker.name().lastName());
        usuarioNovo.setEmail(geradorFaker.internet().emailAddress());
        usuarioNovo.setPassword(geradorFaker.internet().password());
        usuarioNovo.setAdministrador(String.valueOf(geradorRandomBollean.nextBoolean()));
        //Retorna um body request
        return usuarioNovo;
    }
    public static Usuario usuarioComNomeEmBranco() {
        Usuario usuario = novoUsuario();
        usuario.setNome(StringUtils.EMPTY);

        return usuario;
    }

    public static Usuario usuarioComEmailEmBranco() {
        Usuario usuario = novoUsuario();
        usuario.setEmail(StringUtils.EMPTY);

        return usuario;
    }

    public static Usuario usuarioComPasswordEmBranco() {
        Usuario usuario = novoUsuario();
        usuario.setPassword(StringUtils.EMPTY);

        return usuario;
    }

    public static Usuario usuarioComIsAdminEmBranco() {
        Usuario usuario = novoUsuario();
        usuario.setAdministrador(StringUtils.EMPTY);

        return usuario;
    }
}
