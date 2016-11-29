/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import MODELO.Caderneta;
import MODELO.ImagemMapas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author gileno.macedo
 */

public class ImagemMapasDao {
  
    public EntityManager getEM(){
     EntityManagerFactory factory = Persistence.createEntityManagerFactory("ArqMapasPU");
        
    return factory.createEntityManager();
    }
  
    
      public ImagemMapas salvarImagemMapas(ImagemMapas  img)throws Exception {
            EntityManager em = getEM();

            try{
            em.getTransaction().begin();
            if(img.getIdImagemMapas() == null){
                em.persist(img);// salva  as Imagens do mapa no banco de dados
                
              }else{
                if(!em.contains(img)){
                   if(em.find(ImagemMapas.class,img.getIdImagemMapas())== null){
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
      
      public void removeImagemMapas(Long id)throws Exception{
          
              EntityManager em = getEM();
              ImagemMapas img = em.find(ImagemMapas.class, id);
             try {
                  em.getTransaction().begin();
                  em.remove(img);// remove o imagem selecionada
                  em.getTransaction().commit();
              } finally {
                 em.close();
          }
          
      }
      
       public ImagemMapas consultaImagemMapasPorId(Long id){
              EntityManager em = getEM();
              ImagemMapas img = null;
              try {
               em.getTransaction().begin();
               img = em.find(ImagemMapas.class, id);
               em.getTransaction().commit();
              } finally {
               em.close();
             }
            
             return img;
          }
          
       public List<ImagemMapas> consultaTodasImagemMapas(){
           EntityManager em = getEM();
           List<ImagemMapas> listImagemMapas;
           
           try {
               Query q = em.createNamedQuery("ImagemMapas.ConsultaTodos");
               listImagemMapas = q.getResultList();
           } catch (Exception e) {
               listImagemMapas = new ArrayList();
           }finally{
           em.close();
            }
         return listImagemMapas;
      
       }
       
     
       
      
    public int getImagemMapasCount() {
        EntityManager em = getEM();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ImagemMapas> rt = cq.from(ImagemMapas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
}


