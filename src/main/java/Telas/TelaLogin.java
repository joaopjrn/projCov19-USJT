/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Modelos.Usuario;
import Modelos.Atendente;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Cursor;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

/**
 *
 * @author W10 VM
 */
public class TelaLogin extends javax.swing.JFrame {

  /**
   * Creates new form TelaLogin
   */
  public TelaLogin() {
    initComponents();
    this.setLocationRelativeTo(null);
//    login();
  }
  
  private void login() {
    new Thread(() -> {
      toggleInput();
      Usuario u = new Usuario(campoLogin.getText(), new String(campoSenha.getPassword()));
      Object login = u.login();
      if (login instanceof Integer || login == null) {
        System.out.println("erro");
      } else if (login instanceof Atendente) {
        Atendente usuarioLogado = (Atendente) login;

        java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
            new TelaPrincipal(usuarioLogado, tl).setVisible(true);
          }
        });
        setVisible(false);
      }
      toggleInput();
    }).start();
  }
  
  public void toggleInput(){
    toggleCursor();
    campoLogin.setEnabled(!campoLogin.isEnabled());
    campoSenha.setEnabled(!campoSenha.isEnabled());
    btnLogin.setEnabled(!btnLogin.isEnabled());
    btnLimpaLogin.setEnabled(!btnLimpaLogin.isEnabled());
    btnLimpaSenha.setEnabled(!btnLimpaSenha.isEnabled());
  }
  
  public void toggleCursor() {
    if (getCursor().getType() == Cursor.WAIT_CURSOR) {
      tl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    } else {
      tl.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }
  }
  
  public void limparCampos() {
    tl.getCampoLogin().setValue("");
    tl.getCampoSenha().setText("");
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    pnPrincipal = new javax.swing.JPanel();
    txtLogo = new javax.swing.JLabel();
    txtDigiteCpf = new javax.swing.JLabel();
    campoLogin = new javax.swing.JFormattedTextField();
    txtDigiteSenha = new javax.swing.JLabel();
    campoSenha = new javax.swing.JPasswordField();
    btnLogin = new javax.swing.JButton();
    btnLimpaLogin = new javax.swing.JButton();
    btnLimpaSenha = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Portal Vacina");
    setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icons8_virus_16px.png")).getImage());
    setResizable(false);

    txtLogo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
    txtLogo.setForeground(new java.awt.Color(67, 177, 177));
    txtLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    txtLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_male_user_96px_2.png"))); // NOI18N
    txtLogo.setText("Entrar");

    txtDigiteCpf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    txtDigiteCpf.setText("Digite seu CPF");

    try {
      campoLogin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
    } catch (java.text.ParseException ex) {
      ex.printStackTrace();
    }
    campoLogin.setToolTipText("");

    txtDigiteSenha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    txtDigiteSenha.setText("Digite sua Senha");

    btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    btnLogin.setText("Login");
    btnLogin.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnLoginActionPerformed(evt);
      }
    });

    btnLimpaLogin.setBackground(new java.awt.Color(255, 151, 151));
    btnLimpaLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    btnLimpaLogin.setText("X");
    btnLimpaLogin.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnLimpaLoginActionPerformed(evt);
      }
    });

    btnLimpaSenha.setBackground(new java.awt.Color(255, 151, 151));
    btnLimpaSenha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    btnLimpaSenha.setText("X");
    btnLimpaSenha.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnLimpaSenhaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout pnPrincipalLayout = new javax.swing.GroupLayout(pnPrincipal);
    pnPrincipal.setLayout(pnPrincipalLayout);
    pnPrincipalLayout.setHorizontalGroup(
      pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnPrincipalLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(pnPrincipalLayout.createSequentialGroup()
            .addComponent(txtLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPrincipalLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(txtDigiteSenha)
              .addGroup(pnPrincipalLayout.createSequentialGroup()
                .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(txtDigiteCpf)
                  .addComponent(campoSenha)
                  .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(btnLimpaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                  .addComponent(btnLimpaLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGap(75, 75, 75))))
      .addGroup(pnPrincipalLayout.createSequentialGroup()
        .addGap(96, 96, 96)
        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 96, Short.MAX_VALUE))
    );
    pnPrincipalLayout.setVerticalGroup(
      pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPrincipalLayout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addComponent(txtLogo)
        .addGap(20, 20, 20)
        .addComponent(txtDigiteCpf)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnLimpaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(27, 27, 27)
        .addComponent(txtDigiteSenha)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(btnLimpaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
          .addComponent(campoSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(20, Short.MAX_VALUE))
    );

    getContentPane().add(pnPrincipal, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
    login();
  }//GEN-LAST:event_btnLoginActionPerformed

  private void btnLimpaLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaLoginActionPerformed
    campoLogin.setValue(null);
  }//GEN-LAST:event_btnLimpaLoginActionPerformed

  private void btnLimpaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaSenhaActionPerformed
    campoSenha.setText("");
  }//GEN-LAST:event_btnLimpaSenhaActionPerformed

  public JFormattedTextField getCampoLogin() {
    return campoLogin;
  }

  public JPasswordField getCampoSenha() {
    return campoSenha;
  }
  
  /**
   * @param args the command line arguments
   */
//  public static void main(String args[]) {
//    /* Set the Nimbus look and feel */
//    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//     */
//    //</editor-fold>
//
//    FlatLightLaf.install();
//    /* Create and display the form */
//    java.awt.EventQueue.invokeLater(new Runnable() {
//      public void run() {
//        new TelaLogin().setVisible(true);
//      }
//    });
//  }
  
  private TelaLogin tl = (TelaLogin) this;
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnLimpaLogin;
  private javax.swing.JButton btnLimpaSenha;
  private javax.swing.JButton btnLogin;
  private javax.swing.JFormattedTextField campoLogin;
  private javax.swing.JPasswordField campoSenha;
  private javax.swing.JPanel pnPrincipal;
  private javax.swing.JLabel txtDigiteCpf;
  private javax.swing.JLabel txtDigiteSenha;
  private javax.swing.JLabel txtLogo;
  // End of variables declaration//GEN-END:variables
}
