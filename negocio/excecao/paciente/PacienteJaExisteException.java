package negocio.excecao.paciente;

public class PacienteJaExisteException extends PacienteException {
    public PacienteJaExisteException(){
        super("Paciente ja existe, nao é possivel adicioná-lo novamente.");
    }
}
