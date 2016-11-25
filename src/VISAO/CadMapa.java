/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import CONTROLE.JasperReportConnectionFactory;
import MODELO.ManipulaBuffImage;
import CONTROLE.MapasDao;
import MODELO.CadernetaTableModel;
import MODELO.EnviaArquivos;
import MODELO.Mapas;
import MODELO.MapasTableModel;
import MODELO.ReportUtils;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
public class CadMapa extends javax.swing.JFrame implements Serializable {

    MapasTableModel modeloTabela = new MapasTableModel();
    Mapas mp;
    BufferedImage image;// recebe a imagem selecionada da pasta
    File Arquivoselecionado;
    Long id;
    CadernetaTableModel tbm;
    InputStream input;
    BufferedImage imagem;//recebe a imagem da linha selecionada da tabela
   

    public CadMapa() {
        initComponents();
        setLocationRelativeTo(null);
        totalMapas();
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            

    }

    public void Salvar() throws Exception {
        Mapas mapas = new Mapas();
         
        if (verificaCampos()) {
            mapas.setTitulo(titulo.getText().toUpperCase());
            mapas.setFolha(folha.getText().toUpperCase());
            mapas.setEditora(editora.getText().toUpperCase());
            mapas.setTipo(tipo.getSelectedItem().toString().toUpperCase());
            mapas.setAno(String.valueOf(ano.getValue()).toUpperCase());
            mapas.setEscala(escala.getSelectedItem().toString().toUpperCase());
            mapas.setGaveta(gaveta.getSelectedItem().toString().toUpperCase());
            mapas.setQuantidade(quantidade.getSelectedItem().toString().toUpperCase());
            mapas.setImagem(ManipulaBuffImage.getImgBytes(image));
            //metodo responsavel por validar os campos na bean.mapas
            ValidatorFactory validaMapas = Validation.buildDefaultValidatorFactory();
            Validator validador = validaMapas.getValidator();
            Set<ConstraintViolation<Mapas>> Alerta = validador.validate(mapas);
            if (Alerta.size() > 0) {
                for (ConstraintViolation< Mapas> error : Alerta) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }
            } else {
                //Envia os dados do mapa para a tabela e salva no banco de dados
                modeloTabela.adicionaMapa(mapas);

                limpaCampos();
                totalMapas();
            }
        }
    }

    public void alterarDados() throws Exception {
        Mapas mapas = new Mapas();
        if (verificaCampos()) {
            mapas.setCodMapa(id);
            mapas.setTitulo(titulo.getText().toUpperCase());
            mapas.setFolha(folha.getText().toUpperCase());
            mapas.setEditora(editora.getText().toUpperCase());
            mapas.setTipo(tipo.getSelectedItem().toString().toUpperCase());
            mapas.setAno(String.valueOf(ano.getValue()).toUpperCase());
            mapas.setEscala(escala.getSelectedItem().toString().toUpperCase());
            mapas.setGaveta(gaveta.getSelectedItem().toString().toUpperCase());
            mapas.setQuantidade(quantidade.getSelectedItem().toString().toUpperCase());
            mapas.setImagem(ManipulaBuffImage.getImgBytes(imagem));
            //metodo responsavel por validar os campos na bean.mapas      
            ValidatorFactory validaMapas = Validation.buildDefaultValidatorFactory();
            Validator validador = validaMapas.getValidator();
            Set<ConstraintViolation<Mapas>> errors = validador.validate(mapas);
            if (errors.size() > 0) {
                for (ConstraintViolation< Mapas> error : errors) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }
            } else {
                //Envia os dados do mapa para a tabela e salva no banco de dados
                modeloTabela.adicionaMapa(mapas);
                modeloTabela.atualizaListadeMapa(mp);
                limpaCampos();
                totalMapas();

            }
        }
    }

    public void salvaMapasRar() throws IOException, Exception {
         Mapas mpas = new Mapas();  
         
        //seleciona o caminho onde sera capturado a pasta com os mapas
        JFileChooser chooser = new JFileChooser("c:\\");
        chooser.setDialogTitle("Upload de pastas Georeferenciadas");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//captura somente pastas
        int res = chooser.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            String caminho = chooser.getSelectedFile().getAbsolutePath();//caminho da pasta selecionada
           
       
            File diretorio = new File(caminho);//cria um arquivo com os dados da pasta selecionada  
          
            File arquivos[] = diretorio.listFiles();//cria uma lista de arquivos que foram selecionados

            String Titulo = titulo.getText().toUpperCase();
            String Folha = folha.getText().toUpperCase();
            String nome = (Titulo + "_" + Folha);           
            String extensao = ".rar";
            String local = "E:\\NovoMapa";
            File saida = new File(local, nome + extensao);
            EnviaArquivos zipe = new EnviaArquivos(); 
            zipe.zip(arquivos, saida);//passa a lista de arquivo por parametro e retorna o caminho onde sera salvo o arquivo rar.
             mpas.setCaminho(local);
             JOptionPane.showMessageDialog(null, local);
           
        }
    }
    
    public void recuperaMapasRar(){
       String caminho = "";
       File arquivo = new File(caminho);
        try {
            Desktop.getDesktop().open(arquivo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex,  "ERRO",JOptionPane.ERROR_MESSAGE);
        }
    
    }
    

    public void removerMapas() throws Exception {
        MapasDao mapasDao = new MapasDao();
        mapasDao.removeMapas(id);
        modeloTabela.removeMapa(mp);
    }

    public void pesquisaPorFoha() {
        modeloTabela.consultaMapaPorNome(pesquisa.getText().toUpperCase());

    }

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

    public void exibiImagemLabel(byte[] minhaImg, javax.swing.JLabel label) {
        //primeiro verifica se tem a imagem
        //se tem convert para inputstream que é o formato reconhecido pelo ImageIO

        if (minhaImg != null) {
            input = new ByteArrayInputStream(minhaImg);
            try {
                imagem = ImageIO.read(input);
                ImageIcon minhaImagem = new ImageIcon(imagem);
                Image img = minhaImagem.getImage();
                Image novaImagem = img.getScaledInstance(imagemMapa.getWidth(), imagemMapa.getHeight(), Image.SCALE_REPLICATE);
                label.setIcon(new ImageIcon(novaImagem));
            } catch (IOException ex) {
            }
        } else {
            label.setIcon(null);

        }

    }

    public void limpaCampos() {
        titulo.setText("");
        titulo.setEditable(true);
        folha.setText("");
        folha.setEditable(true);
        editora.setText("");
        editora.setEditable(true);
        tipo.setSelectedItem(null);
        gaveta.setSelectedItem(null);
        imagemMapa.setIcon(null);
        escala.setSelectedItem(null);
        quantidade.setSelectedItem(null);
        ano.setValue(2016);
        id = null;

    }

    public void totalMapas() {
        MapasDao mapasDao = new MapasDao();
        int total = mapasDao.getMapasCount();
        totalMapas.setText(String.valueOf(total));
    }

    public void linhaSelecionada() {
        mp = modeloTabela.getLinhaMapas(tabela.getSelectedRow());
        id = mp.getCodMapa();
        titulo.setText(mp.getTitulo());
        folha.setText(mp.getFolha());
        editora.setText(mp.getEditora());
        tipo.setSelectedItem(mp.getTipo());
        escala.setSelectedItem(mp.getEscala());
        ano.setValue(Integer.parseInt(mp.getAno()));
        gaveta.setSelectedItem(mp.getGaveta());
        quantidade.setSelectedItem(String.valueOf(mp.getQuantidade()));
        MapasDao mDao = new MapasDao();
        Mapas mapas = mDao.consultaMapasId(id);
        exibiImagemLabel(mapas.getImagem(), imagemMapa);
    }

    public boolean verificaCampos() {
        if (imagemMapa.getIcon() == null) {
            JOptionPane.showMessageDialog(null, "Uma IMAGEM  deve ser selecionada");
            return false;
        } else {
            return true;
        }
    }

    public void abrirRelatorioMapasPorGaveta() throws JRException, SQLException {
        //Consulta de mapas por folha
        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/MapasPorGaveta.jasper");
        Map parametros = new HashMap();
        String texto = JOptionPane.showInputDialog("Informe Iniciais da folha para consulta, seguido de %").toUpperCase();
        parametros.put("ConsultaPorGaveta", "0" + texto + "%");
        ReportUtils.openReport("Relatorio de Mapas ", inputStream, parametros,
                JasperReportConnectionFactory.getPostgresConnection());
    }

    public void abrirRelatorioMapasPorFolha() throws JRException, SQLException {
        //Consulta de mapas por folha
        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/MapasPorFolha.jasper");
        Map parametros = new HashMap();
        String texto = JOptionPane.showInputDialog("Informe Iniciais da folha para consulta").toUpperCase();
        parametros.put("ConsultaPorFolha", texto + "%");
        ReportUtils.openReport("Relatorio de Mapas ", inputStream, parametros,
                JasperReportConnectionFactory.getPostgresConnection());
    }

    public void abriRelatorioMapasPorTitulo() throws JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/MapasPorTitulo.jasper");
        Map parametros = new HashMap();
        String texto = JOptionPane.showInputDialog("Informe Iniciais da folha para consulta").toUpperCase();
        parametros.put("ConsultaPorTitulo", texto + "%");
        ReportUtils.openReport("Relatorio de Mapas ", inputStream, parametros,
                JasperReportConnectionFactory.getPostgresConnection());
    }

    public void abrirRelatorioMapas() throws JRException, SQLException {
        //Consulta todos os Mapas
        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/Mapas.jasper");
        Map parametros = new HashMap();
        ReportUtils.openReport("Relatorio de Mapas ", inputStream, parametros,
                JasperReportConnectionFactory.getPostgresConnection());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_Mapas = new javax.swing.JPanel();
        imagemMapa = new javax.swing.JLabel();
        jP_campos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        titulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        folha = new javax.swing.JTextField();
        editora = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        ano = new com.toedter.calendar.JYearChooser();
        escala = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        gaveta = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        quantidade = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        carregarImagem = new javax.swing.JButton();
        EnviaImagem = new javax.swing.JButton();
        SalvarMapa = new javax.swing.JButton();
        Deletar = new javax.swing.JButton();
        novoMapa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        totalMapas = new javax.swing.JLabel();
        pesquisarMapas = new javax.swing.JButton();
        pesquisa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jM_ListagemMapas = new javax.swing.JMenuItem();
        jM_ListagemPorFolha = new javax.swing.JMenuItem();
        jM_ConsultaPorGaveta = new javax.swing.JMenuItem();
        jM_ListagemPorTitulo = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jP_Mapas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        imagemMapa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jP_campos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Título:");

        titulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        titulo.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Folha:");

        folha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        editora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Editora:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tipo:");

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "CARTA IMAGEM RADAR", "CARTA TOPOGRÁFICA","CARTA TEMÁTICA DE HIDROLOGIA","CARTA PLANIMÉTRICA","BASE PLANIMÉTRICA"}));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ano:");

        ano.setFont(new java.awt.Font("Tahoma", 0, 14));

        escala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {" ", "1:50.000", "1:100,000", "1:250:000", "1:500.000", "1:1000.000"}));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Escala:");

        gaveta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        gaveta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ","01", "02", "03", "04", "05", "06", "07", "08","09","10"}));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Gaveta:");

        quantidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        quantidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ","1", "2", "3", "4", "5", "6", "7", "8","9","10","11", "12", "13", "14", "15", "16", "17", "18","19","20"}));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Quantidade:");

        javax.swing.GroupLayout jP_camposLayout = new javax.swing.GroupLayout(jP_campos);
        jP_campos.setLayout(jP_camposLayout);
        jP_camposLayout.setHorizontalGroup(
            jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_camposLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(folha, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addComponent(titulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jP_camposLayout.createSequentialGroup()
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_camposLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editora, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo, 0, 237, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gaveta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jP_camposLayout.setVerticalGroup(
            jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_camposLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(folha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editora, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(gaveta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6))
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(escala, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ano, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_camposLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jP_camposLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ano, escala, gaveta, quantidade, tipo});

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        carregarImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_512x512_35976.png"))); // NOI18N
        carregarImagem.setToolTipText("Carregar Mapa");
        carregarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carregarImagemActionPerformed(evt);
            }
        });

        EnviaImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/webdev-search-icon.png"))); // NOI18N
        EnviaImagem.setToolTipText("Mapas Zoom");
        EnviaImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviaImagemActionPerformed(evt);
            }
        });

        SalvarMapa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SalvarMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Hardware-Floppy-icon (Custom).png"))); // NOI18N
        SalvarMapa.setToolTipText("Salvar ou Atulizar Dados");
        SalvarMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarMapaActionPerformed(evt);
            }
        });

        Deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/apaga.jpg"))); // NOI18N
        Deletar.setToolTipText("Deletar Dados");
        Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletarActionPerformed(evt);
            }
        });

        novoMapa.setIcon(new javax.swing.ImageIcon("C:\\Users\\gileno.macedo\\Documents\\NetBeansProjects\\ArqMap\\src\\imagens\\novo2.jpg")); // NOI18N
        novoMapa.setToolTipText("Novo Cadastro");
        novoMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoMapaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(carregarImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EnviaImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SalvarMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(novoMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Deletar, EnviaImagem, SalvarMapa, carregarImagem});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(carregarImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(EnviaImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SalvarMapa, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
            .addComponent(Deletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(novoMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        tabela.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabela.setModel(modeloTabela);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jPanel2.setBackground(new java.awt.Color(130, 60, 122));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de Mapas Cadastrados  :  ");

        totalMapas.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        totalMapas.setForeground(new java.awt.Color(255, 255, 255));
        totalMapas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        pesquisarMapas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pesquisarMapas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_search_find_maps_10803.png"))); // NOI18N
        pesquisarMapas.setToolTipText("Pesquisar");
        pesquisarMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarMapasActionPerformed(evt);
            }
        });

        pesquisa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(pesquisarMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pesquisarMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(totalMapas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(130, 60, 122));
        jLabel9.setText("Imagem do Mapa");
        jLabel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        javax.swing.GroupLayout jP_MapasLayout = new javax.swing.GroupLayout(jP_Mapas);
        jP_Mapas.setLayout(jP_MapasLayout);
        jP_MapasLayout.setHorizontalGroup(
            jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_MapasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_MapasLayout.createSequentialGroup()
                        .addGroup(jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jP_campos, javax.swing.GroupLayout.PREFERRED_SIZE, 699, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jP_MapasLayout.setVerticalGroup(
            jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_MapasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_MapasLayout.createSequentialGroup()
                        .addComponent(jP_campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("Cadastros");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Listagem");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Relatórios");

        jM_ListagemMapas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemMapas.setText("Mapas");
        jM_ListagemMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemMapasActionPerformed(evt);
            }
        });
        jMenu3.add(jM_ListagemMapas);

        jM_ListagemPorFolha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemPorFolha.setText("Mapas Por Folha");
        jM_ListagemPorFolha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemPorFolhaActionPerformed(evt);
            }
        });
        jMenu3.add(jM_ListagemPorFolha);

        jM_ConsultaPorGaveta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ConsultaPorGaveta.setText("Mapas Por Gaveta");
        jM_ConsultaPorGaveta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ConsultaPorGavetaActionPerformed(evt);
            }
        });
        jMenu3.add(jM_ConsultaPorGaveta);

        jM_ListagemPorTitulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemPorTitulo.setText("Mapas Por Título");
        jM_ListagemPorTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemPorTituloActionPerformed(evt);
            }
        });
        jMenu3.add(jM_ListagemPorTitulo);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Ajuda");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_Mapas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_Mapas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void carregarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carregarImagemActionPerformed
        try {
            Cursor cursorAguandando = new Cursor(Cursor.WAIT_CURSOR);
            setCursor(cursorAguandando);
            carregaImagem();
            Cursor cursorNormal = new Cursor(Cursor.DEFAULT_CURSOR);
            setCursor(cursorNormal);
        } catch (IOException ex) {
            Logger.getLogger(CadMapa.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_carregarImagemActionPerformed

    private void EnviaImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviaImagemActionPerformed
        // envia um bufferedImage por paramentro para o ZoomImage
        if (imagemMapa.getIcon() == null) {
            JOptionPane.showMessageDialog(null, "Selecione o Mapa Clicando na Tabela Abaixo !");
        } else {
            ZoomImage im = new ZoomImage();
            im.initializeImagem(imagem);
            im.setVisible(true);
        }
    }//GEN-LAST:event_EnviaImagemActionPerformed

    private void SalvarMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarMapaActionPerformed

        try {
            if (id == null) {
                try {
                    Cursor cursorAguandando = new Cursor(Cursor.WAIT_CURSOR);
                    setCursor(cursorAguandando);
                    salvaMapasRar();
                    Salvar();
                    Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                    setCursor(normalCursor);

                } catch (Exception ex) {
                    Logger.getLogger(CadMapa.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    alterarDados();
                } catch (Exception ex) {
                    Logger.getLogger(CadMapa.class
                            .getName()).log(Level.SEVERE, null, ex);

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CadMapa.class
                    .getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_SalvarMapaActionPerformed


    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        linhaSelecionada();
    }//GEN-LAST:event_tabelaMouseClicked

    private void DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletarActionPerformed
        if (id == null) {
            JOptionPane.showMessageDialog(null, "Selecione  os Dados Que Deseja Excluir Clicando na Linha da Tabela");
        } else {
            int resp = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir Estes Dados ?   " + JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_NO_OPTION) {
                try {
                    removerMapas();
                    limpaCampos();
                } catch (Exception ex) {
                    Logger.getLogger(CadMapa.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_DeletarActionPerformed

    private void pesquisarMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarMapasActionPerformed
        if (pesquisa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a Folha para Efetuar a Pesquisa !");
        } else {
            pesquisaPorFoha();
            totalMapas();

            pesquisa.setText("");
        }
    }//GEN-LAST:event_pesquisarMapasActionPerformed


    private void pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisaActionPerformed

    }//GEN-LAST:event_pesquisaActionPerformed

    private void novoMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoMapaActionPerformed


    }//GEN-LAST:event_novoMapaActionPerformed

    private void jM_ListagemMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemMapasActionPerformed
        try {
            abrirRelatorioMapas();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemMapasActionPerformed

    private void jM_ListagemPorFolhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemPorFolhaActionPerformed
        try {
            abrirRelatorioMapasPorFolha();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemPorFolhaActionPerformed

    private void jM_ConsultaPorGavetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ConsultaPorGavetaActionPerformed
        try {
            abrirRelatorioMapasPorGaveta();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ConsultaPorGavetaActionPerformed

    private void jM_ListagemPorTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemPorTituloActionPerformed
        try {
            abriRelatorioMapasPorTitulo();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemPorTituloActionPerformed

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
            java.util.logging.Logger.getLogger(CadMapa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadMapa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadMapa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadMapa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadMapa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Deletar;
    private javax.swing.JButton EnviaImagem;
    private javax.swing.JButton SalvarMapa;
    private com.toedter.calendar.JYearChooser ano;
    private javax.swing.JButton carregarImagem;
    private javax.swing.JTextField editora;
    private javax.swing.JComboBox<String> escala;
    private javax.swing.JTextField folha;
    private javax.swing.JComboBox<String> gaveta;
    private javax.swing.JLabel imagemMapa;
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
    private javax.swing.JMenuItem jM_ConsultaPorGaveta;
    private javax.swing.JMenuItem jM_ListagemMapas;
    private javax.swing.JMenuItem jM_ListagemPorFolha;
    private javax.swing.JMenuItem jM_ListagemPorTitulo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jP_Mapas;
    private javax.swing.JPanel jP_campos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton novoMapa;
    private javax.swing.JTextField pesquisa;
    private javax.swing.JButton pesquisarMapas;
    private javax.swing.JComboBox<String> quantidade;
    private javax.swing.JTable tabela;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JTextField titulo;
    private javax.swing.JLabel totalMapas;
    // End of variables declaration//GEN-END:variables
}
