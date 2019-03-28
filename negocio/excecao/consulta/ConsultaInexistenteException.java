package negocio.excecao.consulta;

public class ConsultaInexistenteException extends ConsultaException {

    public ConsultaInexistenteException() {
        super("Consulta inexistente no banco de dados.");
    }
    
}
