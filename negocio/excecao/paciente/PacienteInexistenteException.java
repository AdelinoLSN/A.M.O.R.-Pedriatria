package negocio.excecao.paciente;

public class PacienteInexistenteException extends PacienteException{
    public PacienteInexistenteException(){
        super("Paciente não cadastrado no banco de dados.");
    }
}
