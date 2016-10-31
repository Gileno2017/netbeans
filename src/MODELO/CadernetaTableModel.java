/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLE.CadernetaDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gileno.macedo
 */
public class CadernetaTableModel extends AbstractTableModel{
     public List<Caderneta> listCadernetas;
     private final   CadernetaDao cDao = new CadernetaDao();     
   
     
    public CadernetaTableModel() {
                this.listCadernetas = new ArrayList();
                listCadernetas = cDao.consultaTodasCadernetas();
           
    }
   
    public Caderneta getLinhaCaderneta(int linha){
      return listCadernetas.get(linha);
    }
    
     @Override
    public int getRowCount() {
      return listCadernetas.size();
    }

    @Override
    public int getColumnCount() {
       return 7;
    }
     
     @Override
     public String getColumnName(int coluna){
         switch(coluna){
             case 0:
                 return "Projeto" ;
             case 1:
                 return "Geólogo";
             case 2:
                 return  "Tipo";
             case 3:
                  return "Prateleira" ;               
             case 4:                 
                    return "C_Custo" ;
             case 5:
                  return "Ano" ;
             case 6:
                 return "Quantidade";                 
             default:
             return "";   
         }
                       
     }
     
     
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Caderneta cad = listCadernetas.get(rowIndex);
         
    
     switch(columnIndex){
         
         case 0:
                 return cad.getProjeto();           
           case 1:
                 return cad.getGeologo();               
           case 2:
                 return cad.getTipo();
           case 3:                   
                  return cad.getQtd();
           case 4:
                   return cad.getCentroCusto();               
           case 5:
                  return cad.getAno();
               
           case 6:
                  return cad.getPrateleira();
                  
                default:
               
               return "";               
           }
          
     
         }
    
  
    
      public void AdcionarCaderneta(Caderneta c) throws Exception{
           c = cDao.salvarCadernetas(c);
           listCadernetas.add(c);
           fireTableRowsInserted(listCadernetas.size() -1 , listCadernetas.size() -1);      
          
      }
      
        public void RemoveCaderneta(Caderneta c){           
           listCadernetas.remove(c);
           fireTableRowsInserted(listCadernetas.size() -1 , listCadernetas.size() -1);      
      }
          public void atualizaListadeCadernetas(Caderneta c) throws Exception{              
              listCadernetas.remove(c);            
              fireTableRowsInserted(listCadernetas.size() -1 , listCadernetas.size() -1);   
             
      }
          
          public void consultaCadernetaPorNome(String geo){
                this.listCadernetas = new ArrayList();                  
                listCadernetas = cDao.consultarTodosGeologo(geo);
                fireTableRowsInserted(listCadernetas.size() -1 , listCadernetas.size() -1);   
          
          }
        
          
}
