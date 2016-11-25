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
    @NamedQuery(name = "Fotografias.ConsultaTodos",
            query = "SELECT e FROM  Fotografias e"), //        @NamedQuery(name="Fotografias.ConsultaporNome",
//                query="SELECT e FROM Fotografias e WHERE e.obra LIKE :obra"),
//        
//        @NamedQuery(name="Fotografias.ConsultaporFolha",
//                query="SELECT e FROM Fotografias e WHERE e.fotos = :fotos "),
})
public class Fotografias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq_fotos", sequenceName = "seq_cod_fotografia", allocationSize = 1)
    @GeneratedValue(generator = "seq_fotos", strategy = GenerationType.SEQUENCE)

    private Long cod_Fotografia;

    @NotNull(message = "O campo TOTAL deve ser preenchido")
     @NotBlank(message = "O campo TOTAL deve ser preenchido")
     @Pattern(regexp = "[0-9]+", message = "o campo TOTAL  deve ser preenchido somente com numeros")
     @Length(max = 20, message = "O TOTAL não pode conter mais de {max} caracteres")
    private String totaFotografias;

    @NotNull(message = "O campo CÓDIGO INICIAL não pode ser nulo")
    @NotBlank(message = "O campo CÓDIGO INICIAL deve ser preenchido")
    @Pattern(regexp = "[0-9]+", message = "o campo CÓDIGO INICIAL deve ser preenchido somente com numeros")
    @Length(max = 30, message = "O campo CÓDIGO INICIAL não pode conter mais de {max} caracteres")
    private String codInical;

    @NotNull(message = "O campo CÓDIGO FINAL não pode ser nulo")
    @NotBlank(message = "O campo CÓDIGO FINAL deve ser preenchido")
    @Pattern(regexp = "[0-9]+", message = "o campo CÓDIGO FINAL deve ser preenchido somente com numeros")
    @Length(max = 30, message = "O campo CÓDIGO FINAL não pode conter mais de {max} caracteres")
    private String codFinal;

    @NotNull(message = "O campo EXECUTOR não pode ser nulo")
    @NotBlank(message = "O campo  EXECUTOR  deve ser preenchido")
    @Pattern(regexp = "[A-Z\\/d\\s]+", message = "o campo EXECUTOR deve ser preenchido somente com letras e barra  / ")
    @Length(max = 30, message = "O campo EXECUTOR não pode conter mais de {max} caracteres")
    private String orgaoExecutor;

    
    @NotNull(message = "O campo CAIXA não pode ser nulo")
    @NotBlank(message = "O campo CAIXA deve ser preenchido")
    @Pattern(regexp = "[0-9]+", message = "o campo CAIXA deve ser preenchido somente com numeros")
    @Length(max = 10, message = "O campo CAIXA não pode conter mais de {max} caracteres")
    private String localArmazenado;

    @ManyToOne
    @JoinColumn(name = "codMapa")
    private Mapas mapas;

    
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
        final Fotografias other = (Fotografias) obj;
        if (!Objects.equals(this.cod_Fotografia, other.cod_Fotografia)) {
            return false;
        }
        return true;
    }

    public Long getCod_Fotografia() {
        return cod_Fotografia;
    }

    public void setCod_Fotografia(Long cod_Fotografia) {
        this.cod_Fotografia = cod_Fotografia;
    }

    public String getTotaFotografias() {
        return totaFotografias;
    }

    public void setTotaFotografias(String totaFotografias) {
        this.totaFotografias = totaFotografias;
    }

    public String getCodInical() {
        return codInical;
    }

    public void setCodInical(String codInical) {
        this.codInical = codInical;
    }

    public String getCodFinal() {
        return codFinal;
    }

    public void setCodFinal(String codFinal) {
        this.codFinal = codFinal;
    }

    public String getOrgaoExecutor() {
        return orgaoExecutor;
    }

    public void setOrgaoExecutor(String orgaoExecutor) {
        this.orgaoExecutor = orgaoExecutor;
    }

    public String getLocalArmazenado() {
        return localArmazenado;
    }

    public void setLocalArmazenado(String localArmazenado) {
        this.localArmazenado = localArmazenado;
    }

    public Mapas getMapas() {
        return mapas;
    }

    public void setMapas(Mapas mapas) {
        this.mapas = mapas;
    }

}
