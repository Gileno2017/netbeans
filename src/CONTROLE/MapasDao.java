/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import MODELO.Mapas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author gileno.macedo
 */
public class MapasDao {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ArqMapasPU");

        return factory.createEntityManager();
    }

    public Mapas salvarMapas(Mapas mapa) throws Exception {
        EntityManager em = getEM();

        try {
            em.getTransaction().begin();
            if (mapa.getCodMapa() == null) {
                em.persist(mapa);// salva mapa no banco de dados
                JOptionPane.showMessageDialog(null, "Dados Salvo com Sucesso!!");
            } else {
                if (!em.contains(mapa)) {
                    if (em.find(Mapas.class, mapa.getCodMapa()) == null) {
                        throw new Exception("Erro ao Atulaizar o Mapa");
                    }
                }
                mapa = em.merge(mapa);// faz update no banco de dados
                JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso!!");
            }
            em.getTransaction().commit();
        } finally {
            em.close();

        }
        return mapa;
    }

    public void removeMapas(Long id) throws Exception {
        EntityManager em = getEM();
        Mapas mp = em.find(Mapas.class, id);
        try {
            em.getTransaction().begin();
            em.remove(mp);// remove o mapa selecionado
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Mapas consultaMapasId(Long id) {
        EntityManager em = getEM();
        Mapas mp = null;
        try {
            em.getTransaction().begin();
            mp = em.find(Mapas.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return mp;
    }

    public List<Mapas> consultaMapas() {
        EntityManager em = getEM();
        List<Mapas> listMapas;
        try {
            Query q = em.createNamedQuery("Mapas.ConsultaTodos");
            listMapas = q.getResultList();
        } catch (Exception e) {
            listMapas = new ArrayList();
        } finally {
            em.close();
        }
        return listMapas;
    }

    public List<Mapas> consultarMaoasPorFolha(String folha) {
        EntityManager entityManager = getEM();
        Query query = entityManager.createNamedQuery("Mapas.ConsultaporFolha");
        query.setParameter("folha", "%" + folha + "%");

        return query.getResultList();
    }

    public int getMapasCount() {
        EntityManager em = getEM();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mapas> rt = cq.from(Mapas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Mapas removeMapas(Mapas mapas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
