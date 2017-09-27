/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cprm.gov.gileno.macedo.CONTROLE;

import cprm.gov.gileno.macedo.MODELO.Caderneta;
import cprm.gov.gileno.macedo.MODELO.Usuario;
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
public class UsuarioDao {
     public EntityManager getEM(){
     EntityManagerFactory factory = Persistence.createEntityManagerFactory("ArqMapasPU");
        
    return factory.createEntityManager();
    }
  
    
      public Usuario salvarUsuario(Usuario usuario)throws Exception {
            EntityManager em = getEM();

            try{
            em.getTransaction().begin();
            if(usuario.getCod_usuario()== null){
                em.persist(usuario);// salva mapa no banco de dados
                
              }else{
                if(!em.contains(usuario)){
                   if(em.find(Usuario.class,usuario.getCod_usuario()) == null){
                     throw new Exception("Erro ao Cadastrar o Usuario");
                   }
                }
                usuario = em.merge(usuario);// faz update no banco de dados
               // JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso!!");
            }     
            em.getTransaction().commit();
            }finally{
               em.close();

                }
            return usuario;
          }
      
      public void removeUsuario(Long id)throws Exception{
          
              EntityManager em = getEM();
              Usuario usuario = em.find(Usuario.class, id);
             try {
                  em.getTransaction().begin();
                  em.remove(usuario);// remove o mapa selecionado
                  em.getTransaction().commit();
              } finally {
                 em.close();
          }
          
      }
      
       public Usuario consultaUsuario(Long id){
              EntityManager em = getEM();
              Usuario usuario = null;
              try {
               em.getTransaction().begin();
               usuario = em.find(Usuario.class, id);
               em.getTransaction().commit();
              } finally {
               em.close();
             }            
             return usuario;
          }
          
       public List<Usuario> consultaTodasUsuarios(){
           EntityManager em = getEM();
           List<Usuario> listUsuarios;
           
           try {
               Query q = em.createNamedQuery("Usuario.ConsultaTodos");
               listUsuarios = q.getResultList();
           } catch (Exception e) {
               listUsuarios = new ArrayList();
           }finally{
           em.close();
            }
         return listUsuarios;      
       }
       
     
    public int getUsuarioCount() {
        EntityManager em = getEM();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Usuario> consultarUsuariosPorNome(String usuario) {
    EntityManager entityManager = getEM();
    Query query = entityManager.createNamedQuery("Usuario.ConsultaporNome");
    query.setParameter("usuario", usuario );    
    // query.setParameter("senha", senha );
    return   query.getResultList();
  }
}
