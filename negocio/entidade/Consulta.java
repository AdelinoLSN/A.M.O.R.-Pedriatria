package negocio.entidade;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe para representar as consultas, implementa a interface Comparable para
 * que seja possivel comparar ela com outras consultas através das datas, e Serializable
 * para ser possivel salvar seus objetos
 * @author Usuário
 */
public class Consulta implements Comparable, Serializable{
    // Atributos de consulta
    private Medico medico;
    private Paciente paciente;
    private Date data;
    private Laudo laudo;
    
    /** Conta quantos objetos de consulta foram
    criados para assim gerar um codigo especifico para cada um */
    private static int contador = 0; 
    private int codigo;

    /**
     * Construtor unico de consulta.
     * @param medico Medico que ira' atender a consulta
     * @param paciente Paciente que ira' ser atentido
     * @param data Data da consulta
     */
    public Consulta(Medico medico, Paciente paciente, Date data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.laudo = null;
        this.codigo = contador;
        contador++;
    }

    // Getters e setters
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico m){
    	this.medico = m;
    }
    public int getCodigo(){
        return codigo;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data){
    	this.data = data;
    }
    public Laudo getLaudo() {
        return laudo;
    }
    public void setLaudo(Laudo laudo){
    	this.laudo = laudo;
    }
    
    /**
     * Funcao que cria um objeto da classe laudo e que chama 
     * a funcao de emitir laudo(toString() de laudo).
     * @param altura Altura do paciente ao ser atendida
     * @param parecer Parecer do medico sobre o estado do paciente
    */
    public String criarLaudo(double altura, double peso, String parecer) {
        Laudo l = new Laudo(this.medico, altura, this.paciente, this.data, parecer);
        this.laudo = l;
        String laudo = l.toString();
        return laudo;
    }
    
    /**
     * Funcao toString da classe Consulta
     * @return Algumas informacoes importantes sobre a consulta
     */
    @Override
    public String toString() {
        return "Consulta número: " + codigo + " - Médico: " + this.getMedico().getNome() + " - Paciente: " +
                            this.getPaciente().getNome() + " - Data: " + this.getData();
    }
    
    /**
     * Metodo que implementa o compareTo da interface Comparable para que assim
     * se possa ordenar o array(repositorio) de Consulta pela data para qual as 
     * consultas foram marcadas
     * @param obj objeto parametro para comparacao
     */
    @Override
    public int compareTo(Object obj){
        Consulta c = (Consulta) obj;
        if(this.getData().compareTo(c.getData()) == 1) return 1;
        else if(this.getData().compareTo(c.getData()) == -1)return -1;
        else return 0;
    }
	
}