package VISAO;

import CONTROLE.FotografiaDao;
import CONTROLE.ImagemMapasDao;
import CONTROLE.MapasDao;
import MODELO.Fotografias;
import MODELO.Mapas;
import MODELO.MapasTableModel;
import MODELO.FotografiasTableModel;
import MODELO.ImagemMapas;
import MODELO.ManipulaBuffImage;
import MODELO.ValidaCamposComNumeros;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author gileno.macedo
 */
public class CadFotografiasAerea extends javax.swing.JFrame implements Serializable {

    MapasTableModel modeloTabelaMapas = new MapasTableModel();
    FotografiasTableModel modeTabelaFotografias = new FotografiasTableModel();
    Mapas mp;
    Mapas ma;
    Fotografias fotos;
    Long idMapa, id;
    Long idFotografia;
    ValidaCamposComNumeros validacampos;
    BufferedImage image;
    File Arquivoselecionado;

    /**
     * Creates new form CadFotografias
     */
    public CadFotografiasAerea() {
        initComponents();
        setLocationRelativeTo(null);
        totalMapa();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void pesquisaPorFoha() {
        modeloTabelaMapas.consultaMapaPorNome(pesquisasDemapas.getText().toUpperCase());
    }

    public void linhaSelecionadaTableMapas() {
        mp = modeloTabelaMapas.getLinhaMapas(tabela.getSelectedRow());
        idMapa = mp.getCodMapa();
        folha.setText(mp.getFolha());
        escala.setText(mp.getEscala());
        folha.setEditable(false);
        escala.setEditable(false);
    }

    public void linhaSelecionadaTableFotografias() {
        fotos = modeTabelaFotografias.getLinhaFotografias(tabelaFotografias.getSelectedRow());
        idFotografia = fotos.getCod_Fotografia();
        idMapa = fotos.getMapas().getCodMapa();
        fotos.getMapas().getCodMapa();
        folha.setText(String.valueOf(fotos.getMapas().getFolha()));
        escala.setText(String.valueOf(fotos.getMapas().getEscala()));
        numeroInicial.setText(String.valueOf(fotos.getCodInical()));
        numeroFinal.setText(String.valueOf(fotos.getCodFinal()));
        orgaoExecutor.setText(fotos.getOrgaoExecutor());
        totalfoto.setText(String.valueOf(fotos.getTotaFotografias()));
        caixaLocalização.setText(String.valueOf(fotos.getLocalArmazenado()));
        folha.setEditable(false);
        escala.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFotografias = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        folha = new javax.swing.JTextField();
        numeroInicial = new javax.swing.JTextField();
        orgaoExecutor = new javax.swing.JTextField();
        caixaLocalização = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        escala = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        totalfoto = new javax.swing.JTextField();
        numeroFinal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        pesquisarMapa = new javax.swing.JButton();
        pesquisasDemapas = new javax.swing.JTextField();
        jP_Botoes = new javax.swing.JPanel();
        jB_salvarFotografias = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bt_imagem = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        totalMapas = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        imagemMapa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastros de Fotografias Aérea");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        tabelaFotografias.setModel(modeTabelaFotografias);
        tabelaFotografias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFotografiasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaFotografias);

        tabela.setModel(modeloTabelaMapas
        );
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        folha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        folha.setToolTipText("Campo Não Editável");

        numeroInicial.setFont(new java.awt.Font("Tahoma", 0, 14));
        numeroInicial.setToolTipText("Inserir somente números");

        orgaoExecutor.setToolTipText("Inserir somente  letras e /");

        caixaLocalização.setToolTipText("Insergir somente números");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Folha:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Código Inicial:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Executor:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Caixa:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Escala:");

        escala.setToolTipText("Campo Não Editável");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Total: ");

        totalfoto.setToolTipText("Inserir somente números");

        numeroFinal.setFont(new java.awt.Font("Tahoma", 0, 14));
        numeroFinal.setToolTipText("Inserir somente números");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Código Final:");

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        pesquisarMapa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pesquisarMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_search_find_maps_10803.png"))); // NOI18N
        pesquisarMapa.setToolTipText("Pesquisar");
        pesquisarMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarMapaActionPerformed(evt);
            }
        });

        pesquisasDemapas.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        pesquisasDemapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisasDemapasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(pesquisarMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisasDemapas))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pesquisarMapa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisasDemapas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(numeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numeroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(folha)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(orgaoExecutor, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(caixaLocalização, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(escala))
                .addGap(50, 50, 50))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(numeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(numeroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(orgaoExecutor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(caixaLocalização, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jP_Botoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jB_salvarFotografias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.jpg"))); // NOI18N
        jB_salvarFotografias.setToolTipText("Salvar");
        jB_salvarFotografias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_salvarFotografiasActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/apaga.jpg"))); // NOI18N
        jButton1.setToolTipText("Excluir Mapa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bt_imagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/foto.JPG"))); // NOI18N
        bt_imagem.setToolTipText("Carrega Imagem");
        bt_imagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imagemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_BotoesLayout = new javax.swing.GroupLayout(jP_Botoes);
        jP_Botoes.setLayout(jP_BotoesLayout);
        jP_BotoesLayout.setHorizontalGroup(
            jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_BotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(bt_imagem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jB_salvarFotografias, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jP_BotoesLayout.setVerticalGroup(
            jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_BotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_imagem, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jB_salvarFotografias, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(130, 60, 122));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fotográfias Cadastradas");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mapas Cadastrados");

        totalMapas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalMapas.setForeground(new java.awt.Color(255, 255, 255));
        totalMapas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(349, 349, 349)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(totalMapas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(102, 0, 102))); // NOI18N

        imagemMapa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jP_Botoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jP_Botoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarMapaActionPerformed
        if (pesquisasDemapas.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a Folha para Efetuar a Pesquisa !");
        } else {
            pesquisaPorFoha();
            pesquisasDemapas.setText("");
        }
    }//GEN-LAST:event_pesquisarMapaActionPerformed

    public void salvarFotos() throws Exception {
        Fotografias fotografias = new Fotografias();       
        Mapas mapas = new Mapas();
        mapas.setCodMapa(idMapa);
        ImagemMapas imp = new ImagemMapas();
        imp.setImagem(ManipulaBuffImage.getImgBytes(image));
        mapas.setFolha(folha.getText());
        mapas.setEscala(escala.getText());
        fotografias.setCod_Fotografia(idFotografia);
        fotografias.setTotaFotografias(totalfoto.getText());
        fotografias.setCodInical(numeroInicial.getText());
        fotografias.setCodFinal(numeroFinal.getText());
        fotografias.setLocalArmazenado(caixaLocalização.getText());
        fotografias.setOrgaoExecutor(orgaoExecutor.getText().toUpperCase());
        fotografias.setMapas(mapas);
        imp.setFotos(fotografias);
        //metodo responsavel por validar os campos na bean.mapas
        ValidatorFactory validaFotos = Validation.buildDefaultValidatorFactory();
        Validator validador = validaFotos.getValidator();
        Set<ConstraintViolation<Fotografias>> Alerta = validador.validate(fotografias);
        if (Alerta.size() > 0) {
            for (ConstraintViolation< Fotografias> error : Alerta) {
                JOptionPane.showMessageDialog(null, error.getMessage());
            }
        } else {
            //Envia os dados do mapa para a tabela e salva no banco de dados
            
            modeTabelaFotografias.salvaFotografias(fotografias);
            ImagemMapasDao imDao = new ImagemMapasDao();
            imDao.salvarImagemMapas(imp);
            modeTabelaFotografias.atualizaListadeFotos(fotos);
            limpaCampos();
        }
    }

    public void removerFotografias() {
        FotografiaDao fotografiaDao = new FotografiaDao();
        fotografiaDao.removeFotografias(idFotografia);
        modeTabelaFotografias.removeFotografia(fotos);
    }

    public void limpaCampos() {
        folha.setText("");
        escala.setText("");
        numeroInicial.setText("");
        numeroFinal.setText("");
        totalfoto.setText("");
        orgaoExecutor.setText("");
        caixaLocalização.setText("");
    }

    public void totalMapa() {
        MapasDao mDao = new MapasDao();
        int total = mDao.getMapasCount();
        totalMapas.setText(String.valueOf(total));

    }
/////////////////////////////////////////// METODOS RESPONSAVEIS POR ARQUIVAR A  IMAGEM NA PASTA  /////////////////////////////////////////////////////////////////////
       public void carregaImagem() throws IOException {
        JFileChooser file = new JFileChooser("c:\\");
        file.setDialogTitle("Upload de Imagem");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.imagem", "jpg", "gif", "png");
        file.addChoosableFileFilter(filtro);
        int resultado = file.showSaveDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            Arquivoselecionado = file.getSelectedFile();
            String path = Arquivoselecionado.getAbsolutePath();
            imagemMapa.setIcon(redimencionaImagem(path));
        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Arquivo não selecionado");
        }

    }
       
        private ImageIcon redimencionaImagem(String ImagePath) throws IOException {
        ImageIcon minhaImagem = new ImageIcon(ImagePath);
        Image img = minhaImagem.getImage();
        Image novaImagem = img.getScaledInstance(imagemMapa.getWidth(), imagemMapa.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon im = new ImageIcon(novaImagem);
        image = ImageIO.read(Arquivoselecionado);
        return im;
    }

    
    private void pesquisasDemapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisasDemapasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisasDemapasActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        linhaSelecionadaTableMapas();
    }//GEN-LAST:event_tabelaMouseClicked

    private void jB_salvarFotografiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_salvarFotografiasActionPerformed
        try {
            salvarFotos();
        } catch (Exception ex) {
            Logger.getLogger(CadFotografiasAerea.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jB_salvarFotografiasActionPerformed

    private void tabelaFotografiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFotografiasMouseClicked
        linhaSelecionadaTableFotografias();
    }//GEN-LAST:event_tabelaFotografiasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removerFotografias();
        limpaCampos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bt_imagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imagemActionPerformed
        try {
            carregaImagem();
        } catch (IOException ex) {
            Logger.getLogger(CadFotografiasAerea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_imagemActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadFotografiasAerea().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bt_imagem;
    private javax.swing.JTextField caixaLocalização;
    private javax.swing.JTextField escala;
    private javax.swing.JTextField folha;
    private javax.swing.JLabel imagemMapa;
    private javax.swing.JButton jB_salvarFotografias;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jP_Botoes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField numeroFinal;
    private javax.swing.JTextField numeroInicial;
    private javax.swing.JTextField orgaoExecutor;
    private javax.swing.JButton pesquisarMapa;
    private javax.swing.JTextField pesquisasDemapas;
    private javax.swing.JTable tabela;
    private javax.swing.JTable tabelaFotografias;
    private javax.swing.JLabel totalMapas;
    private javax.swing.JTextField totalfoto;
    // End of variables declaration//GEN-END:variables
}
