package usuario.principal;

import fachada.ServidorClinica;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import negocio.autenticacao.Autentica;

public class JanelaLogin extends JDialog{
    
    private JTextField campoLogin  = new JTextField(10);
    private JPasswordField campoSenha = new JPasswordField(10);;
    private JButton botaoEntrar;
    private JButton botaoCancelar;
    private JLabel lblLogin = new JLabel("Login:");
    private JLabel lblSenha = new JLabel("Senha:");
    private Object usuario;
    private ServidorClinica fachada;
    
    public JanelaLogin(Frame owner, String title, boolean modal, ServidorClinica fachada){
        super(owner, title, modal);
        this.fachada = fachada;
        Container tela = getContentPane();
        BorderLayout layout = new BorderLayout();
        tela.setLayout(layout);
        JPanel superior = new JPanel();
        superior.setLayout(new GridLayout(2, 2));
        superior.add(lblLogin);
        superior.add(campoLogin);
        superior.add(lblSenha);
        superior.add(campoSenha);
        JPanel superior2 = new JPanel();
        String titulo = "Digite o Login e Senha para continuar";
        Border etched = BorderFactory.createEtchedBorder();
        Border borda = BorderFactory.createTitledBorder(etched, titulo);
        superior2.setBorder(borda);
        superior2.setLayout(new FlowLayout(FlowLayout.LEFT));
        superior2.add(superior);
        Tratador trat = new Tratador();
        botaoEntrar = new JButton("Entrar");
        botaoEntrar.addActionListener(trat);
        getRootPane().setDefaultButton(botaoEntrar);
        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(trat);
        JPanel inferior = new JPanel();
        inferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
        inferior.add(botaoEntrar);
        inferior.add(botaoCancelar);
        tela.add(BorderLayout.NORTH, superior2);
        tela.add(BorderLayout.SOUTH, inferior);
        setSize(280, 170);
        setMinimumSize(getSize());
        setMaximumSize(getSize());  
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    /**
     * @return the campoLogin
     */
    public JTextField getCampoLogin() {
        return campoLogin;
    }

    /**
     * @return the campoSenha
     */
    public JPasswordField getCampoSenha() {
        return campoSenha;
    }

    /**
     * @return the botaoEntrar
     */
    public JButton getBotaoEntrar() {
        return botaoEntrar;
    }

    /**
     * @return the botaoCancelar
     */
    public JButton getBotaoCancelar() {
        return botaoCancelar;
    }

    public Object getUsuario() {
        return usuario;
    }
    
    private class Tratador implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Object resul; // Recebe o resultado da verificação do login e senha
            String senha = new String(getCampoSenha().getPassword());
            if(e.getSource() == getBotaoEntrar()){
                if (senha.trim().isEmpty() || getCampoLogin().getText() == null
                        || getCampoLogin().getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Por favor, preencha todos os campos!","Atenção",ERROR_MESSAGE);
                }else{
                    Autentica app = new Autentica(getCampoLogin().getText(), senha, fachada);
                    resul = app.verificar();
                    if(resul == null){
                        JOptionPane.showMessageDialog(null,"Dados incorretos!","Erro de autenticação",ERROR_MESSAGE);
                        getCampoLogin().requestFocus();
                    }else{
                        usuario = resul;
                        hide(); // Sai da janela de LOGIN
                    }
                }
            }else
                System.exit(0);
        }
    }
}
