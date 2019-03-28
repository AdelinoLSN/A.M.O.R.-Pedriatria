package dados.repositorio;

import dados.arquivo.ArquivoFuncionario;
import java.util.ArrayList;
import negocio.entidade.Funcionario;

public class RepositorioFuncionario implements IRepositorio{
    private ArrayList<Funcionario> array;
    private static int contador;

    public RepositorioFuncionario() {
        this.array = ArquivoFuncionario.ler();
        if (array.isEmpty()){
            contador = 0;
        }else
            contador = array.get(array.size()-1).getId();
    }

    public static int getContador() {
        return contador;
    }
    
    @Override
    public void adicionar(Object item){
        array.add((Funcionario)item);
        contador++;
    }

    @Override
    public void remover(Object item) {
        array.remove((Funcionario)item);
    }

    @Override
    public Object buscar(String id) {
        for(Funcionario f: array){
            if(f.getId() == Integer.parseInt(id)){
                return f;
            }
        }
        return null;
    }
    
    @Override
    public void atualizar(Object item, String cod) {
    	Object atual = this.buscar(cod);
        int indice = array.indexOf(atual);
        if(indice != -1)
            array.set(indice,((Funcionario)item));
    }

    @Override
    public String listar() {
        String listagem = "";
        for(Funcionario p: array){
            listagem += p.toString()+"\n";
        }
        return listagem;
    }

    @Override
    public boolean existe(String id) {
        for(Funcionario f: array){
            if(f.getId() == Integer.parseInt(id)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ArrayList<Funcionario> getArray() {
        return array;
    }
    
}
