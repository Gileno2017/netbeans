package cprm.gov.gileno.macedo.VISAO;

import cprm.gov.gileno.macedo.MODELO.Sessao;
import cprm.gov.gileno.macedo.MODELO.Usuario;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gileno.macedo
 */
public final class MenuPesquisas extends javax.swing.JFrame {
   
    /**
     * Creates new form CadUsuario
     */
    public MenuPesquisas() throws Exception {
        initComponents();
        setLocationRelativeTo(null);
      
    }

    public void verificaUsuarioLogado() throws Exception {
        Usuario usu = Sessao.getInstance().getUsuario();

        if (usu.getPerfil().equals("ADMIN")) {

            LoginUsuario log = new LoginUsuario();
            log.setVisible(true);
            this.dispose();

        } else {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btg_Perfil = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        sair = new javax.swing.JLabel();
        minimizar = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        imageLogin = new javax.swing.JLabel();
        Cadmap = new javax.swing.JLabel();
        mapaIndice = new javax.swing.JLabel();
        fotoAerea = new javax.swing.JLabel();
        cadernetaCampo = new javax.swing.JLabel();
        cadUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Opcões");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(140, 123, 117));

        jPanel3.setBackground(new java.awt.Color(82, 60, 60));

        sair.setBackground(new java.awt.Color(188, 170, 164));
        sair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sair.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-Close Window-32.png"))); // NOI18N
        sair.setToolTipText("Sair");
        sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sairMouseClicked(evt);
            }
        });

        minimizar.setBackground(new java.awt.Color(188, 170, 164));
        minimizar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        minimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-Minimize Window-32 (1).png"))); // NOI18N
        minimizar.setToolTipText("Sair");
        minimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizarMouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(62, 52, 226));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(223, 213, 217));
        jLabel9.setText("MapotecaCprm");

        jPanel1.setBackground(new java.awt.Color(188, 170, 164));

        Cadmap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-CadMap-96 .png"))); // NOI18N
        Cadmap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cadmap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CadmapMouseClicked(evt);
            }
        });

        mapaIndice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-mapaindice-96.png"))); // NOI18N
        mapaIndice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mapaIndice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mapaIndiceMouseClicked(evt);
            }
        });

        fotoAerea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-fotografia aerea-96 (2).png"))); // NOI18N
        fotoAerea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fotoAerea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fotoAereaMouseClicked(evt);
            }
        });

        cadernetaCampo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-caderneta-96 (3).png"))); // NOI18N
        cadernetaCampo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadernetaCampo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                cadernetaCampoMouseDragged(evt);
            }
        });

        cadUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-cadUsuario-96.png"))); // NOI18N
        cadUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadUsuario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                cadUsuarioMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(464, 464, 464))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Cadmap, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mapaIndice, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fotoAerea, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cadernetaCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cadUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cadmap, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mapaIndice, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fotoAerea, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadernetaCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 32, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(sair)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sair)
                        .addComponent(minimizar)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
       

   

    private void sairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sairMouseClicked
        try {
            Thread.sleep (300);
            this.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuPesquisas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sairMouseClicked

    private void minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseClicked
        try {
            Thread.sleep (300);
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuPesquisas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizarMouseClicked

    private void CadmapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CadmapMouseClicked

        CadMapa mapas = new CadMapa();
        mapas.setVisible(true);
        try {
            Thread.sleep (300);
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuPesquisas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_CadmapMouseClicked

    private void mapaIndiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapaIndiceMouseClicked
        try {
            CadMapaIndice cadMapaIndice = new CadMapaIndice();
            cadMapaIndice.setVisible(true);
            Thread.sleep (300);
            this.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuPesquisas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mapaIndiceMouseClicked

    private void fotoAereaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fotoAereaMouseClicked
        try {
            CadFotografiaAerea cadFotografiaAerea = new CadFotografiaAerea();
            cadFotografiaAerea.setVisible(true);
            Thread.sleep(300);
            this.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuPesquisas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fotoAereaMouseClicked

    private void cadernetaCampoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadernetaCampoMouseDragged
        try {
            Cadernetas cader = new Cadernetas();
            cader.setVisible(true);
            Thread.sleep(300);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(MenuPesquisas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cadernetaCampoMouseDragged

    private void cadUsuarioMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadUsuarioMouseDragged
        try {
            CadUsuario usu = new CadUsuario();
            usu.setVisible(true);
            Thread.sleep(300);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(MenuPesquisas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cadUsuarioMouseDragged

    
    
    
    
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
            java.util.logging.Logger.getLogger(MenuPesquisas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPesquisas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPesquisas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPesquisas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MenuPesquisas().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MenuPesquisas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cadmap;
    private javax.swing.ButtonGroup btg_Perfil;
    private javax.swing.JLabel cadUsuario;
    private javax.swing.JLabel cadernetaCampo;
    private javax.swing.JLabel fotoAerea;
    private javax.swing.JLabel imageLogin;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel mapaIndice;
    private javax.swing.JLabel minimizar;
    private javax.swing.JLabel sair;
    // End of variables declaration//GEN-END:variables
}
