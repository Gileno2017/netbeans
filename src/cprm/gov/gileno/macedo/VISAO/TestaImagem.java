/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cprm.gov.gileno.macedo.VISAO;

import cprm.gov.gileno.macedo.CONTROLE.MapaIndiceDao;
import cprm.gov.gileno.macedo.CONTROLE.FotografiaAereaDao;
import cprm.gov.gileno.macedo.MODELO.MapaIndice;
import cprm.gov.gileno.macedo.MODELO.FotografiaAerea;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author gileno.macedo
 */
public class TestaImagem {

    public static void main(String[] args) throws Exception {

        FotografiaAerea imagem = new FotografiaAerea();// cria  um novo objeto da class imagemmapas
        FotografiaAereaDao imDao = new FotografiaAereaDao();// cria um novo objeto da class imagemMapasDao
        List<FotografiaAerea> lista = imDao.consultaImagensPorFotografiaAerea(1353l);// traz os objetos contido no banco de dados
        int tamanho = lista.size();// obtem o tanho da lista de imagens
        Object[] vetor = new Object[tamanho];// cria um novo objeto com o tamnho da lista
        vetor = lista.toArray();// recebe o array da lista e passa para um objeto
        for (int i = 0; vetor.length > i; i++) {            // percorre os objetos um a um
            imagem = (FotografiaAerea) vetor[i];// faz o cast tranformando cada posicçao  em seu respectivo objeto
            System.out.println("Codigo " + imagem.getIdIFotografiaAerea());
        }
    }
}
