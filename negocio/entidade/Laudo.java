package negocio.entidade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Laudo implements Serializable{
	
    private Medico medico;
    private double altura;
    private Paciente paciente;
    private Date dataNascimento;
    private String parecer;

    public Laudo(Medico medico, double altura, Paciente paciente, Date dataNascimento, String parecer) {
        this.medico = medico;
        this.altura = altura;
        this.paciente = paciente;
        this.dataNascimento = dataNascimento;
        this.parecer = parecer;
    }
    
    public Medico getMedico() {
            return medico;
    }
    
    public double getAltura() {
            return altura;
    }
    
    public Paciente getPaciente() {
            return paciente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public String getParecer() {
            return parecer;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(this.getPaciente().getDataNascimento());
        return 	"              A.M.O.R. Pediatria              \n" + "\n\n"
                        + "Medico: "+this.getPaciente().getNome()+"\n"
                    + "CRM do Medico: "+this.getMedico().getCrm()+"\n\n"
                    + "Nome do paciente: "+this.getPaciente().getNome()+"\n"
                    + "Data de nascimento: "+data+"\n"
                    + "Altura: "+this.getAltura()+"\n\n" + "Parecer: "+this.parecer;
    }
}
