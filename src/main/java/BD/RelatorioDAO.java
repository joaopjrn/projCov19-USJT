/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelos.Paciente;
import Modelos.Relatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author W10 VM
 */
public class RelatorioDAO {
  
  public static ArrayList<Paciente> selectRelatorio(Relatorio re){
    ArrayList<Paciente> lista = new ArrayList<Paciente>();
    Connection c = null;
    try {
      c = BD.iniciaConexao();
      String query = "select dataNasc, dataVac "
                   + "from pacientes where dataVac >= ? AND dataVac <= ?"
                   + "order by dataVac";
      PreparedStatement ps = c.prepareStatement(query);
      ps.setString(1, re.getDi().toString());
      ps.setString(2, re.getDf().toString());
      var r = ps.executeQuery();
      while(r.next()){
        lista.add(new Paciente(LocalDate.parse(r.getString("dataNasc")), LocalDate.parse(r.getString("dataVac"))));
      }
      return lista;
    } catch (SQLException ex) {
      Logger.getLogger(RelatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      BD.encerraConexao(c);
    }
    return null;
  }
  
}
