package negocio.entidade;

import java.io.Serializable;
import java.util.Date;

public class Medico extends Pessoa implements Serializable{
    private String crm;
    private String login;
    private String senha;

    public Medico(String crm, String login, String senha, String nome, String cpf, Date dataNascimento) {
        super(nome, cpf, dataNascimento);
        this.crm = crm;
        this.login = login;
        this.senha = senha;
    }
    
    public String getCrm() {
            return crm;
    }
    public String getLogin() {
            return login;
    }
    public void setLogin(String login){
            this.login = login;
    }
    public String getSenha() {
            return senha;
    }

    @Override
    public String toString() {
        return "CRM: "+this.getCrm()+" - Nome: "+this.getNome();
    }
}
