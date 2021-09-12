/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Telas.TelaLogin;
import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author joaop
 */

//Acesso Atendente
//CPF: 588.634.904-90
//SENHA: senha

//Acesso Administrador
//CPF: 123.137.356-30
//SENHA: senha

public class Main {
  public static void main(String args[]) {
    FlatLightLaf.install();
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new TelaLogin().setVisible(true);
      }
    });
  }
}
