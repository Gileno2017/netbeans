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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author gileno.macedo
 */
@Entity
  @NamedQueries({
        @NamedQuery(name = "Mapas.ConsultaTodos", query = "SELECT e FROM  Mapas e"),
          @NamedQuery(name="Mapas.ConsultaporFolha",  query="SELECT e FROM Mapas e WHERE e.folha LIKE :folha"),
      
}) 
public class Mapas implements Serializable {
     private static final long serialVersionUID = 1L;
 
    @OneToMany( mappedBy = "mapas")   
    @JoinColumn(name="codMapa")
    
     private   List<Fotografias> listadeFotografias;
   
  @Id
  @GeneratedValue
    private Long codMapa;
   
   @Column(length = 30)    
    private String titulo;
    
 @Column(length = 30)    
    private String escala;
    
    @Column(length = 30)    
    private String folha;
    
    @Column(length = 50)    
    private String editora;
    
  @Column(length = 30)    
    private String tipo;
    
  @Column(length = 10)    
    private String ano;
    
    @Column(length = 10)    
    private String gaveta;
    

    private byte[] imagem;
    
    @Column(length = 30)    
      private int quantidade;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

}
