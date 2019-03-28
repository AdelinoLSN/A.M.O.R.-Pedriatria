package negocio.cadastro.consulta;

import negocio.excecao.consulta.ConsultaInexistenteException;
import negocio.excecao.consulta.ConsultasExcedidasMedicoException;
import negocio.excecao.consulta.DataInvalidaException;
import negocio.entidade.*;
import dados.repositorio.RepositorioConsulta;
import dados.repositorio.RepositorioMedico;
import dados.repositorio.RepositorioPaciente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import negocio.excecao.consulta.MedicoSemConsultasExceptions;
import negocio.excecao.medico.MedicoInexistenteException;
import negocio.excecao.paciente.PacienteInexistenteException;

public class AgendaConsulta {
    
    private RepositorioConsulta repositorio;
    
    /**
     * Construtor de AgendaConsulta
     * @param repositorio Repositorio onde estâo e serâo armazenadas as Consultas
     */
    public AgendaConsulta(){
        this.repositorio = new RepositorioConsulta();
    }
    
    /**
     * Esse método adiciona uma consulta ao repositorio de Consultas
     * @param c Objeto Consulta a ser adicionado
     * @param medicos Repositorio de medicos recebido para verifica se o medico da
     * consulta é válido
     * @param pacientes Repositorio de paciente para verificar o paciente de Consulta
     * @throws PacienteInexistenteException Caso o paciente passado da Consulta seja 
     * inválido
     * @throws MedicoInexistenteException Caso o medico da consulta seja inválido
     * @throws DataInvalidaException Caso a data da consulta seja inválida
     * @throws ConsultasExcedidasMedicoException caso o médico tenha mais de 5 consultas agendadas
     * @throws ParseException Caso o objeto não seja uma String no formato correto
     */
    public void adicionar(Consulta c, RepositorioMedico medicos, RepositorioPaciente pacientes)
            throws PacienteInexistenteException, MedicoInexistenteException, DataInvalidaException, ConsultasExcedidasMedicoException, ParseException{
        Date hoje = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hojeS = sdf.format(hoje);
        hoje = sdf.parse(hojeS);
        if(medicos.existe(c.getMedico().getCrm())){
            if(pacientes.existe(c.getPaciente().getCpf())){
                if(c.getData().compareTo(hoje) > 0){
                	if(repositorio.ConsultarLimiteConsultasMedicoDia(c.getMedico().getCrm(), c.getData()) == false){
                		repositorio.adicionar(c);
                	}else{
                		throw new ConsultasExcedidasMedicoException();
                	}
                }else{
                    throw new DataInvalidaException();
                }
            }else{
                throw new PacienteInexistenteException();
            }
        }else{
            throw new MedicoInexistenteException();
        }
    }
    /**
     * Metodo que faz a remocao de uma consulta baseada no parametro
     * @param cod identificacao da consulta
     * @throws ConsultaInexistenteException caso a consulta consultada nao exista
     */
    public void remover(String cod) throws ConsultaInexistenteException {
        Consulta consul = (Consulta) repositorio.buscar(cod);
        if(consul != null) repositorio.remover(cod); 
        else throw new ConsultaInexistenteException();
    }
    
    /**
     * Buscar uma consulta por cod
     * @param cod Codigo da Consulta buscada
     * @return o objeto Consulta encontrado
     * @throws ConsultaInexistenteException Caso nenhuma Consulta seja encontrada 
     */
    public Consulta consultarCod(String cod) throws ConsultaInexistenteException {
        if(repositorio.existe(cod)) throw new ConsultaInexistenteException();
        else return (Consulta) repositorio.buscar(cod);
    }
    
    /**
     * Esse método serve para se obter todas as consultas de um determidado médico
     * @param m Objeto Medico
     * @return Array de Consultas que foram marcadas para o medico passado por
     * parâmetro
     * @throws MedicoSemConsultasExceptions Para o caso de não haver consultas para o medico
     */
    public ArrayList<Consulta> consultarMed(Medico m) throws MedicoSemConsultasExceptions {
        ArrayList<Consulta> consultas =  new ArrayList<>();
        for(Consulta c: repositorio.getArray()){
            if (c.getMedico().equals(m)){
                consultas.add(c);
            }
        }
        if (consultas.isEmpty()){
            throw new MedicoSemConsultasExceptions();
        }
        return consultas;
    }
    
    /**
     * Esse método atualiza dados de uma consulta. Também pode ser referido como
     * remarcar consulta já que ele recebe uma consulta com a data por vim.
     * @param cod Codigo da consulta a ser atualizada
     * @param c Objeto Consulta com os dados atualizados
     * @param medicos Repositorio de Medicos cadastrados até o momento
     * @param pacientes Repositorio de Pacientes cadastrados até o momento
     * @throws ConsultaInexistenteException Caso o cod da consulta seja inválido
     * @throws PacienteInexistenteException Caso o paciente nao exista no banco de dados
     * @throws MedicoInexistenteException Caso o medico nao exista no banco de dados
     * @throws DataInvalidaException Caso a data esteja atrasada
     * @throws ParseException Caso a data não seja passada como String no formato correto
     */
    public void atualizar(String cod, Consulta c, RepositorioMedico medicos,
            RepositorioPaciente pacientes) throws ConsultaInexistenteException, 
            PacienteInexistenteException, MedicoInexistenteException, DataInvalidaException, ParseException{
        if(repositorio.existe(cod)){ 
        	Date hoje = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String hojeS = sdf.format(hoje);
            hoje = sdf.parse(hojeS);
            if(medicos.existe(c.getMedico().getCrm())){
                if(pacientes.existe(c.getPaciente().getCpf())){
                    if(c.getData().compareTo(hoje) > 0){
                    	String codigo = Integer.toString(c.getCodigo()); 
                        repositorio.atualizar(c, codigo); 
                    }else{
                        throw new DataInvalidaException();
                    }
                }else{
                    throw new PacienteInexistenteException();
                }
            }else{
                throw new MedicoInexistenteException();
            }
        }
        else throw new ConsultaInexistenteException();
    }    
    
    /**
     * Esse metodo faz a busca pela proxima consulta de um determinado paciente
     * @param CPF identificador do paciente
     * @param rep banco de dados do sistema onde estao as consultas
     * @return retorna uma String com as informacoes da pr�xima consulta marcada para esse paciente
     */
    public String proxConsultaPaciente(String cpf, ArrayList<Paciente> rep){
        for(Paciente pac: rep){
            if(pac.getCpf().equals(cpf)){
                Paciente p = pac;
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String data = sdf.format(d);
                Consulta consulta = repositorio.buscar_Paciente(p);
                String cons = ""+consulta.getMedico().getNome()+consulta.getPaciente().getNome()+data;
                return cons;
            }
        }
        return "Nenhuma consulta encontrada!";
    }
    
    /**
     * Essa funcao busca todas as consultas cadastradas
     * @return retona um string com todas as consultas agendadas no sistema
     */
    public String listarConsultas(){
    	String consultas = repositorio.listar();
    	return consultas;
    }

    /**
     * Metodo para buscar um repositorio
     * @return Repositorio de Consultas
     */
    public ArrayList<Consulta> getConsultas() {
        return repositorio.getArray();
    }
    
}