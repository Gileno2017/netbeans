package VISAO;

import CONTROLE.MapaIndiceDao;
import CONTROLE.FotografiaAereaDao;
import CONTROLE.MapasDao;
import MODELO.CopiaArquivos;
import MODELO.MapaIndice;
import MODELO.TableMapaIndice;
import MODELO.FotografiaAerea;
import MODELO.TableFotograiaAerea;
import MODELO.ValidaCamposComNumeros;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
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
import javax.swing.table.TableModel;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author gileno.macedo
 */
public class CadFotografiaAerea extends javax.swing.JFrame implements Serializable {

    TableMapaIndice modeloTabelaMapaIndice = new TableMapaIndice();
     TableFotograiaAerea modeloTabelaFotografiaAerea  = new TableFotograiaAerea();
 

    MapaIndice mapaIndice;
    Long idMapaIndice, id;
    ValidaCamposComNumeros validacampos;
    BufferedImage image;
    File fileArquivoselecionado;
    InputStream input;
    BufferedImage imagem;//recebe a imagem da linha selecionada da tabela
    ListIterator<FotografiaAerea> listIterator;
    FotografiaAerea primeiroElemento, fotoAerea;
    List<FotografiaAerea> listaFotografiasAereas;
    String nomeImagemSelecionada, caminhoMapaIndiceBD, caminhoImagemCapturada,caminhoPastaMapaIndicenobanco;
    File fileDestino;

    /**
     * Creates new form CadFotografias
     */
    public CadFotografiaAerea() {
       
     
        initComponents();
        setLocationRelativeTo(null);
        totalFotografiaAerea();
        totalMapaIndice();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void pesquisaPorFoha() {
        modeloTabelaMapaIndice.consultaMapaIndicePorNome(pesquisasDemapas.getText().toUpperCase());
    }

    public void linhaSelecionadaTableMapaIndice() throws IOException, Exception {
        
        mapaIndice = modeloTabelaMapaIndice.getLinhaMapaIndice(tabelaMapaIndice.getSelectedRow());        
        idMapaIndice = mapaIndice.getCod_MapaIndice();
        
        modeloTabelaFotografiaAerea.listaFotografiaPorId(idMapaIndice);
        modeloTabelaFotografiaAerea.atualizaListadeFotografiaAerea(fotoAerea);
        
        folha.setText(mapaIndice.getFolhaFotografia());
        escala.setText(mapaIndice.getEscala());
        orgaoExecutor.setText(mapaIndice.getOrgaoExecutor());
        escala.setEditable(false);
        folha.setEditable(false);
        orgaoExecutor.setEditable(false);
        caminhoMapaIndiceBD = mapaIndice.getCaminhoMapaIndeceBD();
        caminhoPastaMapaIndicenobanco = mapaIndice.getCaminhoPastaBD();
        String caminhoImagemSalva = mapaIndice.getCaminhoMapaIndeceBD();        
        imagemMapa.setIcon(redimencionaImagem2(caminhoImagemSalva));
    }

    public void linhaSelecionadaTableFotografiaAerea() throws IOException {
       
        fotoAerea = modeloTabelaFotografiaAerea.getLinhaFotografiaAerea(tabelaFotografiaAerea.getSelectedRow());
        idMapaIndice = mapaIndice.getCod_MapaIndice();
        mapaIndice.getMapas().getCodMapa();
        numeroInicial.setText(fotoAerea.getNumeroImagem());
        caixaLocalização.setText(fotoAerea.getLocalArmazenado());        
        folha.setText(String.valueOf(fotoAerea.getMpIndiceImagem().getFolhaFotografia()));
        escala.setText(String.valueOf(fotoAerea.getMpIndiceImagem().getEscala()));
        orgaoExecutor.setText(mapaIndice.getOrgaoExecutor());
        imagemMapa.setIcon(redimencionaImagem2(fotoAerea.getImagemFotografiaAerea()));

        FotografiaAereaDao fotografiaAereaDao = new FotografiaAereaDao();// cria um novo objeto da class imagemMapasDao
        listaFotografiasAereas = fotografiaAereaDao.consultaImagensPorFotografiaAerea(idMapaIndice);// traz os objetos contidos no banco de dados
        listIterator = listaFotografiasAereas.listIterator();
        listIterator.next();
        primeiroElemento = listaFotografiasAereas.get(listIterator.nextIndex() + 1);
        String mapaCapiturado = primeiroElemento.getImagemFotografiaAerea();            
        folha.setEditable(false);
        escala.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFotografiaAerea = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaMapaIndice = new javax.swing.JTable();
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
        totalfotografiaAerea = new javax.swing.JLabel();
        imagemMapa = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jM_cadastrarMapas = new javax.swing.JMenuItem();
        jM_cadastrarCadernetas = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jM_pesquisarMapas = new javax.swing.JMenuItem();
        jM_pesquisarCaderneta = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jM_ListagemMapas = new javax.swing.JMenuItem();
        jM_ListagemPorFolha = new javax.swing.JMenuItem();
        jM_ConsultaPorGaveta = new javax.swing.JMenuItem();
        jM_ListagemPorTitulo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastros de Imagens Aérea");
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        tabelaFotografiaAerea.setModel(modeloTabelaFotografiaAerea);
        tabelaFotografiaAerea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFotografiaAereaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaFotografiaAerea);

        tabelaMapaIndice.setModel(modeloTabelaMapaIndice
        );
        tabelaMapaIndice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMapaIndiceMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaMapaIndice);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        folha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        folha.setToolTipText("Campo Não Editável");

