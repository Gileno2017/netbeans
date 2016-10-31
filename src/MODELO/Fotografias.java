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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author gileno.macedo
 */
@Entity
  @NamedQueries({
        @NamedQuery(name="Fotografias.ConsultaTodos",
                query="SELECT e FROM  Fotografias e"),
            
//        @NamedQuery(name="Fotografias.ConsultaporNome",
//                query="SELECT e FROM Fotografias e WHERE e.obra LIKE :obra"),
//        
//        @NamedQuery(name="Fotografias.ConsultaporFolha",
//                query="SELECT e FROM Fotografias e WHERE e.fotos = :fotos "),
   
}) 
public class Fotografias implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq_fotos", sequenceName = "seq_cod_fotografia", allocationSize = 1)
    @GeneratedValue(generator = "seq_fotos", strategy = GenerationType.SEQUENCE)

    private Long cod_Fotografia;
    @Column(length = 30, nullable = false)
    private int totaFotografias;
    @Column(length = 30, nullable = false)
    private int codInical;
    @Column(length = 30, nullable = false)
    private int codFinal;

    @Column(length = 20, nullable = false)
    private String orgaoExecutor;

    @Column(length = 20, nullable = false)
    private int localArmazenado;

    @ManyToOne
    @JoinColumn(name = "codMapa")
    private Mapas mapas;

    public Long getCod_Fotografia() {
        return cod_Fotografia;
    }

    public void setCod_Fotografia(Long cod_Fotografia) {
        this.cod_Fotografia = cod_Fotografia;
    }

    public int getTotaFotografias() {
        return totaFotografias;
    }

    public void setTotaFotografias(int totaFotografias) {
        this.totaFotografias = totaFotografias;
    }

    public int getCodInical() {
        return codInical;
    }

    public void setCodInical(int codInical) {
        this.codInical = codInical;
    }

    public int getCodFinal() {
        return codFinal;
    }

    public void setCodFinal(int codFinal) {
        this.codFinal = codFinal;
    }

    public String getOrgaoExecutor() {
        return orgaoExecutor;
    }

    public void setOrgaoExecutor(String orgaoExecutor) {
        this.orgaoExecutor = orgaoExecutor;
    }

    public int getLocalArmazenado() {
        return localArmazenado;
    }

    public void setLocalArmazenado(int localArmazenado) {
        this.localArmazenado = localArmazenado;
    }

    public Mapas getMapas() {
        return mapas;
    }

    public void setMapas(Mapas mapas) {
        this.mapas = mapas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.cod_Fotografia);
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
        final Fotografias other = (Fotografias) obj;
        if (!Objects.equals(this.cod_Fotografia, other.cod_Fotografia)) {
            return false;
        }
        return true;
    }

}
