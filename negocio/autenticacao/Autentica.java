package negocio.autenticacao;

import fachada.ServidorClinica;
import negocio.entidade.Funcionario;
import negocio.entidade.Medico;
import java.util.ArrayList;
import negocio.entidade.Administrador;

/**
 * Classe responsavel por verificar se a senha e o login corresponde a algum 
 * dos armazenados no repositorio, ou seja, faz a ligação entre a JanelaLogin
 * e os repositorios
 */
public class Autentica{
    private String login, senha;
    private Administrador adm = new Administrador();
    private ArrayList<Medico> medicos;
    private ArrayList<Funcionario> funcionarios;
    
    /**
     * O construtor recebe as informações de autenticação, e faz a leitura dos objetos
     * armazenados nos arquivos
     * @param login Login a ser buscado
     * @param senha Senha a ser buscada
     * @param fachada objeto que contém os usuarios cadastrados até o momento
     */
    public Autentica(String login, String senha, ServidorClinica fachada){
        this.login = login;
        this.senha = senha;
        this.medicos = fachada.getMedicos();
        this.funcionarios = fachada.getFuncionarios();
    }
    
    /**
     * Esse método faz a busca e comparação do login e senha nos repositorios de Funcionario e Medico,
     * caso não ache correspondência neles, ele compara com os dados do Administrador do sistema
     * @return O objeto em que houve correspondencia dos dados
     */
    public Object verificar(){
        if (!medicos.isEmpty()){
            for (int i=0;i<medicos.size();i++) {
                if(medicos.get(i) instanceof Medico){ // Esse if foi a solução para uma exceção de cast INEXPLICÁVEL
                    if (medicos.get(i).getLogin().equals(this.login)){
                        if(medicos.get(i).getSenha().equals(this.senha)){
                            return medicos.get(i);
                        }else{
                            return null;
                        }
                    }
                }
            }
        }
        if (!funcionarios.isEmpty()){
            for (Funcionario f: funcionarios){
                if (f.getLogin().equals(this.login)){
                    if(f.getSenha().equals(this.senha)){
                        return f;
                    }else{
                        return null;
                    }
                }
            }
        }
        if((login.equals("ozildo")) && (senha.equals("9966")))
            return adm;
        else{
            return null;
        }
    }
}
