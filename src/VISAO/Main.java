/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import CONTROLE.JasperReportConnectionFactory;
import MODELO.ReportUtils;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author gileno.macedo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JRException, SQLException {
        new Main().abrirRelatorioMapas();
    }

    public void abrirRelatorioMapas() throws JRException, SQLException {

        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/MapasPorFolha.jasper");
        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();
        String texto = JOptionPane.showInputDialog("Informe Iniciais da folha para consulta, seguido de %").toUpperCase();
        parametros.put("ConsultaPorFolha", texto);
        ReportUtils.openReport("Relatorio de Mapas ", inputStream, parametros,
                JasperReportConnectionFactory.getPostgresConnection());
    }
}
