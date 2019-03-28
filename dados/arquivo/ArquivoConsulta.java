package dados.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import negocio.entidade.Consulta;

public class ArquivoConsulta {

    private static String caminho = "src\\dados\\arquivo\\bd_consultas.txt";
    
    /**
     * Método para gravar os objetos no arquivo .txt especificado na variavel da classe caminho
     * @param array Recebe array de objetos que serão gravados no arquivo
     */
    public static void gravar(ArrayList<Consulta> array) {
        try {
            File file = new File(caminho);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(caminho);
            ObjectOutputStream writer = new ObjectOutputStream(out); //Abre o editor
            writer.writeObject(array); //Escreve o arrayList no arquivo
            writer.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    /**
     * Método para ler os objetos do arquivo .txt especificado na variavel da classe caminho
     * @return Um array com objetos lidos
     */
    public static ArrayList<Consulta> ler(){
        ArrayList<Consulta> listaConsultas = new ArrayList();
        try{
            File file = new File(caminho);
            if(!file.exists()){
                file.createNewFile();
            }
            FileInputStream reader = new FileInputStream(caminho); //Abre o leitor do arquivo criado
            ObjectInputStream in = new ObjectInputStream(reader); //Lê o arquivo
            listaConsultas = (ArrayList<Consulta>) in.readObject(); //Coloca os dados encontrados no arquivo em um ArrayList
            in.close();
        }catch(IOException | ClassNotFoundException e){
            e.getMessage();
        }
        return listaConsultas; //Retorna o arrayList com todos os Objetos do tipo Consulta que estavam no arquivo
    }
}
