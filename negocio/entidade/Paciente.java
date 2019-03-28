package negocio.entidade;

import java.io.Serializable;
import java.util.Date;

public class Paciente extends Pessoa implements Serializable{

    private String nomeResponsavel;
    private String numeroContato;
    private String sexo;

    public Paciente(String nomeResponsavel, String numeroContato, String nome, String cpf, String sexo, Date dataNascimento) {
        super(nome, cpf, dataNascimento);
        this.nomeResponsavel = nomeResponsavel;
        this.numeroContato = numeroContato;
        this.sexo = sexo;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getNumeroContato() {
        return numeroContato;
    }

    public void setNumeroContato(String numeroContato) {
        this.numeroContato = numeroContato;
    }  
    @Override
    public String toString() {
        return "CPF: "+this.getCpf()+" - Nome: "+this.getNome();
    }
	
}
