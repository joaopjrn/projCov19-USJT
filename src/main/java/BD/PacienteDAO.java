/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelos.Endereco;
import Modelos.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaop
 */
public class PacienteDAO {
  // Comentário
    public static int insertPaciente(Paciente p) {
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      String query = "insert into pacientes (cpf, nome, dataNasc, isSaude, cep, rua, complemento, bairro, numero, cidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement ps = c.prepareStatement(query);
      ps.setString(1, p.getCpf());
      ps.setString(2, p.getNome());
      ps.setString(3, p.getDataNasc().toString());
      ps.setBoolean(4, p.isSaude());
      ps.setString(5, p.getEndereco().getCep());
      ps.setString(6, p.getEndereco().getRua());
      ps.setString(7, p.getEndereco().getComplemento());
      ps.setString(8, p.getEndereco().getBairro());
      ps.setInt(9, p.getEndereco().getNumero());
      ps.setString(10, p.getEndereco().getCidade());
      ps.setString(11, p.getEndereco().getEstado());

      return ps.executeUpdate();

    } catch (SQLIntegrityConstraintViolationException ex) {
      return -1;
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
    return 0;
  }
  
  public static Paciente selectPaciente(String cpf){
    Connection c = null;
    Paciente p = null;
    try {
      c = BD.iniciaConexao();
      String query = "select pacientes.*, usuarios.nome as nomeFunc from pacientes "
                   + "left join usuarios "
                   + "on pacientes.vacPor = usuarios.cpf "
                   + "where pacientes.cpf = ?";
      PreparedStatement ps = c.prepareStatement(query);
      ps.setString(1, cpf);
      var r = ps.executeQuery();
      if(r.next()){
        String rua = r.getString("rua");
        String cidade = r.getString("cidade");
        String estado = r.getString("uf");
        String bairro = r.getString("bairro");
        String cep = r.getString("cep");
        String comp = r.getString("complemento");
        int num = r.getInt("numero");
        
        Endereco e = new Endereco(rua, cidade, estado, bairro, cep, comp, num);
        
        String nome = r.getString("nome");
        String dv = r.getString("dataVac");
        LocalDate dataNasc = LocalDate.parse(r.getString("dataNasc"));
        LocalDate dataVac = dv == null ? null : LocalDate.parse(dv);
        boolean isSaude = r.getBoolean("isSaude");
        String nomeVacPor = r.getString("nomeFunc");
        System.out.println(dv);
        System.out.println(dataNasc);
        
        p = new Paciente(nome, cpf, dataNasc, dataVac, isSaude, nomeVacPor, e);
        return p;
      }else{
        return null;
      }
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
    return null;
  }
  
  public static int updatePaciente(Paciente p){
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      String query = "update pacientes "
              + "set nome = ?, dataNasc = ?, isSaude = ?, cep = ?, rua = ?, bairro = ?, numero = ?, complemento = ?, cidade = ?, uf = ? where cpf = ?";
      PreparedStatement ps = c.prepareStatement(query);
      
      ps.setString(1, p.getNome());
      ps.setString(2, p.getDataNasc().toString());
      ps.setBoolean(3, p.isSaude());
      ps.setString(4, p.getEndereco().getCep());
      ps.setString(5, p.getEndereco().getRua());
      ps.setString(6, p.getEndereco().getBairro());
      ps.setInt(7, p.getEndereco().getNumero());
      ps.setString(8, p.getEndereco().getComplemento());
      ps.setString(9, p.getEndereco().getCidade());
      ps.setString(10, p.getEndereco().getEstado());
      ps.setString(11, p.getCpf());
      
      int cod = ps.executeUpdate();
      
      return cod;
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
    return 0;
  }
  
  public static int deletePaciente(String cpf){
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      String query = "delete from pacientes where cpf = ?";
      PreparedStatement ps = c.prepareStatement(query);
      ps.setString(1, cpf);
      
      int cod = ps.executeUpdate();
      
      return cod;
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
    return 0;
  }
  
  public static ArrayList<Paciente> selectFila() {
    Connection c = null;
    ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    try {
      c = BD.iniciaConexao();
      String query = "select cpf, nome, dataNasc, isSaude from pacientes where dataVac is null";
      PreparedStatement ps = c.prepareStatement(query);
      var r = ps.executeQuery();
      while(r.next()){
        pacientes.add(new Paciente(
                r.getString("nome"),
                r.getString("cpf"),
                LocalDate.parse(r.getString("dataNasc")),
                r.getBoolean("isSaude"),
                null)); //vacPor
      }
      return pacientes;
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
      return null;
  }
  
  public static int confVac(Paciente p){
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      String query = "update pacientes set dataVac = ?, vacPor = ? where cpf = ?";
      PreparedStatement ps = c.prepareStatement(query);
      ps.setString(1, p.getDataVac().toString());
      ps.setString(2, p.getVacPor());
      ps.setString(3, p.getCpf());
      int cod = ps.executeUpdate();
      return cod;
      
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
      return 0;
  }
}
