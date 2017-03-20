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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author gileno.macedo
 */
public class TestaImagem {

    public static void main(String[] args) throws Exception {

        ImagemMapas imagem = new ImagemMapas();// cria  um novo objeto da class imagemmapas
        ImagemMapasDao imDao = new ImagemMapasDao();// cria um novo objeto da class imagemMapasDao
        List<ImagemMapas> lista = imDao.consultaImagensPorFotografias(1353l);// traz os objetos contido no banco de dados
        int tamanho = lista.size();// obtem o tanho da lista de imagens
        Object[] vetor = new Object[tamanho];// cria um novo objeto com o tamnho da lista
        vetor = lista.toArray();// recebe o array da lista e passa para um objeto
        for (int i = 0; vetor.length > i; i++) {            // percorre os objetos um a um
            imagem = (ImagemMapas) vetor[i];// faz o cast tranformando cada posicçao  em seu respectivo objeto
            System.out.println("Codigo " + imagem.getIdImagemMapas());
        }
    }
}
