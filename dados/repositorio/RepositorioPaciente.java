package dados.repositorio;

import dados.arquivo.ArquivoPaciente;
import negocio.entidade.Paciente;
import java.util.ArrayList;

public class RepositorioPaciente implements IRepositorio{
    private ArrayList<Paciente> array;

    public RepositorioPaciente() {
        this.array = ArquivoPaciente.ler();
    }
    
    @Override
    public void adicionar(Object item){
        array.add((Paciente)item);
    }

    @Override
    public void remover(Object item) {
        array.remove((Paciente)item);
    }

    @Override
    public Object buscar(String cpf) {
        for(Paciente p: array){
            if(p.getCpf().equals(cpf)){
                return p;
            }
        }
        return null;
    }
    
    @Override
    public void atualizar(Object item, String code) {
    	Object atual = this.buscar(code);
        int indice = array.indexOf(atual);
        if(indice != -1)
            array.set(indice,((Paciente)item));
    }

    @Override
    public String listar() {
        String listagem = "";
        for(Paciente p: array){
            listagem += p.toString()+"\n";
        }
        return listagem;
    }

    @Override
    public boolean existe(String cpf) {
        for(Paciente p: array){
            if(p.getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ArrayList<Paciente> getArray() {
        return array;
    }
}
