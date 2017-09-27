package cprm.gov.gileno.macedo.MODELO;


import cprm.gov.gileno.macedo.MODELO.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gileno.macedo
 */
public class Sessao{
   private static Sessao instance = null;
   public Usuario usuario;
   private Sessao(){
   }
   public void setUsuario(Usuario usuario){
      this.usuario = usuario;
   }
   public Usuario getUsuario(){
       return usuario;
   }
   public static Sessao getInstance(){
         if(instance == null){
               instance = new Sessao();
         }
        return instance;
   }
}