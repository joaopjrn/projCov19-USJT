/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BD.StatsDAO;

/**
 *
 * @author W10 VM
 */
public class Stats {

  private int vacinados, fila;

  public Stats() {
  }
  
  public static Stats getStats(){
    return StatsDAO.getStats();
  }

  public int getVacinados() {
    return vacinados;
  }

  public void setVacinados(int vacinados) {
    this.vacinados = vacinados;
  }

  public int getFila() {
    return fila;
  }

  public void setFila(int fila) {
    this.fila = fila;
  }

}