        numeroInicial.setFont(new java.awt.Font("Tahoma", 0, 14));
        numeroInicial.setToolTipText("Inserir somente números");

        orgaoExecutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        orgaoExecutor.setToolTipText("Inserir somente  letras e /");

        caixaLocalização.setToolTipText("Insergir somente números");
        caixaLocalização.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mapa Índice:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Numero:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Executor:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Caixa:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Escala:");

        escala.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        escala.setToolTipText("Campo Não Editável");

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
            .addComponent(pesquisasDemapas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
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
                .addGap(95, 95, 95)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(caixaLocalização, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3))
                            .addComponent(pesquisarMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(numeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addComponent(orgaoExecutor, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(folha, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
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
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(caixaLocalização, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orgaoExecutor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {caixaLocalização, numeroInicial});

        jP_Botoes.setBackground(new java.awt.Color(130, 60, 122));
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_BotoesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jP_BotoesLayout.createSequentialGroup()
                        .addGroup(jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jB_salvarFotografias)
                            .addComponent(bt_imagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adicionarImagens, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jP_BotoesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {adicionarImagens, bt_imagem, jButton1});

        jP_BotoesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {anterior, jb_proximo});

        jP_BotoesLayout.setVerticalGroup(
            jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_BotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_BotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adicionarImagens, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(bt_imagem, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jB_salvarFotografias, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jP_BotoesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {adicionarImagens, anterior, jButton1, jb_proximo});

        jP_BotoesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bt_imagem, jB_salvarFotografias});

        jPanel4.setBackground(new java.awt.Color(130, 60, 122));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Imagem Aerea ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mapa Índice ");

        totalMapas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalMapas.setForeground(new java.awt.Color(255, 255, 255));
        totalMapas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        totalfotografiaAerea.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalfotografiaAerea.setForeground(new java.awt.Color(255, 255, 255));
        totalfotografiaAerea.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalfotografiaAerea, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(327, 327, 327)
                .addComponent(totalMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalfotografiaAerea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(totalMapas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        imagemMapa.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagens Aereas"));

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

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search_Computer_1_36841.png"))); // NOI18N
        jMenu5.setToolTipText("Pesquisar");
        jMenu5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jM_pesquisarMapas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK));
        jM_pesquisarMapas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_512x512_35976.png"))); // NOI18N
        jM_pesquisarMapas.setText("Mapas");
        jM_pesquisarMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_pesquisarMapasActionPerformed(evt);
            }
        });
        jMenu5.add(jM_pesquisarMapas);

        jM_pesquisarCaderneta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jM_pesquisarCaderneta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/notebook_notes_147.png"))); // NOI18N
        jM_pesquisarCaderneta.setText("Caderneta de Campo");
        jM_pesquisarCaderneta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_pesquisarCadernetaActionPerformed(evt);
            }
        });
        jMenu5.add(jM_pesquisarCaderneta);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_notes_930.png"))); // NOI18N
        jMenu6.setToolTipText("Relatórios");
        jMenu6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jM_ListagemMapas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemMapas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_512x512_35976.png"))); // NOI18N
        jM_ListagemMapas.setText("Mapas");
        jM_ListagemMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemMapasActionPerformed(evt);
            }
        });
        jMenu6.add(jM_ListagemMapas);

        jM_ListagemPorFolha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemPorFolha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/folha.png"))); // NOI18N
        jM_ListagemPorFolha.setText("Mapas Por Folha");
        jM_ListagemPorFolha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemPorFolhaActionPerformed(evt);
            }
        });
        jMenu6.add(jM_ListagemPorFolha);

        jM_ConsultaPorGaveta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ConsultaPorGaveta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/gaveta.png"))); // NOI18N
        jM_ConsultaPorGaveta.setText("Mapas Por Gaveta");
        jM_ConsultaPorGaveta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ConsultaPorGavetaActionPerformed(evt);
            }
        });
        jMenu6.add(jM_ConsultaPorGaveta);

        jM_ListagemPorTitulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemPorTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/titulo.png"))); // NOI18N
        jM_ListagemPorTitulo.setText("Mapas Por Título");
        jM_ListagemPorTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemPorTituloActionPerformed(evt);
            }
        });
        jMenu6.add(jM_ListagemPorTitulo);
        jMenu6.add(jSeparator1);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/people_6080.png"))); // NOI18N
        jMenuItem1.setText("Caderneta por Geólogo");
        jMenu6.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/projeto.png"))); // NOI18N
        jMenuItem2.setText("Caderneta por Projeto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem2);

        jMenuBar1.add(jMenu6);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jP_Botoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jP_Botoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarMapaActionPerformed
        if (pesquisasDemapas.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a Folha para Efetuar a Pesquisa !");
        } else {
            // pesquisaPorFoha();
            pesquisasDemapas.setText("");
        }
    }//GEN-LAST:event_pesquisarMapaActionPerformed

    public void salvarFotografiaAerea() throws Exception {
        FotografiaAerea fotografiaAerea = new FotografiaAerea();
        MapaIndice mapIndice = new MapaIndice();
        mapIndice.setCod_MapaIndice(idMapaIndice);
        mapIndice.setCaminhoMapaIndeceBD(caminhoMapaIndiceBD);        
        fotografiaAerea.setImagemFotografiaAerea(caminhoPastaMapaIndicenobanco+ "\\"+nomeImagemSelecionada);
        fotografiaAerea.setNumeroImagem(numeroInicial.getText().toUpperCase());
        fotografiaAerea.setLocalArmazenado(caixaLocalização.getText().toUpperCase());
        fotografiaAerea.setMpIndiceImagem(mapIndice);

        //metodo responsavel por validar os campos na bean.mapas
        ValidatorFactory validaFotos = Validation.buildDefaultValidatorFactory();
        Validator validador = validaFotos.getValidator();
        Set<ConstraintViolation<FotografiaAerea>> Alerta = validador.validate(fotografiaAerea);
        if (Alerta.size() > 0) {
            for (ConstraintViolation< FotografiaAerea> error : Alerta) {
                JOptionPane.showMessageDialog(null, error.getMessage());
            }
        } else {
           
            CopiaArquivos copy = new CopiaArquivos();         
             copy.copyFileUsingStream(fileArquivoselecionado,caminhoPastaMapaIndicenobanco);//copia a imagem selecionada para uma pasta no servidor de arquivos
            
           //Envia os dados do mapa para a tabela e salva no banco de dados            
           // modeloTabelaFotografiaAerea.salvaFotografiaAerea(fotografiaAerea);
            FotografiaAereaDao imDao = new FotografiaAereaDao();
            imDao.salvarFotografiaAerea(fotografiaAerea);
           // modeloTabelaFotografiaAerea.atualizaListadeFotografiaAerea(fotografiaAerea);
            numeroInicial.setText("");
            caixaLocalização.setText("");
            imagemMapa.setIcon(null);
        }
    }

    public void removerFotografias() {
        MapaIndiceDao fotografiaDao = new MapaIndiceDao();
        fotografiaDao.removeMapaIndice(idMapaIndice);
        
        //modeloTabelaFotografiaAerea.removeFotografiaAerea(fotoAerea);
    }

    public void limpaCampos() {
        folha.setText("");
        escala.setText("");
        numeroInicial.setText("");
        orgaoExecutor.setText("");
        caixaLocalização.setText("");
        imagemMapa.setIcon(null);
    }

    private void totalFotografiaAerea() {
        FotografiaAereaDao fotografiaAereaDao = new FotografiaAereaDao();
        int totalFotografiasAereas = fotografiaAereaDao.getImagemMapasCount();
        totalfotografiaAerea.setText(String.valueOf(totalFotografiasAereas));
    }

    private void totalMapaIndice() {
        MapaIndiceDao mapaIndiceDao = new MapaIndiceDao();
        int totalMapaIndice = mapaIndiceDao.getMapaIndiceCount();
        totalMapas.setText(String.valueOf(totalMapaIndice));
    }

