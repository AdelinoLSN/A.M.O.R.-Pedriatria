package fachada;

import dados.arquivo.ArquivoConsulta;
import dados.arquivo.ArquivoFuncionario;
import dados.arquivo.ArquivoMedico;
import dados.arquivo.ArquivoPaciente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import negocio.cadastro.consulta.AgendaConsulta;
import negocio.cadastro.paciente.CadastroPaciente;
import negocio.cadastro.funcionario.CadastroFuncionario;
import negocio.cadastro.medico.CadastroMedico;
import negocio.entidade.Funcionario;
import negocio.entidade.Medico;
import negocio.entidade.Consulta;
import negocio.entidade.Paciente;
import negocio.excecao.cadastro.LoginEmUsoException;
import negocio.excecao.consulta.ConsultaInexistenteException;
import negocio.excecao.consulta.ConsultasExcedidasMedicoException;
import negocio.excecao.consulta.DataInvalidaException;
import negocio.excecao.consulta.MedicoSemConsultasExceptions;
import negocio.excecao.funcionario.FuncionarioInexistenteException;
import negocio.excecao.funcionario.FuncionarioJaExisteException;
import negocio.excecao.medico.MedicoInexistenteException;
import negocio.excecao.medico.MedicoJaExisteException;
import negocio.excecao.paciente.PacienteInexistenteException;
import negocio.excecao.paciente.PacienteJaExisteException;

public class ServidorClinica {
    private AgendaConsulta agendaConsulta;
    private CadastroPaciente cadastroPaciente;
    private CadastroMedico cadastroMedico;
    private CadastroFuncionario cadastroFuncionario;
    
    public ServidorClinica(){
    	agendaConsulta = new AgendaConsulta();
    	cadastroPaciente = new CadastroPaciente();
    	cadastroMedico = new CadastroMedico();
    	cadastroFuncionario = new CadastroFuncionario();
    }
    
    /**
     * Esse Metodo tem o objetivo de agendar uma consulta adicionando-a ao repositorio.
     * @param crm identificacao do medico
     * @param cpf cpf do paciente que utilizaremos como identificador do mesmo
     * @param date data ao qual a consulta sera agendada
     * @throws MedicoInexistenteException caso o crm do medico nao esteja no banco de dados
     * @throws PacienteInexistenteException caso o paciente nao esteja cadastrado
     * @throws DataInvalidaException caso a data seja inferior ao dia atual
     * @throws ConsultasExcedidasMedicoException caso o medico nao tenha disponibilidade para tal dia
     * @throws ParseException ocorre quando a data entregue nao esta no formato adequado
     */
    public void agendarConsulta(String crm, String cpf, String date) throws MedicoInexistenteException, PacienteInexistenteException, DataInvalidaException, ConsultasExcedidasMedicoException, ParseException{
    	Medico medico = cadastroMedico.consultar(crm);
    	Paciente paciente = cadastroPaciente.consultar(cpf);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Date data = sdf.parse(date);
    	Consulta c = new Consulta(medico, paciente, data);
    	agendaConsulta.adicionar(c, cadastroMedico.getRepositorio(), cadastroPaciente.getRepositorio());
    }
    
    /**
     * Esse método faz o cadastro dos medicos adicionando-os ao banco de dados
     * @param nome nome do medico a ser cadastrado
     * @param cpf numero de cpf do medico a ser cadastrado
     * @param idade idade do medico a ser cadastrado
     * @param crm identificacao medica do medico a ser cadastrado
     * @param login identificacao de sistema do medico a ser cadastrado
     * @param senha senha para identifica��o do medico a ser cadastrado
     * @throws MedicoJaExisteException caso o medico ja esteja no banco de dados
     */
    public void cadastrarMedico(String crm, String login, String senha, String nome, String cpf, Date dataNascimento) throws MedicoJaExisteException, LoginEmUsoException{
    	Medico m = new Medico(crm, login, senha, nome, cpf, dataNascimento);
    	cadastroMedico.adicionar(m);
    }
    
    /**
     * Metodo que tem como objetivo cadastrar um funcionario no sistema
     * @param nome nome do funcionario
     * @param cpf numero de cpf do funcionario
     * @param idade idade do funcionario
     * @param login identificacao do funcionario
     * @param senha identifcacao do funcionario
     * @throws FuncionarioJaExisteException caso funcionario ja esteja cadastrado
     */
    public void cadastrarFuncionario(String login, String senha, String nome, String cpf, Date dataNascimento, int id) throws FuncionarioJaExisteException, LoginEmUsoException{
    	Funcionario func = new Funcionario(login, senha, nome, cpf, dataNascimento, id);
    	cadastroFuncionario.adicionar(func);
    }
    
