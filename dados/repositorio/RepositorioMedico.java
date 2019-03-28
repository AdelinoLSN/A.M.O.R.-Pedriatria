package dados.repositorio;

import dados.arquivo.ArquivoMedico;
import java.util.ArrayList;
import negocio.entidade.Medico;

public class RepositorioMedico implements IRepositorio{
    private ArrayList<Medico> array;

    public RepositorioMedico() {
        this.array = ArquivoMedico.ler();
    }
    
    
    @Override
    public void adicionar(Object item){
        array.add((Medico)item);
    }

    @Override
    public void remover(Object item) {
        array.remove((Medico)item);
    }

    @Override
    public Object buscar(String crm) {
        for(Medico m: array){
            if(m.getCrm().equals(crm)){
                return m;
            }
        }
        return null;
    }
    
    @Override
    public void atualizar(Object item, String code) {
    	Object atual = this.buscar(code);
        int indice = array.indexOf(code);
        if(indice != -1)
            array.set(indice,((Medico)item));
    }

    @Override
    public String listar() {
        String listagem = "";
        for(Medico p: array){
            listagem += p.toString()+"\n";
        }
        return listagem;
    }

    @Override
    public boolean existe(String crm) {
        for(Medico m: array){
            if(m.getCrm().equals(crm)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ArrayList<Medico> getArray(){
    	return this.array;
    }
}
