/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import CONTROLE.FotografiaAereaDao;
import MODELO.CopiaArquivos;
import MODELO.MapaIndice;
import MODELO.TableMapaIndice;
import MODELO.FotografiaAerea;
import MODELO.Mapas;
import MODELO.TableMapas;
import MODELO.MapasTableModelMapaIndice;
import MODELO.ValidaCamposComNumeros;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author gileno.macedo
 */
public class CadMapaIndice extends javax.swing.JFrame implements Serializable {

    MapasTableModelMapaIndice modeloTabelaMapas = new MapasTableModelMapaIndice();
    TableMapaIndice modeTabelaFotografias = new TableMapaIndice();
    Mapas mp;
    Mapas ma;
    MapaIndice fotos;
    Long idMapa, id;
    Long idFotografia;
    ValidaCamposComNumeros validacampos;
    BufferedImage image;
    File fileArquivoselecionado;
    InputStream input;
    BufferedImage imagem;//recebe a imagem da linha selecionada da tabela
    ListIterator<FotografiaAerea> listIterator;
    FotografiaAerea primeiroElemento;
    List<FotografiaAerea> listaMapas;
    String nomeImagemSelecionada, caminhoMapaBd, caminhoImagemCapturada,pastaCriadanobanco;
    File fileDestino;

