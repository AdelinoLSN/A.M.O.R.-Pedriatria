package negocio.cadastro.medico;

import dados.repositorio.RepositorioMedico;
import java.util.ArrayList;
import negocio.entidade.Medico;
import negocio.excecao.cadastro.LoginEmUsoException;
import negocio.excecao.medico.MedicoInexistenteException;
import negocio.excecao.medico.MedicoJaExisteException;

public class CadastroMedico {
    private RepositorioMedico repositorio;

    public CadastroMedico(){
        this.repositorio = new RepositorioMedico();
    }
    
    /**
     * Metodo que adiciona um medico ao repositorio
     * @param med o Medico a ser adicionado
     * @throws MedicoJaExisteException caso esse Medico ja esteja no sistema
     */
    public void adicionar(Medico med) throws MedicoJaExisteException, LoginEmUsoException{
        boolean existe = repositorio.existe(med.getCrm());
        if(existe) throw new MedicoJaExisteException();
        else{ 
            if(!repositorio.getArray().isEmpty()){
                boolean achou = false;
                for(Medico m: repositorio.getArray()){
                    if (m.getLogin().equals(med.getLogin())) achou = true;            
                }
                if(achou){
                    throw new LoginEmUsoException();
                }else{
                    repositorio.adicionar(med);
                }
            }else{
                repositorio.adicionar(med);
            }
        }
    }
    
    /**
     * Metodo para remocao de um medico do sistema
     * @param crm identificador do medico
     * @throws MedicoInexistenteException caso medico nao esteja cadastrado
     */
    public void remover(String crm) throws MedicoInexistenteException {
        Medico med = (Medico) repositorio.buscar(crm);
        if(med != null) repositorio.remover(med);
        else throw new MedicoInexistenteException();
    }
    
    /**
     * Busca um medico no sistema
     * @param crm identificador do medico
     * @return um objeto do tipo medico que tem o crm requisitado
     * @throws MedicoInexistenteException caso nao exista medico com o crm no sistema
     */
    public Medico consultar(String crm) throws MedicoInexistenteException {
        if(!repositorio.existe(crm)) throw new MedicoInexistenteException();
        else return (Medico) repositorio.buscar(crm);
    }
    
    /**
     * Metodo para atualizar informacao de medico
     * @param crm identificador do medico
     * @param med objeto de Medico atualizado
     * @throws MedicoInexistenteException caso o medico nao seja encontrado
     */
    public void atualizar(String crm, Medico med) throws MedicoInexistenteException {
        if(repositorio.existe(crm)) repositorio.atualizar(med, crm);
        else throw new MedicoInexistenteException();
    }    
    
    /**
     * Metodo para buscar um repositorio
     * @return Repositorio de Medicos
     */
    public RepositorioMedico getRepositorio() {
        return repositorio;
    }
    
    public ArrayList<Medico> getMedicos() {
        return repositorio.getArray();
    }
    
}
