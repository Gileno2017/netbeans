/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author gileno.macedo
 */
public class TelaPrincipal extends javax.swing.JFrame implements Serializable {

    public TelaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("ArcMapa (Mapoteca Sureg_MA)");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jM_cadastrarMapas = new javax.swing.JMenuItem();
        jM_cadastrarfotografias = new javax.swing.JMenuItem();
        jM_cadastrarCadernetas = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jM_pesquisarMapas = new javax.swing.JMenuItem();
        jM_pesquisarfotografias = new javax.swing.JMenuItem();
        jM_pesquisarCaderneta = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jM_ListagemMapas = new javax.swing.JMenuItem();
        jM_ListagemPorFolha = new javax.swing.JMenuItem();
        jM_ConsultaPorGaveta = new javax.swing.JMenuItem();
        jM_ListagemPorTitulo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jM_cadastroMapa = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mapMapa_1.JPG"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 4, true));

        jMenuBar1.setMaximumSize(new java.awt.Dimension(179, 900));

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/computer.png"))); // NOI18N
        jMenu2.setToolTipText("Cadastrar Dados");
        jMenu2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenu2.setIconTextGap(5);
        jMenu2.setInheritsPopupMenu(true);

        jM_cadastrarMapas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jM_cadastrarMapas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_512x512_35976.png"))); // NOI18N
        jM_cadastrarMapas.setText("Mapas");
        jM_cadastrarMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_cadastrarMapasActionPerformed(evt);
            }
        });
        jMenu2.add(jM_cadastrarMapas);

        jM_cadastrarfotografias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jM_cadastrarfotografias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/picture_photo_preview_2628.png"))); // NOI18N
        jM_cadastrarfotografias.setText("Fotografias");
        jM_cadastrarfotografias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_cadastrarfotografiasActionPerformed(evt);
            }
        });
        jMenu2.add(jM_cadastrarfotografias);

        jM_cadastrarCadernetas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jM_cadastrarCadernetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/notebook_notes_147.png"))); // NOI18N
        jM_cadastrarCadernetas.setText("Caderneta de Campo");
        jM_cadastrarCadernetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_cadastrarCadernetasActionPerformed(evt);
            }
        });
        jMenu2.add(jM_cadastrarCadernetas);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search_Computer_1_36841.png"))); // NOI18N
        jMenu3.setToolTipText("Pesquisar");
        jMenu3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jM_pesquisarMapas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK));
        jM_pesquisarMapas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_512x512_35976.png"))); // NOI18N
        jM_pesquisarMapas.setText("Mapas");
        jM_pesquisarMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_pesquisarMapasActionPerformed(evt);
            }
        });
        jMenu3.add(jM_pesquisarMapas);

        jM_pesquisarfotografias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        jM_pesquisarfotografias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/picture_photo_preview_2628.png"))); // NOI18N
        jM_pesquisarfotografias.setText("Fotografias");
        jM_pesquisarfotografias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_pesquisarfotografiasActionPerformed(evt);
            }
        });
        jMenu3.add(jM_pesquisarfotografias);

        jM_pesquisarCaderneta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jM_pesquisarCaderneta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/notebook_notes_147.png"))); // NOI18N
        jM_pesquisarCaderneta.setText("Caderneta de Campo");
        jM_pesquisarCaderneta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_pesquisarCadernetaActionPerformed(evt);
            }
        });
        jMenu3.add(jM_pesquisarCaderneta);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_notes_930.png"))); // NOI18N
        jMenu4.setToolTipText("Relatórios");
        jMenu4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jM_ListagemMapas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemMapas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_512x512_35976.png"))); // NOI18N
        jM_ListagemMapas.setText("Mapas");
        jM_ListagemMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemMapasActionPerformed(evt);
            }
        });
        jMenu4.add(jM_ListagemMapas);

        jM_ListagemPorFolha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemPorFolha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/folha.png"))); // NOI18N
        jM_ListagemPorFolha.setText("Mapas Por Folha");
        jM_ListagemPorFolha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemPorFolhaActionPerformed(evt);
            }
        });
        jMenu4.add(jM_ListagemPorFolha);

        jM_ConsultaPorGaveta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ConsultaPorGaveta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/gaveta.png"))); // NOI18N
        jM_ConsultaPorGaveta.setText("Mapas Por Gaveta");
        jM_ConsultaPorGaveta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ConsultaPorGavetaActionPerformed(evt);
            }
        });
        jMenu4.add(jM_ConsultaPorGaveta);

        jM_ListagemPorTitulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemPorTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/titulo.png"))); // NOI18N
        jM_ListagemPorTitulo.setText("Mapas Por Título");
        jM_ListagemPorTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemPorTituloActionPerformed(evt);
            }
        });
        jMenu4.add(jM_ListagemPorTitulo);
        jMenu4.add(jSeparator1);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/people_6080.png"))); // NOI18N
        jMenuItem1.setText("Caderneta por Geólogo");
        jMenu4.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/projeto.png"))); // NOI18N
        jMenuItem2.setText("Caderneta por Projeto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Help_26772.png"))); // NOI18N
        jMenu1.setToolTipText("Ajuda");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jM_cadastroMapa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        jM_cadastroMapa.setText("Cadastro de  Mapas");
        jM_cadastroMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_cadastroMapaActionPerformed(evt);
            }
        });
        jMenu1.add(jM_cadastroMapa);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jM_cadastrarMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadastrarMapasActionPerformed
        CadMapa mapas = new CadMapa();
        mapas.setVisible(true);
    }//GEN-LAST:event_jM_cadastrarMapasActionPerformed

    private void jM_pesquisarMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarMapasActionPerformed
        CadMapa mapas = new CadMapa();
        mapas.setVisible(true);
    }//GEN-LAST:event_jM_pesquisarMapasActionPerformed

    private void jM_pesquisarCadernetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarCadernetaActionPerformed
        Cadernetas cadernetas = new Cadernetas();
        cadernetas.setVisible(true);
    }//GEN-LAST:event_jM_pesquisarCadernetaActionPerformed

    private void jM_pesquisarfotografiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarfotografiasActionPerformed
        CadFotografiasAerea fotografias = new CadFotografiasAerea();
        fotografias.setVisible(true);
    }//GEN-LAST:event_jM_pesquisarfotografiasActionPerformed

    private void jM_cadastrarCadernetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadastrarCadernetasActionPerformed
        Cadernetas cadernetas = new Cadernetas();
        cadernetas.setVisible(true);
    }//GEN-LAST:event_jM_cadastrarCadernetasActionPerformed

    private void jM_cadastrarfotografiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadastrarfotografiasActionPerformed
        CadFotografiasAerea fotografias = new CadFotografiasAerea();
        fotografias.setVisible(true);
    }//GEN-LAST:event_jM_cadastrarfotografiasActionPerformed

    private void jM_ListagemMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemMapasActionPerformed
        try {
            CadMapa mapas = new CadMapa();
            mapas.abrirRelatorioMapas();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemMapasActionPerformed

    private void jM_ListagemPorFolhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemPorFolhaActionPerformed
        try {
            CadMapa mapas = new CadMapa();
            mapas.abrirRelatorioMapasPorFolha();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemPorFolhaActionPerformed

    private void jM_ConsultaPorGavetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ConsultaPorGavetaActionPerformed
        try {
            CadMapa mapas = new CadMapa();
            mapas.abrirRelatorioMapasPorGaveta();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ConsultaPorGavetaActionPerformed

    private void jM_ListagemPorTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemPorTituloActionPerformed
        try {
            CadMapa mps = new CadMapa();
            mps.abriRelatorioMapasPorTitulo();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemPorTituloActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jM_cadastroMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadastroMapaActionPerformed
       lerPDF();
    }//GEN-LAST:event_jM_cadastroMapaActionPerformed
     private void lerPDF(){
      if (Desktop.isDesktopSupported()) {
            // Arquivo no diretório de trabalho do usuário, System.getProperty ("user.dir");
            File file = new File("Tutorialcadastro.pdf");
            if (!file.exists()) {
                // no JAR
                InputStream inputStream = ClassLoader.getSystemClassLoader()
                        .getResourceAsStream("Tutorial/Tutorialcadastro.pdf");
                // copiando arquivo
                OutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] buffer = new byte[1024];
                int length;
                try {
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                // Open file
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jM_ConsultaPorGaveta;
    private javax.swing.JMenuItem jM_ListagemMapas;
    private javax.swing.JMenuItem jM_ListagemPorFolha;
    private javax.swing.JMenuItem jM_ListagemPorTitulo;
    private javax.swing.JMenuItem jM_cadastrarCadernetas;
    private javax.swing.JMenuItem jM_cadastrarMapas;
    private javax.swing.JMenuItem jM_cadastrarfotografias;
    private javax.swing.JMenuItem jM_cadastroMapa;
    private javax.swing.JMenuItem jM_pesquisarCaderneta;
    private javax.swing.JMenuItem jM_pesquisarMapas;
    private javax.swing.JMenuItem jM_pesquisarfotografias;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
