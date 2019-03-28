package negocio.excecao.medico;

public class MedicoException extends Exception{
    private String msg;
	
    public MedicoException(String msg){
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return msg;
    }
}
