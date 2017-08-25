
package MODELO;

import CONTROLE.MapaIndiceDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gileno.macedo
 */
public class TableMapaIndice extends AbstractTableModel{
    
    private List<MapaIndice>  listaMapaIndice;
    private MapaIndiceDao mapaIndiceDao = new MapaIndiceDao();
    

    public TableMapaIndice(){
     this.listaMapaIndice = new ArrayList<>();
     listaMapaIndice = mapaIndiceDao.listarMapaIndice();

}
    
    @Override
    public int getRowCount() {
       return listaMapaIndice.size();
    }

    @Override
    public int getColumnCount() {
      return 3;
    }
    
    @Override
     public String getColumnName(int coluna){
        switch (coluna) {
            case 0:
                return "Folha Mapa Índice";
            case 1:
                return "Orgão Execultor";
            case 2:
                return "Escala";
            
           
              
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      MapaIndice mpIndice = listaMapaIndice.get(rowIndex);
     
          switch (columnIndex) {

            case 0:
                return mpIndice.getFolhaFotografia();
            case 1:
                return  mpIndice.getOrgaoExecutor();
            case 2:
                return mpIndice.getEscala();
            
            
           default:
                return "";
        }
    }
    
        public MapaIndice getLinhaMapaIndice(int linha) {
        return listaMapaIndice.get(linha);
    }

    
public void atualizaMapaIndice(MapaIndice f)  throws Exception { 
        listaMapaIndice.remove(f);
        fireTableRowsInserted(listaMapaIndice.size() - 1, listaMapaIndice.size() - 1);

    }
    
    public void salvaMapaIndice(MapaIndice mpIndice) throws Exception{
    mpIndice = mapaIndiceDao.salvarMapaIndice(mpIndice);
    listaMapaIndice.add(mpIndice);
    fireTableRowsInserted(listaMapaIndice.size() - 1, listaMapaIndice.size() - 1);
    
    }
     public void removeMapaIndice(MapaIndice mpIndice) {

        listaMapaIndice.remove(mpIndice);
        fireTableRowsInserted(listaMapaIndice.size() - 1, listaMapaIndice.size() - 1);
    }
    
      public void consultaMapaIndicePorNome(String mpIndiceFolha) {
        this.listaMapaIndice = new ArrayList();
        listaMapaIndice = mapaIndiceDao.consultarFotografiaPorFolha(mpIndiceFolha);
        fireTableRowsInserted(listaMapaIndice.size() - 1, listaMapaIndice.size() - 1);
    }
}