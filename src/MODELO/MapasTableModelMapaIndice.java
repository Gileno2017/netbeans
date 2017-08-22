/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLE.MapasDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gileno.macedo
 */
public class MapasTableModelMapaIndice extends AbstractTableModel{
    
      public List<Mapas> ListMapas;
    private final  MapasDao mDao = new MapasDao();
    


    public MapasTableModelMapaIndice() {
        this.ListMapas = new ArrayList();
         ListMapas = mDao.consultaMapas();         

    }

    public Mapas getLinhaMapas(int linha) {
        return ListMapas.get(linha);
    }

      @Override
    public int getRowCount() {
        return ListMapas.size();
    }

      @Override
    public int getColumnCount() {
        return 2;
    }

      @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Título";
            case 1:
                return "Folha";            
                
            default:
                return "";
        }

    }
    


      @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mapas m  = ListMapas.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return m.getTitulo();
            case 1:
                return m.getFolha();
           
         
            default:
                return "";
        }

    }

    public void adicionaMapa(Mapas m) throws Exception {
        m = mDao.salvarMapas(m);  
        ListMapas.add(m);
        fireTableRowsInserted(ListMapas.size() - 1, ListMapas.size() - 1);
      
    }

    public void removeMapa(Mapas m) {        
        ListMapas.remove(m);      
        fireTableRowsInserted(ListMapas.size() - 1, ListMapas.size() - 1);
    }

    public void atualizaListadeMapa(Mapas m) throws Exception {
        ListMapas.remove(m);             
        fireTableRowsInserted(ListMapas.size() - 1, ListMapas.size() - 1);

    }

    public void consultaMapaPorNome(String geo) {
        this.ListMapas = new ArrayList();
        ListMapas = mDao.consultarMaoasPorFolha(geo);
        fireTableRowsInserted(ListMapas.size() - 1, ListMapas.size() - 1);

    }
}
