/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cprm.gov.gileno.macedo.CONTROLE;

import cprm.gov.gileno.macedo.MODELO.Caderneta;
import cprm.gov.gileno.macedo.MODELO.FotografiaAerea;
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

public class FotografiaAereaDao {
  
    public EntityManager getEM(){
     EntityManagerFactory factory = Persistence.createEntityManagerFactory("ArqMapasPU");
        
    return factory.createEntityManager();
    }
  
    
      public FotografiaAerea salvarFotografiaAerea(FotografiaAerea  img)throws Exception {
          JOptionPane.showMessageDialog(null,"ID" + img.getIdIFotografiaAerea());
            EntityManager em = getEM();

            try{
            em.getTransaction().begin();
            if(img.getIdIFotografiaAerea() == null){
                em.persist(img);// salva  as Imagens do mapa no banco de dados
                
              }else{
                if(!em.contains(img)){
                   if(em.find(FotografiaAerea.class,img.getIdIFotografiaAerea())== null){
                     throw new Exception("Erro ao Salvar a Imagem");
                   }
                }
                img = em.merge(img);// faz update no banco de dados
             
            }     
            em.getTransaction().commit();
            }finally{
               em.close();

                }
            return img;
          }
      
      public void removeFotografiaAerea(Long id)throws Exception{
          
              EntityManager em = getEM();
              FotografiaAerea img = em.find(FotografiaAerea.class, id);
             try {
                  em.getTransaction().begin();
                  em.remove(img);// remove o imagem selecionada
                  em.getTransaction().commit();
              } finally {
                 em.close();
          }
          
      }
      
       public FotografiaAerea consultaIFotografiaAereaPorId(Long id){
              EntityManager em = getEM();
              FotografiaAerea img = null;
              try {
               em.getTransaction().begin();
               img = em.find(FotografiaAerea.class, id);
               em.getTransaction().commit();
              } finally {
               em.close();
             }
            
             return img;
          }
          
       public List<FotografiaAerea> consultaTodasFotografiaAerea(){
           EntityManager em = getEM();
           List<FotografiaAerea> listFotografiaAerea;
           
           try {
               Query q = em.createNamedQuery("FotografiaAerea.ConsultaTodos");
               listFotografiaAerea = q.getResultList();
           } catch (Exception e) {
               listFotografiaAerea = new ArrayList();
           }finally{
           em.close();
            }
         return listFotografiaAerea;
      
       }
       
     
       
      
    public int getImagemMapasCount() {
        EntityManager em = getEM();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FotografiaAerea> rt = cq.from(FotografiaAerea.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
  public List<FotografiaAerea> consultaImagensPorFotografiaAerea(Long ftaID){
           EntityManager em = getEM();
           List<FotografiaAerea> listFotografiaAerea;
           
           try {
               Query q = em.createNamedQuery("FotografiaAerea.consultaPorFotografiaAereaID");
               q.setParameter("mapaId", ftaID);
               listFotografiaAerea = q.getResultList();
           } catch (Exception e) {
               listFotografiaAerea = new ArrayList();
           }finally{
           em.close();
            }
         return listFotografiaAerea;
      
       }
       
     
    
}


