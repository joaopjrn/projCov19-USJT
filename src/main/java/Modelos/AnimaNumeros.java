/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Telas.PagStats;
import java.text.NumberFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author joaop
 */
public interface AnimaNumeros {
  
  default void animarNumeros(JLabel txt, int total, double s) {
    new Thread(() -> {
      long t1 = new Date().getTime();
      double tempo = s * 1000;
      double intervalo = 49;
      double count = 0;
      double incremento = total / (tempo / intervalo);
      System.out.println(incremento);
      while (count != total) {
        if (count + incremento >= total) {
          count = total;
          txt.setText("" + NumberFormat.getNumberInstance().format((int) count));
        } else {
          count += incremento;
          txt.setText("" + NumberFormat.getNumberInstance().format((int) count));
        }
        try {
          Thread.sleep((int) intervalo);
        } catch (InterruptedException ex) {
          Logger.getLogger(PagStats.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      System.out.println(new Date().getTime() - t1);
    }).start();
  }
  
}
