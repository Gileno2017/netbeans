/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLE.FotografiaDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gileno.macedo
 */
public class FotografiasTableModel extends AbstractTableModel{
    
    private List<Fotografias>  listaFotos;
    private FotografiaDao fotografiaDao = new FotografiaDao();
    

    public FotografiasTableModel(){
     this.listaFotos = new ArrayList<>();
   listaFotos = fotografiaDao.listarFotografias();

}
    
    @Override
    public int getRowCount() {
       return listaFotos.size();
    }

    @Override
    public int getColumnCount() {
      return 8;
    }
    
    @Override
     public String getColumnName(int coluna){
        switch (coluna) {
            case 0:
                return "Folha";
            case 1:
                return "Cod_Inicial";
            case 2:
                return "Cod_Final";
            case 3:
                return "Escala";
            case 4:
                return "Orgão Execultor";
            case 5:
                return "Caixa";
            case 6:
                return "Total";           
                   case 7:
                return "ID MAPAS";   
              
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      Fotografias f = listaFotos.get(rowIndex);
     
          switch (columnIndex) {

            case 0:
                return f.getMapas().getFolha();
            case 1:
                return f.getCodInical();
            case 2:
                return f.getCodFinal();
            case 3:
                return f.getMapas().getEscala();
            case 4:   
                return  f.getOrgaoExecutor();
             case 5:
                return  f.getLocalArmazenado();
              case 6:
                return  f.getTotaFotografias();
                case 7:
                return  f.getMapas().getCodMapa();
                
            default:
                return "";
        }
    }
    
        public Fotografias getLinhaFotografias(int linha) {
        return listaFotos.get(linha);
    }

    
public void atualizaListadeFotos(Fotografias f)  throws Exception { 
        listaFotos.remove(f);
        fireTableRowsInserted(listaFotos.size() - 1, listaFotos.size() - 1);

    }
    
    public void salvaFotografias(Fotografias f) throws Exception{
    f = fotografiaDao.salvarFotografias(f);
    listaFotos.add(f);
    fireTableRowsInserted(listaFotos.size() - 1, listaFotos.size() - 1);
    
    }
     public void removeFotografia(Fotografias foto) {

        listaFotos.remove(foto);
        fireTableRowsInserted(listaFotos.size() - 1, listaFotos.size() - 1);
    }
    
}