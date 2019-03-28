package negocio.entidade;

import java.io.Serializable;
import java.util.Date;

public class Funcionario extends Pessoa implements Serializable{
	
    private String login;
    private String senha;
    private int id;

    public Funcionario(String login, String senha, String nome, String cpf, Date dataNascimento, int id) {
        super(nome, cpf, dataNascimento);
        this.login = login;
        this.senha = senha;
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    public String getSenha() {
        return senha;
    }

    public int getId() {
        return id;
    }
    
    public void setLogin(String login){
    	this.login = login;
    }
	
    @Override
    public String toString() {
        return "ID: "+this.getId()+" - CPF: "+this.getCpf()+" - Nome: "+this.getNome();
    }
}
