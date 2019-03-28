package negocio.excecao.consulta;

public class ConsultasExcedidasMedicoException extends ConsultaException {

    public ConsultasExcedidasMedicoException() {
        super("Médico já atingiu limite para esse dia.");
    }
    
}
