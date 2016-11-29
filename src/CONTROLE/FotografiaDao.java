/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import MODELO.Fotografias;

/**
 *
 * @author gileno.macedo
 */
public class FotografiaDao {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ArqMapasPU");
        return factory.createEntityManager();
    }

    public Fotografias salvarFotografias(Fotografias fotos) throws Exception {
        EntityManager em = getEM();

        try {
            em.getTransaction().begin();
            if (fotos.getCod_Fotografia() == null) {
                em.persist(fotos);// salva fotografia no banco de dados
                JOptionPane.showMessageDialog(null, "Dados Salvo com Sucesso!!");
            } else {
                if (!em.contains(fotos)) {
                    if (em.find(Fotografias.class, fotos.getCod_Fotografia()) == null) {
                        throw new Exception("Erro ao Atulaizar a Fotografia");
                    }
                }
                fotos = em.merge(fotos);// faz update no banco de dados
                JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso!!");
            }
            em.getTransaction().commit();
        } finally {
            em.close();

        }
        return fotos;
    }

    public List<Fotografias> listarFotografias() {
        EntityManager em = getEM();
        List<Fotografias> listFotografias;

        try {
            Query q = em.createNamedQuery("Fotografias.ConsultaTodos");
            listFotografias = q.getResultList();
        } catch (Exception e) {
            listFotografias = new ArrayList();
        } finally {
            em.close();
        }
        return listFotografias;
    }
    
      public Fotografias consultaFotografiasPorId(Long id){
              EntityManager em = getEM();
              Fotografias foto = null;
              try {
               em.getTransaction().begin();
               foto = em.find(Fotografias.class, id);
               em.getTransaction().commit();
              } finally {
               em.close();
             }
            
             return foto;
          }
    public void removeFotografias(Long id) {
        EntityManager em = getEM();
        Fotografias foto = em.find(Fotografias.class, id);
        try {
            em.getTransaction().begin();
            em.remove(foto);// remove a fotografia selecionada
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