    public CadMapaIndice() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }

    public void salvarFotos() throws Exception {
        MapaIndice mpIndice = new MapaIndice();
        Mapas mapas = new Mapas();        
        mapas.setCodMapa(idMapa);      
        mpIndice.setEscala(escala.getSelectedItem().toString().toUpperCase());
        mpIndice.setFolhaFotografia(folha.getText().toUpperCase());
        mpIndice.setOrgaoExecutor(orgaoExecutor.getText().toUpperCase());       
        mpIndice.setCaminhoMapaIndeceBD(caminhoMapaBd +nomeImagemSelecionada+"\\"+nomeImagemSelecionada);
        
        mpIndice.setMapas(mapas);
        
        //metodo responsavel por validar os campos na bean.mapas
        ValidatorFactory validaFotos = Validation.buildDefaultValidatorFactory();
        Validator validador = validaFotos.getValidator();
        Set<ConstraintViolation<MapaIndice>> Alerta = validador.validate(mpIndice);
        if (Alerta.size() > 0) {
            for (ConstraintViolation< MapaIndice> error : Alerta) {
                JOptionPane.showMessageDialog(null, error.getMessage());
            }
        } else {
            CopiaArquivos copy = new CopiaArquivos();          
            copy.copiaArquivo(fileArquivoselecionado,nomeImagemSelecionada,caminhoMapaBd);//copia a imagem selecionada para uma pasta no servidor de arquivos
             
            //Envia os dados do mapa para a tabela e salva no banco de dados            
            modeTabelaFotografias.salvaMapaIndice(mpIndice);
           }
    }

    public void carregaImagem() throws IOException, Exception {
        JFileChooser fileChooser = new JFileChooser("c:\\");
        fileChooser.setDialogTitle("Upload de Imagem");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//captura somente pastas
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.imagem", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filtro);
        int resultado = fileChooser.showSaveDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            fileArquivoselecionado = fileChooser.getSelectedFile();//seleciona a imagem na pasta
            JOptionPane.showMessageDialog(null, fileArquivoselecionado);
            caminhoImagemCapturada = fileArquivoselecionado.getAbsolutePath();// pega o caminho da imagem
            nomeImagemSelecionada = fileChooser.getSelectedFile().getName();// captura o nome da imagem na pasta
            imagemMapa.setIcon(redimencionaImagem(caminhoImagemCapturada));// exibe a imagem na label (tela)

        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Arquivo não selecionado");
        }
    }

    private ImageIcon redimencionaImagem(String ImagePath) throws IOException {
        ImageIcon minhaImagem = new ImageIcon(ImagePath);
        Image img = minhaImagem.getImage();
        Image novaImagem = img.getScaledInstance(imagemMapa.getWidth(), imagemMapa.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon im = new ImageIcon(novaImagem);
        image = ImageIO.read(fileArquivoselecionado);
        return im;

    }

    private ImageIcon redimencionaImagem2(String ImagePath) throws IOException {
        ImageIcon minhaImagem = new ImageIcon(ImagePath);
        Image img = minhaImagem.getImage();
        Image novaImagem = img.getScaledInstance(imagemMapa.getWidth(), imagemMapa.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon im = new ImageIcon(novaImagem);
        return im;
    }

    private void limparCampos() {
        folha.setText("");
        orgaoExecutor.setText("");
        escala.setSelectedItem(null);
        imagemMapa.setIcon(null);
        jB_salvarFotografias.setEnabled(false);
        novoMapa.setEnabled(false);
        Deletar.setEnabled(false);
    }

    private void habilitaBotoes() {
        jB_salvarFotografias.setEnabled(true);
        novoMapa.setEnabled(true);
        Deletar.setEnabled(true);
    }

    public void linhaSelecionadaTableMapas() throws IOException {
        mp = modeloTabelaMapas.getLinhaMapas(tabela.getSelectedRow());
        idMapa = mp.getCodMapa();
        folha.setText(mp.getFolha());
        orgaoExecutor.setText(mp.getEditora());
        ano.setValue(Integer.parseInt(mp.getAno()));

        escala.setEditable(false);
        caminhoMapaBd = mp.getCaminho();
        String caminhoImagemSalva = mp.getImagem();
        imagemMapa.setIcon(redimencionaImagem2(caminhoImagemSalva));
        habilitaBotoes();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imagemMapa = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jT_pesquisarMapas = new javax.swing.JTextField();
        pesquisarMapas = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        folha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        orgaoExecutor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        escala = new javax.swing.JComboBox<>();
        ano = new com.toedter.calendar.JYearChooser();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jB_salvarFotografias = new javax.swing.JButton();
        bt_imagem1 = new javax.swing.JToggleButton();
        jB_Sair = new javax.swing.JButton();
        novoMapa = new javax.swing.JButton();
        Deletar = new javax.swing.JButton();
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

        imagemMapa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        imagemMapa.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Mapa Índice", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(130, 60, 122));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        pesquisarMapas.setText("Listar");
        pesquisarMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarMapasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(pesquisarMapas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jT_pesquisarMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jT_pesquisarMapas, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pesquisarMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        folha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        folha.setToolTipText("Campo Não Editável");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Folha:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Executor:");

        orgaoExecutor.setToolTipText("Inserir somente  letras e /");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Escala:");

        escala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {" ", "1:50.000", "1:100,000", "1:250:000", "1:500.000", "1:1000.000"}));

        ano.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ano:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(folha)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(escala, javax.swing.GroupLayout.Alignment.LEADING, 0, 259, Short.MAX_VALUE)
                            .addComponent(orgaoExecutor, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(folha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orgaoExecutor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {escala, orgaoExecutor});

        tabela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        tabela.setModel(modeloTabelaMapas
        );
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela);

        jPanel3.setBackground(new java.awt.Color(130, 60, 122));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jB_salvarFotografias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save_14706.png"))); // NOI18N
        jB_salvarFotografias.setToolTipText("Salvar");
        jB_salvarFotografias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_salvarFotografiasActionPerformed(evt);
            }
        });

        bt_imagem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/foto.png"))); // NOI18N
        bt_imagem1.setToolTipText("Carrega Imagem");
        bt_imagem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imagem1ActionPerformed(evt);
            }
        });

        jB_Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair.png"))); // NOI18N
        jB_Sair.setToolTipText("Sair");
        jB_Sair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jB_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_SairActionPerformed(evt);
            }
        });

        novoMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        novoMapa.setToolTipText("Novo Cadastro");
        novoMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoMapaActionPerformed(evt);
            }
        });

        Deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/deletes.png"))); // NOI18N
        Deletar.setToolTipText("Deletar Dados");
        Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(bt_imagem1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jB_salvarFotografias, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(novoMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jB_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Deletar, bt_imagem1, jB_Sair, jB_salvarFotografias, novoMapa});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jB_salvarFotografias, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(bt_imagem1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jB_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(novoMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Deletar)
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Deletar, bt_imagem1, jB_Sair, jB_salvarFotografias, novoMapa});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        try {
            linhaSelecionadaTableMapas();
        } catch (IOException ex) {
            Logger.getLogger(CadFotografiaAerea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaMouseClicked
    public void pesquisaPorFoha() {
        modeloTabelaMapas.consultaMapaPorNome(pesquisarMapas.getText().toUpperCase());
    }


    private void pesquisarMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarMapasActionPerformed
        if (pesquisarMapas.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a Folha para Efetuar a Pesquisa !");
        } else {
            pesquisaPorFoha();
            pesquisarMapas.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisarMapasActionPerformed

    private void jB_salvarFotografiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_salvarFotografiasActionPerformed
        try {
            salvarFotos();
            limparCampos();
        } catch (Exception ex) {
            Logger.getLogger(CadFotografiaAerea.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jB_salvarFotografiasActionPerformed

    private void bt_imagem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imagem1ActionPerformed
        try {
            carregaImagem();
        } catch (Exception ex) {
            Logger.getLogger(CadMapaIndice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_bt_imagem1ActionPerformed

    private void jM_cadastrarMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadastrarMapasActionPerformed
        CadMapa mapas = new CadMapa();
        mapas.setVisible(true);
    }//GEN-LAST:event_jM_cadastrarMapasActionPerformed

    private void jM_cadastrarCadernetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadastrarCadernetasActionPerformed
        Cadernetas cadernetas = new Cadernetas();
        cadernetas.setVisible(true);
    }//GEN-LAST:event_jM_cadastrarCadernetasActionPerformed

    private void jM_pesquisarMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarMapasActionPerformed
        CadMapa mapas = new CadMapa();
        mapas.setVisible(true);
    }//GEN-LAST:event_jM_pesquisarMapasActionPerformed

    private void jM_pesquisarCadernetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarCadernetaActionPerformed
        Cadernetas cadernetas = new Cadernetas();
        cadernetas.setVisible(true);
    }//GEN-LAST:event_jM_pesquisarCadernetaActionPerformed

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

    private void jB_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_SairActionPerformed
        dispose();
    }//GEN-LAST:event_jB_SairActionPerformed

    private void novoMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoMapaActionPerformed
        limparCampos();
    }//GEN-LAST:event_novoMapaActionPerformed

    private void DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletarActionPerformed
//        if (id == null) {
//            JOptionPane.showMessageDialog(null, "Selecione  os Dados Que Deseja Excluir Clicando na Linha da Tabela");
//        } else {
//            int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir os dados do Mapa? " +mp.getTitulo(),null,+ JOptionPane.YES_NO_OPTION);
//            if (resp == JOptionPane.YES_NO_OPTION ) {
//                try {
//                    removerMapas();
//                    limpaCampos();
//                    desabilitaBotoes();
//                } catch (Exception ex) {
//                    Logger.getLogger(CadMapa.class
//                        .getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            int resp2 = JOptionPane.showConfirmDialog(null, "Deseja Também  Excluir as pastas do Mapa? "+mp.getTitulo(),null, + JOptionPane.YES_NO_OPTION);
//            if (resp2 == JOptionPane.YES_NO_OPTION) {
//                try {
//                    removePastaMapDir();
//
//                } catch (Exception ex) {
//                    Logger.getLogger(CadMapa.class
//                        .getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        }
    }//GEN-LAST:event_DeletarActionPerformed

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
            java.util.logging.Logger.getLogger(CadMapaIndice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadMapaIndice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadMapaIndice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadMapaIndice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadMapaIndice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Deletar;
    private com.toedter.calendar.JYearChooser ano;
    private javax.swing.JToggleButton bt_imagem1;
    private javax.swing.JComboBox<String> escala;
    private javax.swing.JTextField folha;
    private javax.swing.JLabel imagemMapa;
    private javax.swing.JButton jB_Sair;
    private javax.swing.JButton jB_salvarFotografias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jT_pesquisarMapas;
    private javax.swing.JButton novoMapa;
    private javax.swing.JTextField orgaoExecutor;
    private javax.swing.JButton pesquisarMapas;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
