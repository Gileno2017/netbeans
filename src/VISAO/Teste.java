/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import CONTROLE.FotografiaDao;
import MODELO.Fotografias;
import MODELO.Mapas;

/**
 *
 * @author gileno.macedo
 */
public class Teste {
    public static void main(String[] args) throws Exception {
        Fotografias fot = new Fotografias();
        Mapas mapas = new Mapas();
        FotografiaDao fotografiasDao = new FotografiaDao();
        fot.setCod_Fotografia(401l);
        fot.setCodFinal("16");
        fot.setCodInical("16");
        fot.setLocalArmazenado("16");
        fot.setOrgaoExecutor("MANAUS");
        fot.setTotaFotografias("200");
        mapas.setCodMapa(1l);
        fot.setMapas(mapas);
        fotografiasDao.salvarFotografias(fot);
        
    }
}
