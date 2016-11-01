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
public class MapasTableModel extends AbstractTableModel{
    
      public List<Mapas> ListMapas;
    private final  MapasDao mDao = new MapasDao();
    


    public MapasTableModel() {
        this.ListMapas = new ArrayList();
         ListMapas = mDao.consultaMapas();         

    }

    public Mapas getLinhaCaderneta(int linha) {
        return ListMapas.get(linha);
    }

      @Override
    public int getRowCount() {
        return ListMapas.size();
    }

      @Override
    public int getColumnCount() {
        return 8;
    }

      @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Título";
            case 1:
                return "Folha";
            case 2:
                return "Editora";
            case 3:
                return "Ano";
            case 4:
                return "Tipo";
            case 5:
                return "Escala";
            case 6:
                return "Gaveta";     
             case 7:
                return "Quantidade";  
           
                
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
            case 2:
                return m.getEditora();
            case 3:
                return m.getAno();
            case 4:
                return m.getTipo();
            case 5:
                return m.getEscala();
            case 6:
                return m.getGaveta();
             case 7:
                return m.getQuantidade();
         
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
