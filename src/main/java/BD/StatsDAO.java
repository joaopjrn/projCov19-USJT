/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelos.Stats;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author W10 VM
 */
public class StatsDAO {
  
  public static Stats getStats(){
    Stats s = new Stats();
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      Statement stm = c.createStatement();
      var r = stm.executeQuery("select (select count(cpf) from pacientes where dataVac is not null) as vacinados, (select count(cpf) from pacientes where dataVac is null) as fila");
      if(r.next()){
        s.setFila(r.getInt("fila"));
        s.setVacinados(r.getInt("vacinados"));
        return s;
      }
    } catch (SQLException ex) {
      Logger.getLogger(StatsDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
     BD.encerraConexao(c);
    }
    return null;
    
  }
  
}
