/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author W10 VM
 */
public class BD {

  public static Connection iniciaConexao() throws SQLException {
    Connection c = DriverManager.getConnection("jdbc:mysql://HOST_NAME:PORT/BD_NAME", "SCHEMA_NAME", "PASSWORD");
    System.out.println("Conexão Iniciada!");
    return c;
  }

  public static void encerraConexao(Connection c) {
    try {
      c.close();
      System.out.println("conexão encerrada");
    } catch (SQLException ex) {
      Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
