package negocio.excecao.cadastro;

import negocio.excecao.funcionario.FuncionarioException;

public class LoginEmUsoException extends FuncionarioException{
    
    public LoginEmUsoException() {
        super("Login em uso.");
    }
        
    
}