    /**
     * Metodo para cadastro de pacientes no banco de dados
     * @param nome nome do paciente
     * @param cpf cpf do paciente
     * @param idade idade do paciente
     * @throws PacienteJaExisteException caso o paciente ja esteja em nosso banco de dados
     */
    public void cadastrarPaciente(String nome, String cpf, Date dataNascimento, String nomeResponsavel,
            String numeroContato, String cpfResponsavel, String sexo) throws PacienteJaExisteException{
    	Paciente p = new Paciente(nomeResponsavel, numeroContato, nome, cpf, sexo, dataNascimento);
    	cadastroPaciente.adicionar(p);
    }
    
    /**
     * Metodo para remocao de consultas
     * @param codigo identificador da consulta
     * @throws ConsultaInexistenteException caso a consulta n�o exista
     */
    public void removerConsulta(int codigo) throws ConsultaInexistenteException{
    	String cod = Integer.toString(codigo);
    	agendaConsulta.remover(cod);
    }
    
    /**
     * Metodo para remocao de um medico
     * @param crm identificador do medico
     * @throws MedicoInexistenteException caso o medico nao esteja cadastrado
     */
    public void removerMedico(String crm) throws MedicoInexistenteException{
    	cadastroMedico.remover(crm);
    }
    
    /**
     * Esse metodo faz a remocao de um funcionario dado um id
     * @param id identificador do funcionario
     * @throws FuncionarioInexistenteException caso o funcionario nao esteja em nosso sistema
     */
    public void removerFuncionario(String id) throws FuncionarioInexistenteException{
    	cadastroFuncionario.remover(id);
    }
    
    /**
     * Metodo para remocao de um paciente dado um cpf
     * @param cpf cpf do paciente que utilizaremos como identificador do mesmo
     * @throws PacienteInexistenteException caso o paciente nao existe em nosso sistema
     */
    public void removerPaciente(String cpf) throws PacienteInexistenteException{
    	cadastroPaciente.remover(cpf);
    }
    
    /**
     * Esse metodo tem o intuito de atualizar uma consulta
     * @param cod identificador da consulta para sabermos qual consulta queremos atualizar
     * @param crm identificador de novo medico caso seja necessario, ou de mesmo medico para manter
     * @param dat nova data da consulta
     * @throws ConsultaInexistenteException caso a consulta nao exista
     * @throws MedicoInexistenteException caso o medico nao exista em nos sistema
     * @throws DataInvalidaException caso a data seja anterior ao dia atual
     * @throws ParseException caso o formato da data esteja incoerente
     * @throws PacienteInexistenteException  caso o paciente nao exista
     */
    public void atualizarConsulta(String cod, String crm, String dat) throws ConsultaInexistenteException, ParseException, MedicoInexistenteException, PacienteInexistenteException, DataInvalidaException{
    	Consulta c = agendaConsulta.consultarCod(cod);
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Date temp = sdf.parse(dat);
    	Medico m = cadastroMedico.consultar(crm);
    	c.setData(temp);
    	c.setMedico(m);
    	agendaConsulta.atualizar(cod, c, cadastroMedico.getRepositorio(), cadastroPaciente.getRepositorio());
    }
    
    /**
     * Metodo para atualizar dados de medicos
     * @param crm identificador do medico que queremos alterar
     * @param login novo login caso seja necessario, ou antigo para mante-lo como esta
     * @param idade nova idade do medico caso seja necessario, ou antigo para mante-lo
     * @throws MedicoInexistenteException caso o medico nao conste em nosso sistema
     */
    public void atualizarMedico(String crm) throws MedicoInexistenteException{
    	Medico m = cadastroMedico.consultar(crm);
    	cadastroMedico.atualizar(crm, m);
    }
    
    /**
     * Metodo para atualizar dados de funcionarios
     * @param id identificador fixo do funcionario para identificar de quem queremos alterar
     * @param login novo login caso necessario, ou antigo para mante-lo
     * @param idade nova idade do funcionario caso necessario, ou antiga para mante-la
     * @throws FuncionarioInexistenteException caso o funcionario n�o esteja em nosso banco de dados
     */
    public void atualizarFuncionario(String id, Funcionario f) throws FuncionarioInexistenteException{
    	cadastroFuncionario.atualizar(id, f);
    }
    
    /**
     * Metodo para atualizacao de informacoes do paciente
     * @param cpf cpf do paciente que utilizaremos como identificador do mesmo
     * @param idade nova idade do paciente
     * @throws PacienteInexistenteException caso o paciente nao exista em nosso sistema
     */
    public void atualizarPaciente(String cpf, Paciente pac) throws PacienteInexistenteException{
    	cadastroPaciente.atualizar(cpf, pac);
    }
    
