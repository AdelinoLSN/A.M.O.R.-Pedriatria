package negocio.excecao.consulta;

public class MedicoSemConsultasExceptions extends ConsultaException{

    public MedicoSemConsultasExceptions() {
        super("Nenhuma consulta marcada para esse medico.");
    }
    
}
