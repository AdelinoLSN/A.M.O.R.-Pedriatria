package negocio.cadastro.paciente;

import negocio.entidade.Paciente;
import dados.repositorio.RepositorioPaciente;
import java.util.ArrayList;
import negocio.excecao.paciente.PacienteJaExisteException;
import negocio.excecao.paciente.PacienteInexistenteException;

public class CadastroPaciente {
    
    private RepositorioPaciente repositorio;

    public CadastroPaciente(){
        this.repositorio = new RepositorioPaciente();
    }
    /**
     * Metodo para adicionar um paciente ao repositorio
     * @param pac objeto do tipo Paciente a ser adicionado
     * @throws PacienteJaExisteException caso o paciente ja esteja no repositorio
     */
    public void adicionar(Paciente pac) throws PacienteJaExisteException{
        boolean existe = repositorio.existe(pac.getCpf());
        if(existe) throw new PacienteJaExisteException();
        else repositorio.adicionar(pac);
    }
    
    /**
     * Metodo para remocao de um paciente do sistema
     * @param cpf cpf do paciente que utilizaremos como identificador do mesmo
     * @throws PacienteInexistenteException caso paciente nao exista
     */
    public void remover(String cpf) throws PacienteInexistenteException {
        Paciente pac = (Paciente) repositorio.buscar(cpf);
        if(pac != null) repositorio.remover(pac);
        else throw new PacienteInexistenteException();
    }
    
    /**
     * Busca um paciente dado um id
     * @param cpf cpf do paciente que utilizaremos como identificador do mesmo
     * @return objeto do tipo Paciente que tenha o id requisitado
     * @throws PacienteInexistenteException
     */
    public Paciente consultar(String cpf) throws PacienteInexistenteException {
        if(!repositorio.existe(cpf)) throw new PacienteInexistenteException();
        else return (Paciente) repositorio.buscar(cpf);
    }
    
    /**
     * Metodo para atualizacao de um paciente
     * @param cpf cpf do paciente que utilizaremos como identificador do mesmo
     * @param pac objeto do tipo Paciente atualizado
     * @throws PacienteInexistenteException caso o nao haja paciente com esse id
     */
    public void atualizar(String cpf, Paciente pac) throws PacienteInexistenteException {
        if(repositorio.existe(cpf)) repositorio.atualizar(pac, cpf);
        else throw new PacienteInexistenteException();
    }    
    
    /**
     * Metodo para buscar um repositorio
     * @return Repositorio de Pacientes
     */
    public RepositorioPaciente getRepositorio() {
        return repositorio;
    }
    
    
    public ArrayList<Paciente> getPacientes() {
        return repositorio.getArray();
    }
}
