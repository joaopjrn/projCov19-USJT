/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author W10 VM
 */
public class Endereco {
  private ViaCEPEndereco endereco = null;
  private String rua, cidade, estado, bairro, cep, complemento;
  private int numero;

//  public Endereco(String rua, String complemento, int numero) {
//    this.rua = rua;
//    this.complemento = complemento;
//    this.numero = numero;
//  }
//  
//  public Endereco(int numero, String complemento, String cep) {
//      this.cep = cep;
//      this.complemento = complemento;
//      this.numero = numero;
//  }

  public Endereco(String rua, String cidade, String estado, String bairro, String cep) {
    this.rua = rua;
    this.cidade = cidade;
    this.estado = estado;
    this.bairro = bairro;
    this.cep = cep;
  }
  
  public Endereco(String rua, String cidade, String estado, String bairro, String cep, String complemento, int numero) {
    this.rua = rua;
    this.cidade = cidade;
    this.estado = estado;
    this.bairro = bairro;
    this.cep = cep;
    this.complemento = complemento;
    this.numero = numero;
  }
  
  private boolean checarCep() {
    try {
      ViaCEPClient vcc = new ViaCEPClient();
//      ViaCEPEndereco vce = vcc.getEndereco("18960-000");
      ViaCEPEndereco vce = vcc.getEndereco("05126-000");
      if(vce == null) return false;
      if(vce != null && vce.getLogradouro().equals("")) return false;
      if(vce != null && !vce.getLogradouro().equals("")) {
        this.endereco = vce;
      }
    } catch (IOException ex) {
      Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }
  
  public String getRua() {
    return rua;
  }

  public String getCidade() {
    return cidade;
  }

  public String getEstado() {
    return estado;
  }

  public String getBairro() {
    return bairro;
  }

  public String getCep() {
    return cep;
  }

  public int getNumero() {
    return numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }
  
  
  
  
  
  
  
}
