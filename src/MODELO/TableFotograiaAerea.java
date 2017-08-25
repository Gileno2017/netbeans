/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLE.FotografiaAereaDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gileno.macedo
 */
public class TableFotograiaAerea extends AbstractTableModel{
    
    private List<FotografiaAerea>  listaFotografiaAerea;
    private final FotografiaAereaDao fotografiaAereaDao = new FotografiaAereaDao();
    

   public TableFotograiaAerea(  ){
   this.listaFotografiaAerea = new ArrayList<>(0);
  

}
    
    @Override
    public int getRowCount() {
        
       return listaFotografiaAerea.size();
        
    }

    @Override
    public int getColumnCount() {
      return 3;
    }
    
    @Override
     public String getColumnName(int coluna){
        switch (coluna) {
            case 0:
                return "Folha Mapa";
            case 1:
                return "Folha Fotografia Aerea";
            case 2:
                return "Numero Imagem Radar";
           
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FotografiaAerea fotografiaAerea = listaFotografiaAerea.get(rowIndex);
     
          switch (columnIndex) {

            case 0:
               // return fotografiaAerea.getMpIndiceImagem().getMapas().getFolha();
                return fotografiaAerea.getMpIndiceImagem().getMapas().getFolha();
                        
            case 1:
                return fotografiaAerea.getMpIndiceImagem().getFolhaFotografia();
            case 2:
                return fotografiaAerea.getNumeroImagem();
            
            default:
                return "";
        }
    }
    
        public FotografiaAerea getLinhaFotografiaAerea(int linha) {
        return listaFotografiaAerea.get(linha);
    }

    
public void atualizaListadeFotografiaAerea(FotografiaAerea f)  throws Exception { 
        listaFotografiaAerea.remove(f);
        fireTableRowsInserted(listaFotografiaAerea.size() - 1, listaFotografiaAerea.size() - 1);

    }
    
    public void salvaFotografiaAerea(FotografiaAerea im) throws Exception{
    im  = fotografiaAereaDao.salvarFotografiaAerea(im);
    listaFotografiaAerea.add(im);
    fireTableRowsInserted(listaFotografiaAerea.size() - 1, listaFotografiaAerea.size() - 1);
    
    }
     public void removeFotografiaAerea(FotografiaAerea im) {

        listaFotografiaAerea.remove(im);
        fireTableRowsInserted(listaFotografiaAerea.size() - 1, listaFotografiaAerea.size() - 1);
    }
     
     public void listaFotografiaPorId(long id){    
     
    this.listaFotografiaAerea = new ArrayList<>();
   listaFotografiaAerea = fotografiaAereaDao.consultaImagensPorFotografiaAerea(id);
                
     }
    
}