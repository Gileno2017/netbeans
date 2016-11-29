/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import CONTROLE.FotografiaDao;
import CONTROLE.ImagemMapasDao;
import MODELO.Fotografias;
import MODELO.ImagemMapas;

/**
 *
 * @author gileno.macedo
 */
public class TestaImagem {

    public static void main(String[] args) throws Exception {

        ImagemMapas imagem = new ImagemMapas();
        ImagemMapasDao imDao = new ImagemMapasDao();
        Fotografias foto = new Fotografias();
      

        foto.setCod_Fotografia(1276l);
   //     imagem.setImagem("teste");
        imagem.setFotos(foto);
        imDao.salvarImagemMapas(imagem);
    }
}
