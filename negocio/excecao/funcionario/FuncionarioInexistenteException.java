package negocio.excecao.funcionario;

public class FuncionarioInexistenteException extends FuncionarioException{
    public FuncionarioInexistenteException() {
        super("Funcionario nao existe no banco de dados.");
    }
}
