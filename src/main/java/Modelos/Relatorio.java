/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BD.RelatorioDAO;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author W10 VM
 */
public class Relatorio {

  private LocalDate di, df;
  private long periodo;
  private ArrayList<Paciente> lista;
  private int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
  private int m1, m2, m3, m4;
  
  public Relatorio(Date di, Date df) {
    this.di = Instant.ofEpochMilli(di.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    this.df = Instant.ofEpochMilli(df.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    this.periodo = ChronoUnit.DAYS.between(this.di, this.df) + 1;
    this.lista = gerarRelatorio();
    contar();
    calcMedia();
  }
  
  private void contar(){
    for(Paciente p : this.lista){
      if(p.getIdade() >= 90){
        c1++;
      }else if(p.getIdade() >= 70 && p.getIdade() < 90){
        c2++;
      } else if(p.getIdade() >= 50 && p.getIdade() < 70){
        c3++;
      } else if(p.getIdade() < 50){
        c4++;
      }
    }
  }
  
  private void calcMedia(){
    m1 = c1 / (int) periodo;
    m2 = c2 / (int) periodo;
    m3 = c3 / (int) periodo;
    m4 = c4 / (int) periodo;
  }

  private ArrayList<Paciente> gerarRelatorio() {
    return RelatorioDAO.selectRelatorio(this);
  }

  public LocalDate getDi() {
    return di;
  }

  public void setDi(LocalDate di) {
    this.di = di;
  }

  public LocalDate getDf() {
    return df;
  }

  public void setDf(LocalDate df) {
    this.df = df;
  }

  public long getPeriodo() {
    return periodo;
  }

  public void setPeriodo(long periodo) {
    this.periodo = periodo;
  }

  public ArrayList<Paciente> getLista() {
    return lista;
  }

  public void setLista(ArrayList<Paciente> lista) {
    this.lista = lista;
  }

  public int getC1() {
    return c1;
  }

  public void setC1(int c1) {
    this.c1 = c1;
  }

  public int getC2() {
    return c2;
  }

  public void setC2(int c2) {
    this.c2 = c2;
  }

  public int getC3() {
    return c3;
  }

  public void setC3(int c3) {
    this.c3 = c3;
  }

  public int getC4() {
    return c4;
  }

  public void setC4(int c4) {
    this.c4 = c4;
  }

  public int getM1() {
    return m1;
  }

  public void setM1(int m1) {
    this.m1 = m1;
  }

  public int getM2() {
    return m2;
  }

  public void setM2(int m2) {
    this.m2 = m2;
  }

  public int getM3() {
    return m3;
  }

  public void setM3(int m3) {
    this.m3 = m3;
  }

  public int getM4() {
    return m4;
  }

  public void setM4(int m4) {
    this.m4 = m4;
  }





}
