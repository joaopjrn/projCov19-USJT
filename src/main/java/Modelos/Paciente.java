/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author W10 VM
 */
public class Paciente implements Comparable<Paciente> {

  private int idade, nivel;
  private String nome, cpf, vacPor, nomeVacPor;
  private LocalDate dataNasc, dataVac;
  private boolean saude;
  private Endereco endereco;

  public Paciente(String nome, String cpf, LocalDate dataNasc, LocalDate dataVac, boolean saude, String nomeVacPor, Endereco endereco) {
    this.dataNasc = dataNasc;
//    this.idade = (int) ChronoUnit.DAYS.between(dataNasc, LocalDate.now());
    this.idade = Period.between(dataNasc, LocalDate.now()).getYears();
    if (this.idade >= 70) {
      this.nivel = 1;
    } else if (this.idade < 70 && saude) {
      this.nivel = 2;
    } else {
      this.nivel = 3;
    }
    this.nome = nome;
    this.cpf = cpf;
    this.dataVac = dataVac;
    this.saude = saude;
    this.nomeVacPor = nomeVacPor;
    this.endereco = endereco;
  }
  
  public Paciente(String nome, String cpf, LocalDate dataNasc, LocalDate dataVac, boolean saude, Endereco endereco) {
    this.dataNasc = dataNasc;
//    this.idade = (int) ChronoUnit.DAYS.between(dataNasc, LocalDate.now());
    this.idade = Period.between(dataNasc, LocalDate.now()).getYears();
    if (this.idade >= 70) {
      this.nivel = 1;
    } else if (this.idade < 70 && saude) {
      this.nivel = 2;
    } else {
      this.nivel = 3;
    }
    this.nome = nome;
    this.cpf = cpf;
    this.dataVac = dataVac;
    this.saude = saude;
    this.endereco = endereco;
  }

  public Paciente(LocalDate dataNasc, LocalDate dataVac) {
    this.dataNasc = dataNasc;
    this.dataVac = dataVac;
    this.idade = Period.between(dataNasc, LocalDate.now()).getYears();
  }

  public Paciente(String nome, String cpf, LocalDate dataNasc, boolean saude, String vacPor) {
    this.dataNasc = dataNasc;
    this.idade = Period.between(dataNasc, LocalDate.now()).getYears();
    if (this.idade >= 70) {
      this.nivel = 1;
    } else if (this.idade < 70 && saude) {
      this.nivel = 2;
    } else {
      this.nivel = 3;
    }
    this.nome = nome;
    this.cpf = cpf;
    this.saude = saude;
    this.vacPor = vacPor;
  }

  public Object[] toTableRow() {
    return new Object[]{this.nome, this.idade, this.nivel, this.saude ? "Sim" : "Não"};
  }

  public int getIdade() {
    return idade;
  }

  public int getNivel() {
    return nivel;
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public LocalDate getDataNasc() {
    return dataNasc;
  }

  public Date getDataNascAsDate() {
    return new Date().from(this.dataNasc.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public LocalDate getDataVac() {
    return dataVac;
  }

  public boolean isSaude() {
    return saude;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setDataNasc(LocalDate dataNasc) {
    this.dataNasc = dataNasc;
  }

  public void setDataVac(LocalDate dataVac) {
    this.dataVac = dataVac;
  }

  public void setSaude(boolean saude) {
    this.saude = saude;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  public String getNomeVacPor() {
    return nomeVacPor;
  }

  public void setNomeVacPor(String nomeVacPor) {
    this.nomeVacPor = nomeVacPor;
  }

  @Override
  public int compareTo(Paciente p) {
    if (this.nivel == p.getNivel()) {
      if (this.idade > p.getIdade()) {
        return -1;
      } else {
        return 1;
      }
    } else if (this.nivel < p.getNivel()) {
      return -1;
    } else {
      return 1;
    }
  }

  public void setVacPor(String vacPor) {
    this.vacPor = vacPor;
  }

  public String getVacPor() {
    return vacPor;
  }

}
