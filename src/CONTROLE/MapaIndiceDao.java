/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import MODELO.FotografiaAerea;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import MODELO.MapaIndice;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author gileno.macedo
 */
public class MapaIndiceDao {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ArqMapasPU");
        return factory.createEntityManager();
    }

    public MapaIndice salvarMapaIndice(MapaIndice fotos) throws Exception {
        EntityManager em = getEM();

        try {
            em.getTransaction().begin();
            if (fotos.getCod_MapaIndice() == null) {
                em.persist(fotos);// salva fotografia no banco de dados
                JOptionPane.showMessageDialog(null, "Dados Salvo com Sucesso!!");
            } else {
                if (!em.contains(fotos)) {
                    if (em.find(MapaIndice.class, fotos.getCod_MapaIndice()) == null) {
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

    public List<MapaIndice> listarMapaIndice() {
        EntityManager em = getEM();
        List<MapaIndice> listMapaIndice;

        try {
            Query q = em.createNamedQuery("MapaIndice.ConsultaTodos");
            listMapaIndice = q.getResultList();
        } catch (Exception e) {
            listMapaIndice = new ArrayList();
        } finally {
            em.close();
        }
        return listMapaIndice;
    }
    
      public MapaIndice consultaMapaIndicePorId(Long id){
              EntityManager em = getEM();
              MapaIndice mpIndice = null;
              try {
               em.getTransaction().begin();
               mpIndice = em.find(MapaIndice.class, id);
               em.getTransaction().commit();
              } finally {
               em.close();
             }
            
             return mpIndice;
          }
    public void removeMapaIndice(Long id) {
        EntityManager em = getEM();
        MapaIndice mpIndice = em.find(MapaIndice.class, id);
        try {
            em.getTransaction().begin();
            em.remove(mpIndice);// remove a fotografia selecionada
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public List<MapaIndice> consultarFotografiaPorFolha(String folhaMapaIndice) {
        EntityManager entityManager = getEM();
        Query query = entityManager.createNamedQuery("MapaIndice.ConsultaMapaIndiceporFolha");
        query.setParameter("folhaMapaIndice", "%" + folhaMapaIndice + "%");

        return query.getResultList();
    }
    
     public int getMapaIndiceCount() {
        EntityManager em = getEM();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MapaIndice> rt = cq.from(MapaIndice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
