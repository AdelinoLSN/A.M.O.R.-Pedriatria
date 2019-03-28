package main;

import fachada.ServidorClinica;
import usuario.principal.JanelaPrincipal;

/**
 *
 * @author Usuário
 */
public class Main {
    public static void main(String args[]){
        ServidorClinica fachada = new ServidorClinica();
        JanelaPrincipal janela = new JanelaPrincipal(fachada);
        janela.iniciar();
    }
}