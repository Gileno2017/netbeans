/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cprm.gov.gileno.macedo.MODELO;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author gileno.macedo
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.ConsultaTodos",
            query = "SELECT u FROM  Usuario u"),

    @NamedQuery(name = "Usuario.ConsultaporNome",
            query = "SELECT u FROM Usuario u WHERE u.login LIKE :usuario"),

    @NamedQuery(name = "Caderneta.ConsultaporTitulo",
            query = "SELECT e FROM Caderneta e WHERE e.projeto = :projeto "),})

public class Usuario implements Serializable{     
    
     private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
  
    private Long cod_usuario;
    
    @NotNull(message = "O campo NOME não pode ser nulo")
    @NotBlank(message = "O campo NOME deve ser preenchido")
    private String nome;
    
    @NotNull(message = "O campo EMAIL não pode ser nulo")
    @NotBlank(message = "O campo EMAIL deve ser preenchido")
    private String email;
    
    @NotNull(message = "O campo LOGIN não pode ser nulo")
    @NotBlank(message = "O campo LOGIN deve ser preenchido")
    private String login;
    
    
    @NotNull(message = "O campo SENHA não pode ser nulo")
    @NotBlank(message = "O campo SENHA deve ser preenchido")    
    private String senha;
    
    @NotNull(message = "O campo TELEFONE não pode ser nulo")
    @NotBlank(message = "O campo TELEFONE deve ser preenchido")
    private String telefone;
    
    @NotNull(message = "O campo PERFIL não pode ser nulo")
    @NotBlank(message = "O campo PERFIL deve ser preenchido")
    private String perfil;  

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Long getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(Long cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
