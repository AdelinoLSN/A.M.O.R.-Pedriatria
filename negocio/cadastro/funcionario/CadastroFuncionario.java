package negocio.cadastro.funcionario;

import negocio.entidade.Funcionario;
import dados.repositorio.RepositorioFuncionario;
import java.util.ArrayList;
import negocio.excecao.funcionario.FuncionarioJaExisteException;
import negocio.excecao.funcionario.FuncionarioInexistenteException;
import negocio.excecao.cadastro.LoginEmUsoException;

public class CadastroFuncionario {
    private RepositorioFuncionario repositorio;

    public CadastroFuncionario(){
        this.repositorio = new RepositorioFuncionario();
    }
    
    /**
     * Metodo para cadastrar um funcionario no sistema
     * @param func um objeto do tipo funcionario que sera adicionado
     * @throws FuncionarioJaExisteException caso esse funcionario ja conste em nosso sistema
     * @throws LoginEmUsoException caso o login digitado já esteja em uso
     */
    public void adicionar(Funcionario func) throws FuncionarioJaExisteException, LoginEmUsoException{
        boolean existe = repositorio.existe(Integer.toString(func.getId()));
        if  (existe)  throw new FuncionarioJaExisteException();
        else{
             if(!repositorio.getArray().isEmpty()){
                boolean achou = false;
                for(Funcionario m: repositorio.getArray()){
                    if (m.getLogin().equals(func.getLogin())) achou = true;            
                }
                if(achou){
                    throw new LoginEmUsoException();
                }else{
                    repositorio.adicionar(func);
                }
            }else{
                repositorio.adicionar(func);
            }
        }
    }
    
    /**
     * Metodo para remocao de um funcionario dado um id
     * @param id identificacao do funcionario
     * @throws FuncionarioInexistenteException caso funcionario nao esteja em nosso sistema
     */
    public void remover(String id) throws FuncionarioInexistenteException {
        Funcionario func = (Funcionario) repositorio.buscar(id);
        if(func != null) repositorio.remover(func); 
        else throw new FuncionarioInexistenteException();
    }
    
    /**
     * Busca um funcionario no banco de dados dada uma informacao do mesmo
     * @param id identificador do funcionario a ser buscado
     * @return um objeto do tipo Funcionario
     * @throws FuncionarioInexistenteException caso o funcionario nao conste no sistema
     */
    public Funcionario consultar(String id) throws FuncionarioInexistenteException {
        if(!repositorio.existe(id)) throw new FuncionarioInexistenteException();
        else return (Funcionario) repositorio.buscar(id);
    }
    
    /**
     * Funcao para atualizacao de um funcionario
     * @param id identificador do funcionario
     * @param func objeto de funcionario a sobrescrever o anterior
     * @throws FuncionarioInexistenteException caso funcionario nao exista no sistema
     */
    public void atualizar(String id, Funcionario func) throws FuncionarioInexistenteException {
        if(repositorio.existe(id)) repositorio.atualizar(func,id); 
        else throw new FuncionarioInexistenteException();
    }    
    /**
     * Metodo para buscar um repositorio
     * @return Repositorio de Funcionarios
     */
    public ArrayList<Funcionario> getFuncionarios() {
        return repositorio.getArray();
    }
}
