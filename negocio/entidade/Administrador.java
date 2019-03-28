package negocio.entidade;

/**
 * Essa classe representa o administrador do sistema da clinica, ele é necessário
 * somente na primeira vez que o sistema é inicializado, ele só pode fazer cadastros
 * de médicos ou funcionários.
 */
public class Administrador {
    
    private static String login = "adm";
    private static String senha = "senha";

    public Administrador() {
    }

    public static String getLogin() {
        return login;
    }

    public static String getSenha() {
        return senha;
    }
    
    
}