/////////////////////////////////////////// METODOS RESPONSAVEIS POR ARQUIVAR A  IMAGEM NA PASTA  /////////////////////////////////////////////////////////////////////
    public void carregaImagem() throws IOException, Exception {
        JFileChooser fileChooser = new JFileChooser("c:\\");
        fileChooser.setDialogTitle("Upload de Imagem");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//captura somente pastas
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.imagem", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filtro);
        int resultado = fileChooser.showSaveDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            fileArquivoselecionado = fileChooser.getSelectedFile();//seleciona a imagem na pasta
            caminhoImagemCapturada = fileArquivoselecionado.getAbsolutePath();// pega o caminho da imagem
            nomeImagemSelecionada = fileChooser.getSelectedFile().getName();// captura o nome da imagem na pasta
            imagemMapa.setIcon(redimencionaImagem(caminhoImagemCapturada));// exibe a imagem na label (tela)

        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Arquivo não selecionado");
        }
    }

    private ImageIcon redimencionaImagem2(String ImagePath) throws IOException {
        ImageIcon minhaImagem = new ImageIcon(ImagePath);
        Image img = minhaImagem.getImage();
        Image novaImagem = img.getScaledInstance(imagemMapa.getWidth(), imagemMapa.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon im = new ImageIcon(novaImagem);
        return im;
    }

    private ImageIcon redimencionaImagem(String ImagePath) throws IOException {
        ImageIcon minhaImagem = new ImageIcon(ImagePath);
        Image img = minhaImagem.getImage();
        Image novaImagem = img.getScaledInstance(imagemMapa.getWidth(), imagemMapa.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon im = new ImageIcon(novaImagem);
        image = ImageIO.read(fileArquivoselecionado);
        return im;
    }

    //METODO RESPONSÁVEL POR ADD MAIS IMAGENS A FOTOGRAFIA AEREA
    public void SalvarImagem() throws Exception {
        String caminho = mapaIndice.getCaminhoMapaIndeceBD();
        JOptionPane.showMessageDialog(null, "caminho imagem banco " + caminho);
        FotografiaAereaDao fotoAereaDao = new FotografiaAereaDao();
        FotografiaAerea fotografiaAerea = new FotografiaAerea();
        MapaIndice fotografias = new MapaIndice();
        fotografias.setCod_MapaIndice(idMapaIndice);
        carregaImagem();
        CopiaArquivos copiaArquivos = new CopiaArquivos();
       // copiaArquivos.addUmArquivo(fileArquivoselecionado, nomeImagemSelecionada, caminho);
        fotografiaAerea.setImagemFotografiaAerea(caminho + "\\" + nomeImagemSelecionada);
        fotografiaAerea.setMpIndiceImagem(fotografias);
        fotoAereaDao.salvarFotografiaAerea(fotografiaAerea);
    }

    public void exibiImagemLabel(byte[] minhaImg, javax.swing.JLabel label) throws IOException {
        //primeiro verifica se tem a imagem
        //se tem convert para inputstream que é o formato reconhecido pelo ImageIO
        if (minhaImg != null) {
            input = new ByteArrayInputStream(minhaImg);
            imagem = ImageIO.read(input);
            ImageIcon minhaImagem = new ImageIcon(imagem);
            Image img = minhaImagem.getImage();
            Image novaImagem = img.getScaledInstance(imagemMapa.getWidth(), imagemMapa.getHeight(), Image.SCALE_REPLICATE);
            label.setIcon(new ImageIcon(novaImagem));
        } else {
            label.setIcon(null);
        }
    }

    private void pesquisasDemapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisasDemapasActionPerformed

    }//GEN-LAST:event_pesquisasDemapasActionPerformed

    private void jB_salvarFotografiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_salvarFotografiasActionPerformed
        try {
            salvarFotografiaAerea();
        } catch (Exception ex) {
            Logger.getLogger(CadFotografiaAerea.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jB_salvarFotografiasActionPerformed


    private void tabelaFotografiaAereaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFotografiaAereaMouseClicked

        try {
            linhaSelecionadaTableFotografiaAerea();
        } catch (IOException ex) {
            Logger.getLogger(CadFotografiaAerea.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_tabelaFotografiaAereaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removerFotografias();
        limpaCampos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bt_imagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imagemActionPerformed
        try {
            carregaImagem();
        } catch (Exception ex) {
            Logger.getLogger(CadFotografiaAerea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_imagemActionPerformed

    private void adicionarImagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarImagensActionPerformed
        try {
            SalvarImagem();
            imagemMapa.setIcon(null);
        } catch (Exception ex) {
            Logger.getLogger(CadFotografiaAerea.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_adicionarImagensActionPerformed

    private void jb_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_proximoActionPerformed
        if (listIterator.hasNext()) {
            primeiroElemento = (FotografiaAerea) listIterator.next();
            String mapaCapiturado = primeiroElemento.getImagemFotografiaAerea();

            try {
                imagemMapa.setIcon(redimencionaImagem2(mapaCapiturado));

            } catch (IOException ex) {
                Logger.getLogger(CadFotografiaAerea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jb_proximoActionPerformed


    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
        if (listIterator.hasPrevious()) {
            primeiroElemento = (FotografiaAerea) listIterator.previous();
            String mapaCapiturado = primeiroElemento.getImagemFotografiaAerea();
            //  String nomeImagem = primeiroElemento.getMpIndiceImagem().getMapas().getCaminho();
            try {
                imagemMapa.setIcon(redimencionaImagem2(mapaCapiturado));

            } catch (IOException ex) {
                Logger.getLogger(CadFotografiaAerea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_anteriorActionPerformed

    private void jM_pesquisarMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarMapasActionPerformed
        CadMapa mapas = new CadMapa();
        mapas.setVisible(true);
    }//GEN-LAST:event_jM_pesquisarMapasActionPerformed

    private void jM_pesquisarCadernetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarCadernetaActionPerformed
        Cadernetas cadernetas = new Cadernetas();
        cadernetas.setVisible(true);
    }//GEN-LAST:event_jM_pesquisarCadernetaActionPerformed

    private void jM_cadastrarMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadastrarMapasActionPerformed
        CadMapa mapas = new CadMapa();
        mapas.setVisible(true);
    }//GEN-LAST:event_jM_cadastrarMapasActionPerformed

    private void jM_cadastrarCadernetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadastrarCadernetasActionPerformed
        Cadernetas cadernetas = new Cadernetas();
        cadernetas.setVisible(true);
    }//GEN-LAST:event_jM_cadastrarCadernetasActionPerformed

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

    private void tabelaMapaIndiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMapaIndiceMouseClicked
        try {
            linhaSelecionadaTableMapaIndice();
        } catch (IOException ex) {
            Logger.getLogger(CadFotografiaAerea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CadFotografiaAerea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaMapaIndiceMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadFotografiaAerea().setVisible(true);
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
    private javax.swing.JLabel imagemMapa;
    private javax.swing.JButton jB_salvarFotografias;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jM_ConsultaPorGaveta;
    private javax.swing.JMenuItem jM_ListagemMapas;
    private javax.swing.JMenuItem jM_ListagemPorFolha;
    private javax.swing.JMenuItem jM_ListagemPorTitulo;
    private javax.swing.JMenuItem jM_cadastrarCadernetas;
    private javax.swing.JMenuItem jM_cadastrarMapas;
    private javax.swing.JMenuItem jM_pesquisarCaderneta;
    private javax.swing.JMenuItem jM_pesquisarMapas;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jP_Botoes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton jb_proximo;
    private javax.swing.JTextField numeroInicial;
    private javax.swing.JTextField orgaoExecutor;
    private javax.swing.JButton pesquisarMapa;
    private javax.swing.JTextField pesquisasDemapas;
    private javax.swing.JTable tabelaFotografiaAerea;
    private javax.swing.JTable tabelaMapaIndice;
    private javax.swing.JLabel totalMapas;
    private javax.swing.JLabel totalfotografiaAerea;
    // End of variables declaration//GEN-END:variables
}
