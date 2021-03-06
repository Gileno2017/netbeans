/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cprm.gov.gileno.macedo.MODELO;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author gileno.macedo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Mapas.ConsultaTodos", query = "SELECT e FROM  Mapas e"),
    @NamedQuery(name = "Mapas.ConsultaporFolha", query = "SELECT e FROM Mapas e WHERE e.folha LIKE :folha"),})
public class Mapas implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "mapas",cascade = CascadeType.ALL)
    @JoinColumn(name = "codMapa")

    private List<MapaIndice> listadeFotografias;

    @Id
    @GeneratedValue
    private Long codMapa;

    @NotNull(message = "O campo TÍTULO não pode ser nulo")
    @NotBlank(message = "O campo TÍTULO deve ser preenchido")
     @Pattern(regexp = "[A-Z-0-9\\s]+", message = "o campo TÍTULO deve ser preenchido somente com letras e numeros")
    @Length(max = 50, message = "O TÍTULO não pode conter mais de {max} caracteres")
    private String titulo;

    @NotNull(message = "O campo ESCALA  não pode ser nulo")
    @NotBlank(message = "O campo ESCALA deve ser preenchido")
    private String escala;

    @NotNull(message = "O campo FOLHA não pode ser nulo")
    @NotBlank(message = "O campo FOLHA deve ser preenchido")
      @Pattern(regexp = "([a-zA-Z]{2}\\.[0-9]{2}?\\-[a-zA-Z]{1}?\\-[a-zA-Z]{1}?\\-[a-zA-Z]{3}?)|([a-zA-Z]{2}\\.[0-9]{2}?\\-[a-zA-Z]{1}?\\-[a-zA-Z]{1}?)",
     message = "o campo FOLHA deve ser preenchido  com o formato (XX.11-X-X)  ou  (XX.11-X-X-1)")
    @Length(max = 20, message = "O FOLHA não pode conter mais de {max} caracteres")
    private String folha;

    
    @NotNull(message = "O campo EDITORA não pode ser nulo")
    @NotBlank(message = "O campo EDITORA deve ser preenchido")
    @Pattern(regexp = "[A-Z\\/d\\s]+", message = "o campo EDITORA deve ser preenchido somente com letras e barra  / ")
    @Length(max = 30, message = "O EDITORA não pode conter mais de {max} caracteres")
    private String editora;

    @NotNull(message = "O campo TIPO não pode ser nulo")
    @NotBlank(message = "O campo TIPO deve ser preenchido")
    private String tipo;

    @Column(length = 10)
    private String ano;

    @NotNull(message = "O campo GAVETA não pode ser nulo")
    @NotBlank(message = "O campo GAVETA deve ser preenchido")
    private String gaveta;
    
    @NotNull(message = "O campo IMAGEM não pode ser nulo")
    @NotBlank(message = "O campo IMAGEM deve ser selecionada")
    private String imagem;

    @NotNull(message = "O campo QUANTIDADE  não pode ser nulo")
    @NotBlank(message = "O campo QUANTIDADE deve ser preenchido")
    private String quantidade;
    
    @NotNull(message = "O campo caminho  não pode ser nulo")
    @NotBlank(message = "O campo caminho deve ser preenchido")
    private String caminho;
    
    @NotNull(message = "O campo HEMISFÉRIO  não pode ser nulo")
    @NotBlank(message = "O campo HEMISFÉRIO deve ser preenchido")
    private String hemisferio;

    public List<MapaIndice> getListadeFotografias() {
        return listadeFotografias;
    }

    public void setListadeFotografias(List<MapaIndice> listadeFotografias) {
        this.listadeFotografias = listadeFotografias;
    }

    public Long getCodMapa() {
        return codMapa;
    }

    public void setCodMapa(Long codMapa) {
        this.codMapa = codMapa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getFolha() {
        return folha;
    }

    public void setFolha(String folha) {
        this.folha = folha;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
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

    public String getGaveta() {
        return gaveta;
    }

    public void setGaveta(String gaveta) {
        this.gaveta = gaveta;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem( String imagem) {
        this.imagem = imagem;
    }


    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getHemisferio() {
        return hemisferio;
    }

    public void setHemisferio(String hemisferio) {
        this.hemisferio = hemisferio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codMapa);
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
        final Mapas other = (Mapas) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.escala, other.escala)) {
            return false;
        }
        if (!Objects.equals(this.folha, other.folha)) {
            return false;
        }
        if (!Objects.equals(this.editora, other.editora)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.ano, other.ano)) {
            return false;
        }
        if (!Objects.equals(this.gaveta, other.gaveta)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        if (!Objects.equals(this.caminho, other.caminho)) {
            return false;
        }
        if (!Objects.equals(this.listadeFotografias, other.listadeFotografias)) {
            return false;
        }
        if (!Objects.equals(this.codMapa, other.codMapa)) {
            return false;
        }
        if (!Objects.equals(this.imagem, other.imagem)) {
            return false;
        }
        return true;
    }
    
    
}
  
   
