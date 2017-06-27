/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author gileno.macedo
 *
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Caderneta.ConsultaTodos",
            query = "SELECT e FROM  Caderneta e"),

    @NamedQuery(name = "Caderneta.ConsultaporNome",
            query = "SELECT e FROM Caderneta e WHERE e.geologo LIKE :geologo"),

    @NamedQuery(name = "Caderneta.ConsultaporTitulo",
            query = "SELECT e FROM Caderneta e WHERE e.projeto = :projeto "),})


public class Caderneta implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cod_Caderneta;

    @Column(length = 100)
    private String projeto;

    @Column(length = 100)
    private String geologo;

    @Column(length = 100)
    private String centroCusto;

    @Column(length = 100)
    private String tipo;

    @Column(length = 10)
    private String ano;

    @Column(length = 100)
    private int qtd;

    @Column(length = 100)
    private int prateleira;

    public Long getCod_Caderneta() {
        return cod_Caderneta;
    }

    public void setCod_Caderneta(Long cod_Caderneta) {
        this.cod_Caderneta = cod_Caderneta;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getGeologo() {
        return geologo;
    }

    public void setGeologo(String geologo) {
        this.geologo = geologo;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(int prateleira) {
        this.prateleira = prateleira;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.cod_Caderneta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Caderneta other = (Caderneta) obj;
        if (!Objects.equals(this.cod_Caderneta, other.cod_Caderneta)) {
            return false;
        }
        return true;
    }

}
