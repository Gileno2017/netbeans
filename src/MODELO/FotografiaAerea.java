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
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author gileno.macedo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "FotografiaAerea.ConsultaTodos",
            query = "SELECT e FROM  FotografiaAerea e"),})
//    @NamedQuery(name="FotografiaAerea.consultaPorFotografiaAerea",
//       query = "SELECT e FROM FotografiaAerea e WHERE  e.FotografiaAerea.cod_MapaIndice = :consultaPorFotografiaAereaId " ),
//})

public class FotografiaAerea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq_img", sequenceName = "seq_cod_img", allocationSize = 1)
    @GeneratedValue(generator = "seq_img", strategy = GenerationType.SEQUENCE)
    private Long idIFotografiaAerea;

    @NotNull(message = "O Campo Imagem não pode ser Nullo !!")
    private String imagemFotografiaAerea;

    public Long getIdIFotografiaAerea() {
        return idIFotografiaAerea;
    }

    public void setIdIFotografiaAerea(Long idIFotografiaAerea) {
        this.idIFotografiaAerea = idIFotografiaAerea;
    }

  
    @ManyToOne
    @JoinColumn(name = "cod_Fotografia")
    private MapaIndice mpIndiceImagem;

    public MapaIndice getMpIndiceImagem() {
        return mpIndiceImagem;
    }

    public void setMpIndiceImagem(MapaIndice mpIndiceImagem) {
        this.mpIndiceImagem = mpIndiceImagem;
    }
    
    @NotNull(message = "O campo CÓDIGO INICIAL não pode ser nulo")
    @NotBlank(message = "O campo CÓDIGO INICIAL deve ser preenchido")
    @Pattern(regexp = "[0-9]+", message = "o campo CÓDIGO INICIAL deve ser preenchido somente com numeros")
    @Length(max = 30, message = "O campo CÓDIGO INICIAL não pode conter mais de {max} caracteres")
    private String NumeroImagem;
    
      @NotNull(message = "O campo CAIXA não pode ser nulo")
    @NotBlank(message = "O campo CAIXA deve ser preenchido")
    @Pattern(regexp = "[0-9]+", message = "o campo CAIXA deve ser preenchido somente com numeros")
    @Length(max = 10, message = "O campo CAIXA não pode conter mais de {max} caracteres")
    private String localArmazenado;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.idIFotografiaAerea);
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
        final FotografiaAerea other = (FotografiaAerea) obj;
        if (!Objects.equals(this.idIFotografiaAerea, other.idIFotografiaAerea)) {
            return false;
        }
        return true;
    }

    public void setImagem(FotografiaAerea imagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getImagemFotografiaAerea() {
        return imagemFotografiaAerea;
    }

    public void setImagemFotografiaAerea(String imagemFotografiaAerea) {
        this.imagemFotografiaAerea = imagemFotografiaAerea;
    }
    
    public String getNumeroImagem() {
        return NumeroImagem;
    }

    public void setNumeroImagem(String codInical) {
        this.NumeroImagem = codInical;
    }
 public String getLocalArmazenado() {
        return localArmazenado;
    }

    public void setLocalArmazenado(String localArmazenado) {
        this.localArmazenado = localArmazenado;
    }
   
}
