package negocio.excecao.medico;

public class MedicoInexistenteException extends MedicoException{
    public MedicoInexistenteException() {
        super("Medico não cadastrado no banco de dados.");
    }
}
