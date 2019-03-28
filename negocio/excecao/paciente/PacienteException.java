package negocio.excecao.paciente;

public class PacienteException extends Exception{
    private String msg;
	
    public PacienteException(String msg){
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return msg;
    }
}