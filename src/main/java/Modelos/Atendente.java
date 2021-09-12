/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BD.PacienteDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author W10 VM
 */
public class Atendente {

  private String nome, cpf, senha;

  public Atendente(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
  }
  
  public Atendente(String nome, String cpf, String senha) {
    this.nome = nome;
    this.cpf = cpf;
    this.senha = senha;
  }

  public Atendente(String cpf) {
    this.cpf = cpf;
  }
  

  public ArrayList verFila() {
    ArrayList<Paciente> fila = PacienteDAO.selectFila();
    Collections.sort(fila);
    if(fila != null){
      return fila;
    }
    return null;
  }

  public int confVac(Paciente p, Atendente a) {
    p.setDataVac(LocalDate.now());
    p.setVacPor(a.getCpf());
    p.setNomeVacPor(a.getNome());
    int cod = PacienteDAO.confVac(p);
    if(cod > 0){
      JOptionPane.showMessageDialog(null, "Paciente vacinado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      return cod;
    }
    return 0;
  }
  
  public static boolean isCPF(String CPF) {
    CPF = CPF.replace(".", "");
    CPF = CPF.replace("-", "");
    
    if (CPF.equals("00000000000")
            || CPF.equals("11111111111")
            || CPF.equals("22222222222") || CPF.equals("33333333333")
            || CPF.equals("44444444444") || CPF.equals("55555555555")
            || CPF.equals("66666666666") || CPF.equals("77777777777")
            || CPF.equals("88888888888") || CPF.equals("99999999999")
            || (CPF.length() != 11)) {
      return (false);
    }

    char dig10, dig11;
    int sm, i, r, num, peso;

    try {
      sm = 0;
      peso = 10;
      for (i = 0; i < 9; i++) {
        num = (int) (CPF.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11)) {
        dig10 = '0';
      } else {
        dig10 = (char) (r + 48);
      }

      sm = 0;
      peso = 11;
      for (i = 0; i < 10; i++) {
        num = (int) (CPF.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11)) {
        dig11 = '0';
      } else {
        dig11 = (char) (r + 48);
      }
      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
        return (true);
      } else {
        return (false);
      }
    } catch (InputMismatchException erro) {
      return (false);
    }
  }

  public void setLabelUsuario(JLabel labelNome) {
    if (this instanceof Administrador) {
      labelNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_manager_32px.png")));
    } else {
      labelNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_account_32px.png")));
    }
    labelNome.setText(this.nome);
  }
  
  public void gerarRelatorio() {
    erroDeAcesso();
  }
  
  public Endereco buscarCep(String cep){
    erroDeAcesso();
    return null;
  }
  
  public void erroDeAcesso() {
    JOptionPane.showMessageDialog(null, "Você não tem acesso a essa função!", "Erro de Acesso", JOptionPane.ERROR_MESSAGE);
  }
  
  // <editor-fold defaultstate="collapsed" desc="CRUD">   
  public Paciente buscarPaciente(String cpf){
    Paciente p = PacienteDAO.selectPaciente(cpf);
    if(p == null){
      JOptionPane.showMessageDialog(null, "Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }else{
      return p;
    }
  }
  
  public Paciente buscarParaVac(String cpf){
    Paciente p = PacienteDAO.selectPaciente(cpf);
    if(p == null){
      JOptionPane.showMessageDialog(null, "Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }else{
      return p;
    }
  }
  
  public Atendente buscarUsuario(String cpf){
    erroDeAcesso();
    return null;
  }
  
  public int cadUsuario(String cpf, String nome, String senha, String repSenha, boolean isAdmin) {
    erroDeAcesso();
    return 3;
  }

  public boolean atualizarUsuario(String cpf, String nome, String senha, String repSenha, boolean isAdmin) {
    erroDeAcesso();
    return false;
  }

  public boolean removerUsuario(String cpf) {
    erroDeAcesso();
    return false;
  }

  public int cadPaciente(String cpf, String nome, Date dataNasc, boolean isSaude, String cep, String rua, String num, String bairro, String comp, String cidade, String estado) {
    erroDeAcesso();
    return 0;
  }

  public int atualizarPaciente(Paciente p, String nome, Date dataNasc, boolean isSaude, String cep, String rua, String num, String comp, String bairro, String cidade, String uf) {
    erroDeAcesso();
    return 0;
  }

  public int removerPaciente(String cpf) {
    erroDeAcesso();
    return 0;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Getters/Setters">   
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  
  public String getSenha() {
    return senha;
  }
  // </editor-fold>


}
