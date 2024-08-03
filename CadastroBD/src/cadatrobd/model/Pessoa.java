/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadatrobd.model;

/**
 *
 * @author Administrador
 */
public class Pessoa {
    
  protected int id;
  protected String nome;
  protected String logradouro;
  protected String cidade;
  protected String estado;
  protected String telefone;
  protected String email;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String logradouro, String cidade,
            String estado, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
       
   public void exibir(){
          System.out.println("---------------------------------");  
          System.out.println("ID: " + id);
          System.out.println("Nome: " + nome);
          System.out.println("Logradouro: " + logradouro);
          System.out.println("Cidade: " + cidade);
          System.out.println("Estado: " + estado);
          System.out.println("Telefone: " + telefone);
          System.out.println("Email: " + email);
     }
}