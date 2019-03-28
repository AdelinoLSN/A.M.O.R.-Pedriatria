package negocio.excecao.funcionario;

public class FuncionarioException extends Exception{
    private String msg;
	
    public FuncionarioException(String msg){
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return msg;
    }
}
