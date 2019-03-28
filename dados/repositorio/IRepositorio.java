package dados.repositorio;

import java.util.ArrayList;

public interface IRepositorio {
	/**
	 * Metodo para adicionar objeto ao repositorio
	 * @param item Objeto a ser adicionado
	 */
    void adicionar(Object item);
    /**
     * Metodo para remoção de objeto do repositorio
     * @param item Objeto a ser removido
     */
    void remover(Object item);
    /**
     * Metodo para atualizacao de objeto do repositorio
     * @param item Objeto atualizado
     * @param ident Identificador do objeto antigo
     */
    void atualizar(Object item, String ident);
    /**
     * Metodo para listar todo repositorio
     * @return String com todos os dados do repositorio
     */
    String listar();
    /**
     * Metodo para saber se ha objeto com esse identificador
     * @param ident Identificador do objeto
     * @return True, caso o objeto esteja no repositorio. False, caso não esteja
     */
    boolean existe(String ident);
    /**
     * Metodo para buscar um elemento pela identificacao
     * @param ident Identificador do objeto
     * @return Objeto que contenha esse identifcador
     */
    Object buscar(String ident);
    
    /**
     * Metodo para retornar um ArrayList
     * @return ArrayList pedido
     */
    ArrayList getArray();
}
