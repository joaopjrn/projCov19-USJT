/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BD.UsuarioDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author W10 VM
 */
public class Usuario {
  private String login, senha;

  public Usuario(String login, String senha) {
    this.login = login;
    this.senha = senha;
  }
  
  public Object login() {
    if (senha.equals("") || login.contains(" ")) {
      JOptionPane.showMessageDialog(null, "Verifique os campos de acesso", "Erro", JOptionPane.ERROR_MESSAGE);
      return 1;
    }
    
    Object[] a = UsuarioDAO.login(this);
    
    if (a == null) {
      JOptionPane.showMessageDialog(null, "Usuário ou Senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
      return 2;
    }
    
    String cpf = (String) a[0];
    String nome = (String) a[1];
    boolean isAdmin = (boolean) a[2];

    if (isAdmin) {
      return new Administrador(nome, cpf);
    } else if (!isAdmin) {
      return new Atendente(nome, cpf);
    }
    return null;
  }

  public String getLogin() {
    return login;
  }

  public String getSenha() {
    return senha;
  }  
  
}
