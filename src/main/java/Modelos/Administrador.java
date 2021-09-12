/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BD.PacienteDAO;
import BD.UsuarioDAO;
import Telas.PagGerPacientes;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author W10 VM
 */
public class Administrador extends Atendente {

  public Administrador(String nome, String cpf) {
    super(nome, cpf);
  }

  public Administrador(String nome, String cpf, String senha) {
    super(nome, cpf, senha);
  }
  
//  @Override
//  public Paciente buscarPaciente(String cpf) {
//    Paciente p = UsuarioDAO.selectPaciente(cpf);
//  }
  
  @Override
  public int removerPaciente(String cpf) {
    if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este paciente?") == 0) {
      int cod = PacienteDAO.deletePaciente(cpf);
      if (cod > 0) {
        JOptionPane.showMessageDialog(null, "Paciente removido com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int atualizarPaciente(Paciente p, String nome, Date dataNasc, boolean isSaude, String cep, String rua, String num, String comp, String bairro, String cidade, String estado) {
    if (nome.equals("") || dataNasc == null || rua.equals("") || cidade.equals("") || estado.equals("") || num.equals("")) {
      JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
      return 0;
    }
    
    p.setNome(nome);
    p.setDataNasc(Instant.ofEpochMilli(dataNasc.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
    p.setSaude(isSaude);
    
    p.getEndereco().setCep(cep);
    p.getEndereco().setRua(rua);
    p.getEndereco().setBairro(bairro);
    p.getEndereco().setNumero(Integer.parseInt(num));
    p.getEndereco().setComplemento(comp);
    p.getEndereco().setCidade(cidade);
    p.getEndereco().setEstado(estado);
    
    int cod = PacienteDAO.updatePaciente(p);
    
    if(cod > 0){
      JOptionPane.showMessageDialog(null, "Paciente atualizado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      return 1;
    }
    return 0;
  }
  
  @Override
  public int cadPaciente(String cpf, String nome, Date dataNasc, boolean isSaude, String cep, String rua, String num, String bairro, String comp, String cidade, String estado) {
    if (cpf.contains(" ") || nome.equals("") || dataNasc == null || rua.equals("") || cidade.equals("") || estado.equals("") || num.equals("")) {
      JOptionPane.showMessageDialog(null, "Preencha todos os campos Por favor!", "Erro", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    
    if(!isCPF(cpf)){
      JOptionPane.showMessageDialog(null, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
      return 0;
    }

    Endereco end = new Endereco(rua, cidade, estado, bairro, cep, comp, Integer.parseInt(num));
    LocalDate dNasc = Instant.ofEpochMilli(dataNasc.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    Paciente p = new Paciente(nome, cpf, dNasc, null, isSaude, end);
    
    if(p.getIdade() < 18){
      JOptionPane.showMessageDialog(null, "Idade precisa ser maior ou igual a 18 anos!", "Erro", JOptionPane.ERROR_MESSAGE);
      return -2;
    }

    int cod = PacienteDAO.insertPaciente(p);
    System.out.println(cod);
    if (cod > 0) {
      JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      return 1;
    } else if (cod == -1) {
      JOptionPane.showMessageDialog(null, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, "Erro ao cadastrar paciente", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return 0;
  }

  @Override
    public Endereco buscarCep(String cep){
      if(cep.contains(" ")){
        JOptionPane.showMessageDialog(null, "Preencha o CEP corretamente!", "Erro",  JOptionPane.ERROR_MESSAGE);
        return null;
      }
    try {
      ViaCEPClient vcc = new ViaCEPClient();
      ViaCEPEndereco end = vcc.getEndereco(cep);
      if(end != null){  
//        return new Endereco(end.getLogradouro(), end.getLocalidade(), end.getUf(), end.getBairro(), cep);
        return new Endereco(end.getLogradouro(), end.getLocalidade(), end.getUf(), end.getBairro(), cep);
      }else{
        JOptionPane.showMessageDialog(null, "CEP não encontrado!", "Erro",  JOptionPane.ERROR_MESSAGE);
      }
    } catch (IOException ex) {
      Logger.getLogger(PagGerPacientes.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
  
  
  
  @Override
  public Atendente buscarUsuario(String cpf) {
    if (getCpf().equals(cpf)) {
      JOptionPane.showMessageDialog(null, "Não é possível alterar o usuário que está usando o sistema!", "Sucesso", JOptionPane.ERROR_MESSAGE);
      return null;
    }

    if (cpf.contains(" ")) {
      JOptionPane.showMessageDialog(null, "Prencha o CPF corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }
    
    Object[] u = UsuarioDAO.selectUsuario(cpf);
    if (u == null) {
      JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }
    if ((boolean) u[2]) {
      return new Administrador((String) u[0], (String) u[1], (String) u[3]);
    } else {
      return new Atendente((String) u[0], (String) u[1], (String) u[3]);
    }
  }

  @Override
  public boolean removerUsuario(String cpf) {
    if(cpf.contains(" ")){
      JOptionPane.showMessageDialog(null, "Prencha o CPF corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse Usuário?") == 0){
      if(UsuarioDAO.deleteUsuario(cpf)){
        JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        return true;
      }else{
        JOptionPane.showMessageDialog(null, "Falha ao Excluir Usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }else{
      return false;
    }
  }

  @Override
  public boolean atualizarUsuario(String cpf, String nome, String senha, String repSenha, boolean isAdmin) {
    if(cpf.contains(" ") || nome.equals("") || senha.equals("") || repSenha.equals("")){
      JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    if(!senha.equals(repSenha)){
      JOptionPane.showMessageDialog(null, "Senhas não compatíveis!", "Erro", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    Atendente u = null;
    if(isAdmin){
      u = new Administrador(nome, cpf, senha);
    }else{
      u = new Atendente(nome, cpf, senha);
    }
    
    if(u != null){
      int x = UsuarioDAO.updateUsuario(u);
      if (x==1){
        JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        return true;
      }
    }
    
    return false;
  }

  @Override
  public int cadUsuario(String cpf, String nome, String senha, String repSenha, boolean isAdmin) {
    if (cpf.contains(" ") || nome.equals("") || senha.equals("") || repSenha.equals("")) {
      JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
      return 3;
    }
    if (!senha.equals(repSenha)) {
      JOptionPane.showMessageDialog(null, "Senhas incompatíveis!", "Erro", JOptionPane.ERROR_MESSAGE);
      return 3;
    }
    if(!isCPF(cpf)){
      JOptionPane.showMessageDialog(null, "CPF Inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
      return 3;
    }
    Atendente u = new Atendente(nome, cpf, senha);
    int x = UsuarioDAO.insertUsuario(u, isAdmin);
    if (x == 2) {
      JOptionPane.showMessageDialog(null, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
      return 3;
    }
    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    return 1;
  }

  @Override
  public void gerarRelatorio() {
    
  }

  
  
}
