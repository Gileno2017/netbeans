/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cprm.gov.gileno.macedo.MODELO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "MapaIndice.ConsultaTodos",
            query = "SELECT e FROM  MapaIndice e"),
    @NamedQuery(name = "MapaIndice.ConsultaMapaIndiceporFolha", query = "SELECT e FROM MapaIndice e WHERE e.folhaMapaIndice LIKE :folhaMapaIndice"),})

public class MapaIndice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq_fotos", sequenceName = "seq_cod_fotografia", allocationSize = 1)
    @GeneratedValue(generator = "seq_fotos", strategy = GenerationType.SEQUENCE)

    private Long cod_MapaIndice;

     @NotNull(message = "O campo FOLHA não pode ser nulo")
    @NotBlank(message = "O campo FOLHA deve ser preenchido")
     // @Pattern(regexp = "([a-zA-Z]{2}\\.[0-9]{2}?\\-[a-zA-Z]{1}?\\-[a-zA-Z]{1}?\\-[a-zA-Z]{3}?)|([a-zA-Z]{2}\\.[0-9]{2}?\\-[a-zA-Z]{1}?\\-[a-zA-Z]{1}?)",
     //message = "o campo FOLHA deve ser preenchido  com o formato (XX.11-X-X)  ou  (XX.11-X-X-1)")
    @Length(max = 20, message = "O FOLHA não pode conter mais de {max} caracteres")
    private String folhaMapaIndice;

    

    @NotNull(message = "O campo EXECUTOR não pode ser nulo")
    @NotBlank(message = "O campo  EXECUTOR  deve ser preenchido")
    @Pattern(regexp = "[A-Z\\/d\\s]+", message = "o campo EXECUTOR deve ser preenchido somente com letras e barra  / ")
    @Length(max = 30, message = "O campo EXECUTOR não pode conter mais de {max} caracteres")
    private String orgaoExecutor;

  
    
    @NotNull(message = " Selecione um campo na Tabela de Mapas! ")
    @NotBlank(message = "Selecione um campo na Tabela de Mapas! ")    
    private String caminhoMapaIndeceBD;    
   
    private String caminhoPastaBD;
    
    @NotNull(message = " O campo  Escala  não pode ser nulo")
    @NotBlank(message = "Selecione a Escala! ") 
    private String escala;

    @ManyToOne
    @JoinColumn(name = "codMapa")
    private Mapas mapas;

    @OneToMany(mappedBy = "mpIndiceImagem", cascade = CascadeType.ALL)
    @JoinColumn(name = "idImagemMapas")    
    private  List<FotografiaAerea> imagemMapas;

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
        final MapaIndice other = (MapaIndice) obj;
        if (!Objects.equals(this.cod_MapaIndice, other.cod_MapaIndice)) {
            return false;
        }
        return true;
    }

    public Long getCod_MapaIndice() {
        return cod_MapaIndice;
    }

    public void setCod_MapaIndice(Long cod_MapaIndice) {
        this.cod_MapaIndice = cod_MapaIndice;
    }

   

    

    public String getOrgaoExecutor() {
        return orgaoExecutor;
    }

    public void setOrgaoExecutor(String orgaoExecutor) {
        this.orgaoExecutor = orgaoExecutor;
    }

   

    public Mapas getMapas() {
        return mapas;
    }

    public void setMapas(Mapas mapas) {
        this.mapas = mapas;
    }

    public List<FotografiaAerea> getImagemMapas() {
        return imagemMapas;
    }

    public void setImagemMapas(List<FotografiaAerea> imagemMapas) {
        this.imagemMapas = imagemMapas;
    }

    public String getCaminhoMapaIndeceBD() {
        return caminhoMapaIndeceBD;
    }

    public void setCaminhoMapaIndeceBD(String caminhoMapaIndeceBD) {
        this.caminhoMapaIndeceBD = caminhoMapaIndeceBD;
    }


    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getFolhaFotografia() {
        return folhaMapaIndice;
    }

    public void setFolhaFotografia(String folhaFotografia) {
        this.folhaMapaIndice = folhaFotografia;
    }

    public String getFolhaMapaIndice() {
        return folhaMapaIndice;
    }

    public String getCaminhoPastaBD() {
        return caminhoPastaBD;
    }

    public void setFolhaMapaIndice(String folhaMapaIndice) {
        this.folhaMapaIndice = folhaMapaIndice;
    }

    public void setCaminhoPastaBD(String caminhoPastaBD) {
        this.caminhoPastaBD = caminhoPastaBD;
    }

   

}
