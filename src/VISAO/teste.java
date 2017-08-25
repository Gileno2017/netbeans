/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import CONTROLE.FotografiaAereaDao;
import MODELO.FotografiaAerea;
import MODELO.MapaIndice;
import java.util.List;

/**
 *
 * @author gileno.macedo
 */
public class teste {
    public static void main(String[] args) {
        FotografiaAereaDao fd = new FotografiaAereaDao();
        
        List<FotografiaAerea> foto = fd.consultaImagensPorFotografiaAerea(8l);
        for (FotografiaAerea fotografiaAerea : foto) {
            System.out.println("Foto ,............." +fotografiaAerea.getNumeroImagem());
             
        }
    }
}
