/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gileno.macedo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ImagemMapas.consultaPorId",
            query = "SELECT e FROM  ImagemMapas e"),})

public class ImagemMapas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq_img", sequenceName = "seq_cod_img", allocationSize = 1)
    @GeneratedValue(generator = "seq_img", strategy = GenerationType.SEQUENCE)
    private Long idImagemMapas;

    @NotNull(message = "O Campo Imagem não pode ser Nullo !!")
    private byte[] imagem;

    public Long getIdImagemMapas() {
        return idImagemMapas;
    }

    public void setIdImagemMapas(Long idImagemMapas) {
        this.idImagemMapas = idImagemMapas;
    }

  
    @ManyToOne
    @JoinColumn(name = "cod_Fotografia")
    private Fotografias fotos;

    public Fotografias getFotos() {
        return fotos;
    }

    public void setFotos(Fotografias fotos) {
        this.fotos = fotos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.idImagemMapas);
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
        final ImagemMapas other = (ImagemMapas) obj;
        if (!Objects.equals(this.idImagemMapas, other.idImagemMapas)) {
            return false;
        }
        return true;
    }

    public void setImagem(ImagemMapas imagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
