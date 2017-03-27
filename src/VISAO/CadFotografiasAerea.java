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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
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
    InputStream input;
    BufferedImage imagem;//recebe a imagem da linha selecionada da tabela
    ListIterator<ImagemMapas> listIterator;
    ImagemMapas primeiroElemento;
    List<ImagemMapas> lista;

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

    /**
     * 2 *metodo responsável por capiturar os valores da tabela e inserir no
     * Jlabels 3 * 4 * 5
     */
    public void linhaSelecionadaTableFotografias() throws IOException {
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

        ImagemMapas imagem = new ImagemMapas();// cria  um novo objeto da class imagemmapas
        ImagemMapasDao imDao = new ImagemMapasDao();// cria um novo objeto da class imagemMapasDao
        lista = imDao.consultaImagensPorFotografias(idFotografia);// traz os objetos contido no banco de dados
        listIterator = lista.listIterator();
        listIterator.next();
        primeiroElemento = lista.get(listIterator.nextIndex() - 1);
        exibiImagemLabel(primeiroElemento.getImagem(), imagemLabel);    
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
        pesquisasDemapas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pesquisarMapa = new javax.swing.JButton();
        jP_Botoes = new javax.swing.JPanel();
        jB_salvarFotografias = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bt_imagem = new javax.swing.JToggleButton();
        adicionarImagens = new javax.swing.JToggleButton();
        jb_proximo = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        totalMapas = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        imagemLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

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
                .addComponent(jScrollPane2)
                .addContainerGap())
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pesquisasDemapas, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pesquisasDemapas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logocprm.png"))); // NOI18N

        pesquisarMapa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pesquisarMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_search_find_maps_10803.png"))); // NOI18N
        pesquisarMapa.setToolTipText("Pesquisar");
        pesquisarMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarMapaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
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
                            .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(pesquisarMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pesquisarMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addGap(20, 20, 20)
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
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jP_Botoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jB_salvarFotografias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save_14706.png"))); // NOI18N
        jB_salvarFotografias.setToolTipText("Salvar");
        jB_salvarFotografias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_salvarFotografiasActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/deletes.png"))); // NOI18N
        jButton1.setToolTipText("Excluir Mapa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bt_imagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/foto.png"))); // NOI18N
        bt_imagem.setToolTipText("Carrega Imagem");
        bt_imagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imagemActionPerformed(evt);
            }
        });

        adicionarImagens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/plus-one-icon_34213.png"))); // NOI18N
        adicionarImagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarImagensActionPerformed(evt);
            }
        });

        jb_proximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaDireita.png"))); // NOI18N
        jb_proximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_proximoActionPerformed(evt);
            }
        });

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaEsquerda.png"))); // NOI18N
        anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_BotoesLayout = new javax.swing.GroupLayout(jP_Botoes);
        jP_Botoes.setLayout(jP_BotoesLayout);
        jP_BotoesLayout.setHorizontalGroup(
            jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_BotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_BotoesLayout.createSequentialGroup()
                        .addComponent(anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jP_BotoesLayout.createSequentialGroup()
                        .addGroup(jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jB_salvarFotografias)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adicionarImagens, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_imagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jP_BotoesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {adicionarImagens, bt_imagem, jButton1});

        jP_BotoesLayout.setVerticalGroup(
            jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_BotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adicionarImagens, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_imagem, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jB_salvarFotografias, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jP_BotoesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {adicionarImagens, anterior, jb_proximo});

        jP_BotoesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bt_imagem, jB_salvarFotografias});

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
                .addContainerGap())
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

        imagemLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(imagemLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/computer.png"))); // NOI18N
        jMenu1.setToolTipText("Cadastrar");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/picture_photo_preview_2628.png"))); // NOI18N
        jMenuItem2.setText("Fotografias");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/notebook_notes_147.png"))); // NOI18N
        jMenuItem3.setText("Caderneta de Campo");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search_Computer_1_36841.png"))); // NOI18N
        jMenu2.setToolTipText("Pesquisar");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setText("Fotografias");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Caderneta de Campo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_notes_930.png"))); // NOI18N
        jMenu3.setToolTipText("Relatórios");
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Help_26772.png"))); // NOI18N
        jMenu4.setToolTipText("Ajuda");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jP_Botoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jP_Botoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        imagemLabel.setIcon(null);
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
            imagemLabel.setIcon(redimencionaImagem(path));
        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Arquivo não selecionado");
        }

    }

    private ImageIcon redimencionaImagem(String ImagePath) throws IOException {
        ImageIcon minhaImagem = new ImageIcon(ImagePath);
        Image img = minhaImagem.getImage();
        Image novaImagem = img.getScaledInstance(imagemLabel.getWidth(), imagemLabel.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon im = new ImageIcon(novaImagem);
        image = ImageIO.read(Arquivoselecionado);
        return im;
    }

    public void SalvarImagem() throws Exception {
        ImagemMapasDao imDao = new ImagemMapasDao();
        ImagemMapas im = new ImagemMapas();
        Fotografias fotografias = new Fotografias();
        fotografias.setCod_Fotografia(idFotografia);
        im.setImagem(ManipulaBuffImage.getImgBytes(image));
        im.setFotos(fotografias);
        imDao.salvarImagemMapas(im);

    }

    public void exibiImagemLabel(byte[] minhaImg, javax.swing.JLabel label) throws IOException {
        //primeiro verifica se tem a imagem
        //se tem convert para inputstream que é o formato reconhecido pelo ImageIO

        if (minhaImg != null) {
            input = new ByteArrayInputStream(minhaImg);
            imagem = ImageIO.read(input);
            ImageIcon minhaImagem = new ImageIcon(imagem);
            Image img = minhaImagem.getImage();
            Image novaImagem = img.getScaledInstance(imagemLabel.getWidth(), imagemLabel.getHeight(), Image.SCALE_REPLICATE);
            label.setIcon(new ImageIcon(novaImagem));
        } else {
            label.setIcon(null);
        }

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
            Logger.getLogger(CadFotografiasAerea.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jB_salvarFotografiasActionPerformed

    private void tabelaFotografiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFotografiasMouseClicked
        try {
            linhaSelecionadaTableFotografias();

        } catch (IOException ex) {
            Logger.getLogger(CadFotografiasAerea.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaFotografiasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removerFotografias();
        limpaCampos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bt_imagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imagemActionPerformed
        try {
            carregaImagem();

        } catch (IOException ex) {
            Logger.getLogger(CadFotografiasAerea.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_imagemActionPerformed

    private void adicionarImagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarImagensActionPerformed
        try {
            SalvarImagem();
            imagemLabel.setIcon(null);

        } catch (Exception ex) {
            Logger.getLogger(CadFotografiasAerea.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_adicionarImagensActionPerformed

    private void jb_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_proximoActionPerformed
      if (listIterator.hasNext()) {
            primeiroElemento = (ImagemMapas) listIterator.next(); 
           
          try {  
              exibiImagemLabel(primeiroElemento.getImagem(), imagemLabel);
          } catch (IOException ex) {
              Logger.getLogger(CadFotografiasAerea.class.getName()).log(Level.SEVERE, null, ex);
          }
          
               }
        
        
       
    }//GEN-LAST:event_jb_proximoActionPerformed

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
          if (listIterator.hasPrevious()) {
            primeiroElemento = (ImagemMapas) listIterator.previous();            
          try {  
              exibiImagemLabel(primeiroElemento.getImagem(), imagemLabel);
          } catch (IOException ex) {
              Logger.getLogger(CadFotografiasAerea.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        
    }//GEN-LAST:event_anteriorActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadFotografiasAerea().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton adicionarImagens;
    private javax.swing.JButton anterior;
    private javax.swing.JToggleButton bt_imagem;
    private javax.swing.JTextField caixaLocalização;
    private javax.swing.JTextField escala;
    private javax.swing.JTextField folha;
    private javax.swing.JLabel imagemLabel;
    private javax.swing.JButton jB_salvarFotografias;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jP_Botoes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jb_proximo;
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
