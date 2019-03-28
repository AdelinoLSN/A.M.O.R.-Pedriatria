package negocio.excecao.funcionario;

public class FuncionarioJaExisteException extends FuncionarioException {
    public FuncionarioJaExisteException(){
        super("Funcionario ja existe no banco de dados, nao e possivel adiciona-lo novamente.");
    }
}
