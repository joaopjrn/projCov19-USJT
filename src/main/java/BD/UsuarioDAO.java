/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelos.Administrador;
import Modelos.Atendente;
import Modelos.Endereco;
import Modelos.Paciente;
import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author W10 VM
 */
public class UsuarioDAO {

  public static Object[] login(Usuario u){
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      PreparedStatement ps = c.prepareStatement("select * from usuarios where cpf=? and senha=?");
      ps.setString(1, u.getLogin());
      ps.setString(2, u.getSenha());
      ResultSet r = ps.executeQuery();
      if(r.next()){
        return new Object[] {r.getString("cpf"), r.getString("nome"), r.getBoolean("isAdmin")};
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
  
  public static int insertUsuario(Atendente u, boolean isAdmin) {
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      PreparedStatement ps = c.prepareStatement("insert into usuarios (cpf, nome, senha, isAdmin) "
              + "values (?,?,?,?)");
      ps.setString(1, u.getCpf());
      ps.setString(2, u.getNome());
      ps.setString(3, u.getSenha());
      ps.setBoolean(4, isAdmin);
      ps.executeUpdate();
      return 1;
    } catch (SQLIntegrityConstraintViolationException ex) {
      return 2;
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
      return 3;
    } finally {
      BD.encerraConexao(c);
    }
  }
  
  public static Object[] selectUsuario(String cpf) {
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      PreparedStatement ps = c.prepareStatement("select * from usuarios where cpf=?");
      ps.setString(1, cpf);
      ResultSet r = ps.executeQuery();
      if (r.next()) {
        return new Object[]{r.getString("nome"), r.getString("cpf"), r.getBoolean("isAdmin"), r.getString("senha")};
      } else {
        return null;
      }
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
    return null;
  }
  
  public static boolean deleteUsuario(String cpf){
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      PreparedStatement ps = c.prepareStatement("delete from usuarios where cpf=?");
      ps.setString(1, cpf);
      ps.executeUpdate();
      return true;
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
    return false;
  }
  
  public static int updateUsuario(Atendente u){
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      PreparedStatement ps = c.prepareStatement("update usuarios set nome=?, senha=?, isAdmin=? where cpf=?");
      ps.setString(1, u.getNome());
      ps.setString(2, u.getSenha());
      ps.setBoolean(3, u.getClass().equals(Administrador.class) ? true : false);
      ps.setString(4, u.getCpf());
      int x = ps.executeUpdate();
      System.out.println(x);
      return x;
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
    return 0;
  }
  
}