    /**
     * Metodo para emissao de laudo a partir de uma consulta
     * @param code identificador da consulta ao qual o laudo sera vinculado
     * @param altura altura do paciente no momento da consulta
     * @param peso peso do paciente no momento da consulta
     * @param parecer informacoes adicionais e diagnostico
     * @return retorna uma string para exibicao em tela
     * @throws ConsultaInexistenteException caso a consulta nao exista
     */
    public String emitirLaudo(String code, double altura, double peso, String parecer) throws ConsultaInexistenteException{
    	Consulta c = agendaConsulta.consultarCod(code);
    	String laudo = c.criarLaudo(altura, peso, parecer);
    	return laudo;
    }
    
    /**
     * Metodo para mostrar agenda de um medico dado o seu CRM
     * @param crm identificador do medico para com o sistema
     * @return uma string com todas as consultas marcadas para ele
     * @throws MedicoInexistenteException caso o medico procurado nao esteja cadastrado no sistema
     * @throws MedicoSemConsultasExceptions caso o medico nao tenha consultas agendadas
     */
    public String agendaMedico(String crm) throws MedicoInexistenteException, MedicoSemConsultasExceptions{
    	Medico m = cadastroMedico.consultar(crm);
    	ArrayList<Consulta> consultasDoMedico = agendaConsulta.consultarMed(m);
    	String retorno = "";
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	for(Consulta i: consultasDoMedico){
            Date data = i.getData();
            String dataS = sdf.format(data);
            retorno = retorno + i.getPaciente().getNome()+" - "+dataS+"\n";
    	}
    	return retorno;
    }
    
    /**
     * Metodo que retorna a proxima consulta de um dado paciente
     * @param cpf cpf do paciente que utilizaremos como identificador do mesmo
     * @return uma string com os dados da proxima consulta
     * @throws ConsultaInexistenteException caso nao haja consulta marcada para esse paciente
     */
    public String ProxConsultaPaciente(String cpf) throws ConsultaInexistenteException{
    	String consulta;
    	consulta = agendaConsulta.proxConsultaPaciente(cpf, cadastroPaciente.getPacientes());
    	return consulta;
    }
    
    /**
     * Busca um funcionario dada a uma informacao dele
     * @param id Informacao identificadora do usuario
     * @return o objeto encontrado
     * @throws FuncionarioInexistenteException Caso não seja encontrado o funcionario no sistema
     */
    public Funcionario buscaFuncionario(String id) throws FuncionarioInexistenteException{
        return cadastroFuncionario.consultar(id);
    }
    
    /**
     * Busca um funcionario dada a uma informacao dele
     * @param crm Informacao identificadora do usuario
     * @return o objeto encontrado
     * @throws MedicoInexistenteException Caso não seja encontrado o funcionario no sistema
     */
    public Medico buscaMedico(String crm) throws MedicoInexistenteException{
        return cadastroMedico.consultar(crm);
    }
    
    /**
     * Busca um funcionario dada a uma informacao dele
     * @param cpf Informacao identificadora do usuario
     * @return o objeto encontrado
     * @throws PacienteInexistenteException Caso não seja encontrado o funcionario no sistema
     */
    public Paciente buscaPaciente(String cpf) throws PacienteInexistenteException{
        return cadastroPaciente.consultar(cpf);
    }
    
    /**
     * Esse método deve ser chamado toda vez em que um usuario fizer um logout ou antes de encerrar o programa,
     * ele serve para salvar todos os objetos nos aquivos
     */
    public void sair(){
        ArquivoConsulta.gravar(this.getConsultas());
        ArquivoFuncionario.gravar(this.getFuncionarios());
        ArquivoMedico.gravar(this.getMedicos());
        ArquivoPaciente.gravar(this.getPaciente());
    }
    
    /**
     * Metodo que apenas funcionario pode ver e que lista todas as consultas que estao agendadas
     * no sistema (todos os medicos)
     * @return uma string com todas as consultas e suas informacoes
     */
    public String agendaConsultorio(){
    	return agendaConsulta.listarConsultas();
    }

    /**
     * Metodo para buscar o array de consultas
     * @return array do repositorio
     */
    public ArrayList<Consulta> getConsultas(){
        return agendaConsulta.getConsultas();
    }
    
    /**
     * Metodo para buscar o array de funcionarios
     * @return array do repositorio
     */
    public ArrayList<Funcionario> getFuncionarios(){
        return cadastroFuncionario.getFuncionarios();
    }
    
    /**
     * Metodo para buscar o array de medicos
     * @return array do repositorio
     */
    public ArrayList<Medico> getMedicos(){
        return cadastroMedico.getMedicos();
    }
    
    /**
     * Metodo para buscar o array de pacientes
     * @return array do repositorio
     */
    public ArrayList<Paciente> getPaciente(){
        return cadastroPaciente.getPacientes();
    }
}