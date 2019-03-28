package negocio.excecao.medico;

public class MedicoJaExisteException extends MedicoException{
    public MedicoJaExisteException() {
        super("CRM já cadastrado no banco de dados.");
    }
}
