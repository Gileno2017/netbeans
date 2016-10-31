/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import MODELO.Caderneta;
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

public class CadernetaDao {
    public EntityManager getEM(){
     EntityManagerFactory factory = Persistence.createEntityManagerFactory("ArqMapasPU");
        
    return factory.createEntityManager();
    }
  
    
      public Caderneta salvarCadernetas(Caderneta cc)throws Exception {
            EntityManager em = getEM();

            try{
            em.getTransaction().begin();
            if(cc.getCod_Caderneta() == null){
                em.persist(cc);// salva mapa no banco de dados
                
              }else{
                if(!em.contains(cc)){
                   if(em.find(Caderneta.class,cc.getCod_Caderneta()) == null){
                     throw new Exception("Erro ao Salvar a Caderneta");
                   }
                }
                cc = em.merge(cc);// faz update no banco de dados
                JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso!!");
            }     
            em.getTransaction().commit();
            }finally{
               em.close();

                }
            return cc;
          }
      
      public void removeCaderneta(Long id)throws Exception{
          
              EntityManager em = getEM();
              Caderneta cc = em.find(Caderneta.class, id);
             try {
                  em.getTransaction().begin();
                  em.remove(cc);// remove o mapa selecionado
                  em.getTransaction().commit();
              } finally {
                 em.close();
          }
          
      }
      
       public Caderneta consultaCadernetas(Long id){
              EntityManager em = getEM();
              Caderneta cc = null;
              try {
               em.getTransaction().begin();
               cc = em.find(Caderneta.class, id);
               em.getTransaction().commit();
              } finally {
               em.close();
             }
            
             return cc;
          }
          
       public List<Caderneta> consultaTodasCadernetas(){
           EntityManager em = getEM();
           List<Caderneta> listCadernetas;
           
           try {
               Query q = em.createNamedQuery("Caderneta.ConsultaTodos");
               listCadernetas = q.getResultList();
           } catch (Exception e) {
               listCadernetas = new ArrayList();
           }finally{
           em.close();
            }
         return listCadernetas;
      
       }
       
     
       
      
    public int getMapasCount() {
        EntityManager em = getEM();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caderneta> rt = cq.from(Caderneta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Caderneta> consultarTodosGeologo(String geologo) {
    EntityManager entityManager = getEM();
    Query query = entityManager.createNamedQuery("Caderneta.ConsultaporNome");
    query.setParameter("geologo", "%"+geologo+"%" );
  
    
    return query.getResultList();
  }

   
}


