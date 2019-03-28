package dados.repositorio;

import dados.arquivo.ArquivoConsulta;
import negocio.entidade.Consulta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import negocio.entidade.Medico;
import negocio.entidade.Paciente;

public class RepositorioConsulta implements IRepositorio{
    private ArrayList<Consulta> array;

    public RepositorioConsulta() {
        this.array = ArquivoConsulta.ler();
    }
    
    /**
     * Alem de adicionar uma consulta no repositorio, esse metodo chama um
     * metodo da classe Collections para ordenar o array a cada elemento
     * adicionado a ele. A ordenacao e' feita pela data da consulta.
     * @param item Consulta a ser adicionada no vetor
     */
    @Override
    public void adicionar(Object item){
        array.add((Consulta)item);
        Collections.sort(array); // Efetua a ordenacao do vetor
    }
    

    @Override
    public void remover(Object item) {
        array.remove((Consulta)item);
    }

    @Override
    public Object buscar(String codConsulta) {
        for(Consulta c: array){
            if(c.getCodigo() == Integer.parseInt(codConsulta)){
                return c;
            }
        }
        return null;
    }

    
    @Override
    public void atualizar(Object item, String cod) {
    	Object atual = this.buscar(cod);
        int indice = array.indexOf(atual);
        if(indice != -1)
            array.set(indice, ((Consulta)item));
    }

    @Override
    public String listar() {
        String listagem = "";
        for(Consulta c: array){
            listagem += c.toString()+"\n";
        }
        return listagem;
    }

    @Override
    public boolean existe(String codConsulta) {
        for(Consulta c: array){
            if(c.getCodigo() == Integer.parseInt(codConsulta)){
                return true;
            }
        }
        return false;
    }
    
    
    /** 
    * Esse método servira para buscar consultas agendadas para 
    * um paciente passado como parâmetro.
    * @param pac o paciente para qual a consulta foi marcadar
    * @return a primeira consulta marcada para o paciente ou
    * null para indicar que nao ha consulta marcada para o paciente dado
    */
    public Consulta buscar_Paciente(Paciente pac){
        for(Consulta i: array){
            if(i.getPaciente().equals(pac)){
                return i;
            }
        }
        return null;
    }
    
    /** 
    * Esse método servira para buscar consultas agendadas para serem feitas 
    * por um medico passado como parâmetro.
    * @param med medico que ira atender a consulta
    * @return a primeira consulta marcada para o medico ou
    * null para indicar que nao ha consulta marcada para o paciente dado
    */
    public Consulta buscar_Medico(Medico med){
        for(Consulta i: array){
            if(i.getMedico().equals(med)){
                return i;
            }
        }
        return null;
    }


    /** 
    * Esse método servira para ver se o médico já tem o limite de consultas 
    * para o dia vingente excedido, dados o CRM do médico e a data.
    * @param crm CRM do médico ao qual buscamos.
    * @param data Data ao qual queremos saber se terá disponibilidade.
    * @return boolean que diz se ele já tem o limite excedido ou não.
    */

    
    public boolean ConsultarLimiteConsultasMedicoDia(String crm, Date data){
    	int contador = 0;
    	for(Consulta i: array){
    		if(i.getMedico().getCrm().equals(crm)){
    			if(i.getData().compareTo(data)==0){
    				contador ++;
    			}
    		}
    	}
        return contador == 5;
    }
    
    /** 
    */
    public String buscarConsultasMedico(String crm){
    	String consultas = "";
        for(Consulta i: array){
            if(i.getMedico().getCrm().equals(crm)){
            	consultas = consultas+i.toString()+"\n";
            }
        }
        return consultas;
    }
    
    @Override
    public ArrayList<Consulta> getArray(){
    	return this.array;
    }
}
