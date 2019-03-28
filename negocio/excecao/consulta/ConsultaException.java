package negocio.excecao.consulta;

public class ConsultaException extends Exception{
    private String msg;
    
    public ConsultaException(String mgs){
        super(mgs);
        this.msg = mgs;
    }
    
    @Override
    public String getMessage(){
        return msg;
    }
}
