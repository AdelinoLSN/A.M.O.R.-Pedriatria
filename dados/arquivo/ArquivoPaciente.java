package dados.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import negocio.entidade.Paciente;

public class ArquivoPaciente {
    private static String caminho = "src\\dados\\arquivo\\bd_pacientes.txt";
    
    /**
     * Método para gravar os objetos no arquivo .txt especificado na variavel da classe caminho
     * @param array Recebe array de objetos que serão gravados no arquivo
     */
    public static void gravar(ArrayList<Paciente> array) {
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
            e.printStackTrace();
        }
    }
    
    /**
     * Método para ler os objetos do arquivo .txt especificado na variavel da classe caminho
     * @return Um array com objetos lidos
     */
    public static ArrayList<Paciente> ler(){
        ArrayList<Paciente> listaPacientes = new ArrayList();
        try{
            File file = new File(caminho);
            if(!file.exists()){
                file.createNewFile();
            }
            FileInputStream reader = new FileInputStream(caminho); //Abre o leitor do arquivo criado
            ObjectInputStream in = new ObjectInputStream(reader); //Lê o arquivo
            listaPacientes = (ArrayList<Paciente>) in.readObject(); //Coloca os dados encontrados no arquivo em um ArrayList
            in.close();
        }catch(IOException | ClassNotFoundException e){
            e.getMessage();
        }
        return listaPacientes; //Retorna o arrayList com todos os Objetos do tipo Paciente que estavam no arquivo
    }
}
