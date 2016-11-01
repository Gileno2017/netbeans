/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
import javax.validation.constraints.Null;
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

    @OneToMany(mappedBy = "mapas")
    @JoinColumn(name = "codMapa")

    private List<Fotografias> listadeFotografias;

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
      @Pattern(regexp = "([a-zA-Z]{2}\\.[0-9]{2}?\\-[a-zA-Z]{1}?\\-[a-zA-Z]{1}?\\-[0-9]{1}?)|([a-zA-Z]{2}\\.[0-9]{2}?\\-[a-zA-Z]{1}?\\-[a-zA-Z]{1}?)",
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
    
    @Lob
     private byte[] imagem;

    @NotNull(message = "O campo QUANTIDADE  não pode ser nulo")
    @NotBlank(message = "O campo QUANTIDADE deve ser preenchido")
    private String quantidade;

    public List<Fotografias> getListadeFotografias() {
        return listadeFotografias;
    }

    public void setListadeFotografias(List<Fotografias> listadeFotografias) {
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

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }


  
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.codMapa);
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
        return Objects.equals(this.codMapa, other.codMapa);
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

}
