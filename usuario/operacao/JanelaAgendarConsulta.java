/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.operacao;

import fachada.ServidorClinica;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import negocio.entidade.Medico;
import negocio.entidade.Paciente;
import negocio.excecao.consulta.ConsultasExcedidasMedicoException;
import negocio.excecao.consulta.DataInvalidaException;
import negocio.excecao.medico.MedicoInexistenteException;
import negocio.excecao.paciente.PacienteInexistenteException;

/**
 *
 * @author Usuário
 */
public class JanelaAgendarConsulta extends javax.swing.JDialog {
    
    private ServidorClinica fachada;
    private String crmMedico;
    
    /**
     * Creates new form JanelaAgendarConsulta
     */
    public JanelaAgendarConsulta(ServidorClinica fachada) {
        this.fachada = fachada;
        setModal(true);
        initComponents();
        setResizable(false);
        preencheComboBox();
    }
       
    private void preencheComboBox(){
        ArrayList<String> ops = new ArrayList<>();
        String linha;
        int cont = 1;
        for(Medico m: fachada.getMedicos()){
            linha = "CRM: "+m.getCrm()+" - Nome: "+m.getNome();
            ops.add(linha);
            cont++;
        }
        List opcoes = ops;
        this.comboBoxMedico.setModel(new DefaultComboBoxModel(opcoes.toArray()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboBoxMedico = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        campoCPF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoData = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();
        botaoAgendar = new javax.swing.JButton();
        NomePaciente = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agendamento de Consulta");
        jLabel1.setToolTipText("");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Selecione o médico:");

        comboBoxMedico.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMedicoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("CPF do responsável:");

        campoCPF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        campoCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCPFActionPerformed(evt);
            }
        });
        campoCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoCPFFocusLost(evt);
            }
        });
        campoCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCPFKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoCPFKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Data para a consulta:");

        campoData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        campoData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoData.setText("dd/mm/aaaa");
        campoData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataActionPerformed(evt);
            }
        });
        campoData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoDataFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoDataFocusLost(evt);
            }
        });
        campoData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDataKeyTyped(evt);
            }
        });

        botaoCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoAgendar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoAgendar.setText("Agendar");
        botaoAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAgendarActionPerformed(evt);
            }
        });

        NomePaciente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NomePaciente.setText("-----------------------------------------------------------------");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxMedico, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(botaoAgendar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NomePaciente))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NomePaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAgendar)
                    .addComponent(botaoCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMedicoActionPerformed
        this.crmMedico = ((String)comboBoxMedico.getSelectedItem()).split(" ")[1];
    }//GEN-LAST:event_comboBoxMedicoActionPerformed

    private void botaoAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAgendarActionPerformed
        String cpf = campoCPF.getText();
        String data = campoData.getText();
        if (cpf.trim().isEmpty() || data.trim().isEmpty() || data.equals("dd/mm/aaaa") || data.length() < 10
                || cpf.length() < 14) {
            JOptionPane.showMessageDialog(null,"Por favor, preencha todos os campos!","Atenção",ERROR_MESSAGE);
        }else{
            try {
                fachada.agendarConsulta(crmMedico, cpf, data);
                JOptionPane.showMessageDialog(null,"Agendamento realizado com sucesso!","Resultado",INFORMATION_MESSAGE);
                dispose();
            } catch (MedicoInexistenteException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro no agendamento!",ERROR_MESSAGE);
            } catch (PacienteInexistenteException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro no agendamento!",ERROR_MESSAGE);
            } catch (DataInvalidaException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro no agendamento!",ERROR_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro no agendamento!",ERROR_MESSAGE);
            } catch (ConsultasExcedidasMedicoException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro no agendamento!",ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_botaoAgendarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void campoDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataKeyTyped
        int k = evt.getKeyChar();
        if((k > 47 && k < 58)) {
            if(campoData.getText().length() == 10){
                evt.setKeyChar((char)KeyEvent.VK_CLEAR);
            }else if(campoData.getText().length() == 5){
                campoData.setText(campoData.getText()+"/");
            }else if(campoData.getText().length() == 2){
                campoData.setText(campoData.getText()+"/");
            }
        } else {
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }
    }//GEN-LAST:event_campoDataKeyTyped

    private void campoCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCPFKeyTyped
        int k = evt.getKeyChar();
        if((k > 47 && k < 58)) {
            if(campoCPF.getText().length() == 14){
                evt.setKeyChar((char)KeyEvent.VK_CLEAR);
                campoData.requestFocus();
            }else if(campoCPF.getText().length() == 11){
                campoCPF.setText(campoCPF.getText()+"-");
            }else if(campoCPF.getText().length() == 7){
                campoCPF.setText(campoCPF.getText()+".");
            }else if(campoCPF.getText().length() == 3){
                campoCPF.setText(campoCPF.getText()+".");
            }             
        } else {
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }
    }//GEN-LAST:event_campoCPFKeyTyped

    private void campoDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataActionPerformed
        
    }//GEN-LAST:event_campoDataActionPerformed

    private void campoDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFocusGained
        campoData.setText("");
    }//GEN-LAST:event_campoDataFocusGained

    private void campoCPFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCPFKeyReleased
        // TODO add your handling code here:
        if (campoCPF.getText().length() != 13)
                NomePaciente.setText("-----------------------------------------------------------------"); 
    }//GEN-LAST:event_campoCPFKeyReleased

    private void campoDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFocusLost
        if (campoData.getText().length() < 10 || campoData.getText().isEmpty()){
            campoData.setText("dd/mm/aaaa");
        }
    }//GEN-LAST:event_campoDataFocusLost

    private void campoCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCPFFocusLost
        if(campoCPF.getText().length() < 13)
            NomePaciente.setText("CPF inválido");
        else if (campoCPF.getText().isEmpty())
            NomePaciente.setText("-- CPF não pode ser vazio --");
        else
            try {
                Paciente p = fachada.buscaPaciente(campoCPF.getText());
                NomePaciente.setText(p.getNome());
            } catch (PacienteInexistenteException ex) {
                NomePaciente.setText(ex.getMessage());
                campoCPF.setText(" ");
                campoCPF.requestFocus();
            }
    }//GEN-LAST:event_campoCPFFocusLost

    private void campoCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCPFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NomePaciente;
    private javax.swing.JButton botaoAgendar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JTextField campoData;
    private javax.swing.JComboBox comboBoxMedico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
