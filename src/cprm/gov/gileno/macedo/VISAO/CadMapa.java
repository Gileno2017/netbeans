/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cprm.gov.gileno.macedo.VISAO;

import cprm.gov.gileno.macedo.CONTROLE.JasperReportConnectionFactory;
import cprm.gov.gileno.macedo.CONTROLE.MapasDao;
import cprm.gov.gileno.macedo.MODELO.TableCaderneta;
import cprm.gov.gileno.macedo.MODELO.CopiaArquivos;
import cprm.gov.gileno.macedo.MODELO.Mapas;
import cprm.gov.gileno.macedo.MODELO.TableMapas;
import cprm.gov.gileno.macedo.MODELO.ReportUtils;
import cprm.gov.gileno.macedo.MODELO.Sessao;
import cprm.gov.gileno.macedo.MODELO.Usuario;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.image.BufferedImage;
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

    TableMapas modeloTabela = new TableMapas();
    Mapas mp;

    File fileArquivoselecionado;
    File fileDestino;
    Long id;
    TableCaderneta tbm;
    InputStream inputStreamImage;
    BufferedImage bufferedImagem;//recebe a imagem da linha selecionada da tabela
    String caminhoImagem;
    String nomeImagemSelecionada;
    String caminho;

    public CadMapa() {
        initComponents();
        setLocationRelativeTo(null);
        totalMapas();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        verificaUsuarioLogado();
        this.setBackground(Color.RED);
        
   
    }
    
    public void verificaUsuarioLogado(){
     Usuario usu =  Sessao.getInstance().getUsuario();
     
     if(usu.getPerfil().equals("ADMIN")){
                    habilitaBotoes();  
                     SalvarMapa.setEnabled(false);
                    usuario.setText(usu.getNome().toUpperCase());
                    this.dispose();
                     
        }else{                   
                    TelaPrincipal telaPrincipal = new TelaPrincipal();
                    telaPrincipal.setVisible(true);                    
                     usuario.setText(usu.getNome());                
                }    
    
    }

    public void SalvaMapas() throws Exception {
        File src = new File("") ;
        //Envia os dados do mapa para a tabela e salva no banco de dados            
        //seleciona o caminhoPastaCapiturada onde sera capturado a pasta com os mapas
        JFileChooser chooser = new JFileChooser("c:\\");
        chooser.setDialogTitle("Uploud das pastas Georeferenciadas do Mapa  " + titulo.getText().toUpperCase());
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//captura somente pastas
        int res = chooser.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            String caminhoPastaCapiturada = chooser.getSelectedFile().getAbsolutePath();//caminho da pasta 
            String nomeImagem = chooser.getSelectedFile().getName();
            String pastaNoBD = "\\\\serv-arquivo-ma\\GERIDE2\\ARQUIVOS_MAPOTECA";    //caminho onde sera salvo os mapas  no BD        
            src = new File(caminhoPastaCapiturada);// caminho da imagem para ser copiada
            fileDestino = new File(pastaNoBD, nomeImagem);            
        }
        
        
          
        Mapas mapas = new Mapas();
        mapas.setTitulo(titulo.getText().toUpperCase());
        mapas.setFolha(folha.getText().toUpperCase());
        mapas.setEditora(editora.getText().toUpperCase());
        mapas.setTipo(tipo.getSelectedItem().toString().toUpperCase());
        mapas.setAno(String.valueOf(ano.getValue()).toUpperCase());
        mapas.setEscala(escala.getSelectedItem().toString().toUpperCase());
        mapas.setGaveta(gaveta.getSelectedItem().toString().toUpperCase());
        mapas.setQuantidade(quantidade.getSelectedItem().toString().toUpperCase());
        mapas.setHemisferio(hemisferio.getSelectedItem().toString().toUpperCase());
        mapas.setImagem(fileDestino + "\\" + nomeImagemSelecionada);
        mapas.setCaminho(String.valueOf(fileDestino));

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
            CopiaArquivos cp = new CopiaArquivos();
            cp.copyAll(src, fileDestino, true);
            modeloTabela.adicionaMapa(mapas);
            limpaCampos();
            totalMapas();
          
        }
    }

    public void alteraMapas() throws Exception {
        Mapas mapas = new Mapas();
        mapas.setCodMapa(id);
        mapas.setTitulo(titulo.getText().toUpperCase());
        mapas.setFolha(folha.getText().toUpperCase());
        mapas.setEditora(editora.getText().toUpperCase());
        mapas.setTipo(tipo.getSelectedItem().toString().toUpperCase());
        mapas.setAno(String.valueOf(ano.getValue()).toUpperCase());
        mapas.setEscala(escala.getSelectedItem().toString().toUpperCase());
        mapas.setGaveta(gaveta.getSelectedItem().toString().toUpperCase());
        mapas.setQuantidade(quantidade.getSelectedItem().toString().toUpperCase());
        mapas.setHemisferio(hemisferio.getSelectedItem().toString().toUpperCase());
        // mapas.setImagem(ManipulaBuffImage.getImgBytes(bufferedImagem));
        mapas.setCaminho(String.valueOf(fileDestino));
        mapas.setImagem(caminhoImagem);
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
        int resp = JOptionPane.showConfirmDialog(null, "Deseja altera os arquivos dos mapas? " + mapas.getTitulo() + JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_NO_OPTION) {
            try {
                File src;
                //Envia os dados do mapa para a tabela e salva no banco de dados            
                //seleciona o caminhoPastaCapiturada onde sera capturado a pasta com os mapas
                JFileChooser chooser = new JFileChooser("c:\\");
                chooser.setDialogTitle("Upload de Pastas Georeferenciadas do Mapa" + titulo.getText());
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//captura somente pastas
                int res = chooser.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    String caminhoImagemCapiturada = chooser.getSelectedFile().getAbsolutePath();//caminho da pasta 
                    String nomeImagem = chooser.getSelectedFile().getName();
                    String pastalNoBD = "\\D:\\NovoMapa";    //caminho onde sera salvo os             
                    src = new File(caminhoImagemCapiturada);
                    fileDestino = new File(pastalNoBD, nomeImagem);
                    CopiaArquivos cp = new CopiaArquivos();
                    cp.copyAll(src, fileDestino, true);
                }
                limpaCampos();
                desabilitaBotoes();
            } catch (Exception ex) {
                Logger.getLogger(CadMapa.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void removerMapas() throws Exception {
        MapasDao mapasDao = new MapasDao();
        mapasDao.removeMapas(id);
        modeloTabela.removeMapa(mp);
    }

    public void removePastaMapDir() {
        String diretorio = mp.getCaminho();
        File folder = new File(diretorio);
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
        }
        boolean success = (new File(diretorio)).delete();
        if (!success) {
            JOptionPane.showMessageDialog(null, "diretorio nao existe");
        }
    }

    public void pesquisaPorFoha() {
        modeloTabela.consultaMapaPorNome(pesquisa.getText().toUpperCase());

    }

    public void carregaImagem() throws IOException, Exception {
        JFileChooser file = new JFileChooser("c:\\");
        file.setDialogTitle("Upload de Imagem");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.imagem", "jpg", "gif", "png");
        file.addChoosableFileFilter(filtro);
        int resultado = file.showSaveDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            fileArquivoselecionado = file.getSelectedFile();
            String path = fileArquivoselecionado.getAbsolutePath();
            nomeImagemSelecionada = fileArquivoselecionado.getName();
            imagemMapa.setIcon(redimencionaImagem2(path));
            SalvarMapa.setEnabled(true);
           
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

    private ImageIcon redimencionaImagem(BufferedImage ImagePath) throws IOException {
        ImageIcon minhaImagem = new ImageIcon(ImagePath);
        Image img = minhaImagem.getImage();
        Image novaImagem = img.getScaledInstance(imagemMapa.getWidth(), imagemMapa.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon im = new ImageIcon(novaImagem);
        return im;
    }

    public void exibiImagemLabel() throws IOException {
        //primeiro verifica se tem a imagem
        File file = new File(caminhoImagem);
        bufferedImagem = ImageIO.read(file);
        imagemMapa.setIcon(redimencionaImagem(bufferedImagem));
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
        hemisferio.setSelectedItem(null);
        ano.setValue(2017);
        id = null;
    }

    public boolean verificaCampos() {
        if (titulo != null && folha != null
                && editora != null && tipo != null
                && gaveta != null && imagemMapa != null
                && quantidade != null && escala != null
                && hemisferio != null && ano != null) {
        }
        return true;
    }

    private void desabilitaBotoes() {
        zoomImagem.setEnabled(false);
        download.setEnabled(false);
        Deletar.setEnabled(false);
        SalvarMapa.setEnabled(false);
    }

    private void habilitaBotoes() {
        zoomImagem.setEnabled(true);
        download.setEnabled(true);
        Deletar.setEnabled(true);
       
    }

    public void totalMapas() {
        MapasDao mapasDao = new MapasDao();
        int total = mapasDao.getMapasCount();
        totalMapas.setText(String.valueOf(total));
    }

    public void linhaSelecionada() throws IOException {
        mp = modeloTabela.getLinhaMapas(tabela.getSelectedRow());
        id = mp.getCodMapa();
        titulo.setText(mp.getTitulo());
        folha.setText(mp.getFolha());
        editora.setText(mp.getEditora());
        tipo.setSelectedItem(mp.getTipo());
        hemisferio.setSelectedItem(mp.getHemisferio());
        escala.setSelectedItem(mp.getEscala());
        ano.setValue(Integer.parseInt(mp.getAno()));
        gaveta.setSelectedItem(mp.getGaveta());
        quantidade.setSelectedItem(String.valueOf(mp.getQuantidade()));

        MapasDao mDao = new MapasDao();
        Mapas mapas = mDao.consultaMapasId(id);
        caminho = mapas.getCaminho();
        caminhoImagem = (mapas.getImagem());
        exibiImagemLabel();
        fileDestino = new File(caminho);
        habilitaBotoes();
    }

    // Metodo responsável por fazer o download do mapa     
    public void dounloadPastas() throws IOException {
        MapasDao mDao = new MapasDao();
        Mapas mps = mDao.consultaMapasId(id);
        String caminhos = mps.getCaminho();
        JFileChooser chooser = new JFileChooser("c:\\");
        chooser.setDialogTitle("Downloads de pastas Georeferenciadas");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//captura somente pastas
        int res = chooser.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            String pastadeDownloads = chooser.getSelectedFile().getAbsolutePath();//caminho da pasta selecionada para downloads                  
            String Titulo = titulo.getText().toUpperCase();
            String Folha = folha.getText().toUpperCase();
            String nome = (Titulo + "_" + Folha);
            File origem = new File(caminhos);
            File downloads = new File(pastadeDownloads, nome);
            CopiaArquivos cp = new CopiaArquivos();
            cp.copyAll(origem, downloads, true);
            JOptionPane.showMessageDialog(null, "Arquivos " + nome + " copiados para: " + pastadeDownloads);
        }

    }

    public void abrirRelatorioMapasPorGaveta() throws JRException, SQLException {
        //Consulta de mapas por folha
        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/MapasPorGaveta.jasper");
        Map parametros = new HashMap();
        String texto = JOptionPane.showInputDialog("Informe Iniciais da folha para consulta").toUpperCase();
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        JPmenu = new javax.swing.JPanel();
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
        hemisferio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        ano = new com.toedter.calendar.JYearChooser();
        escala = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        gaveta = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        quantidade = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        carregarImagem = new javax.swing.JButton();
        zoomImagem = new javax.swing.JButton();
        SalvarMapa = new javax.swing.JButton();
        Deletar = new javax.swing.JButton();
        novoMapa = new javax.swing.JButton();
        download = new javax.swing.JToggleButton();
        jB_Sair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        totalMapas = new javax.swing.JLabel();
        pesquisarMapas = new javax.swing.JButton();
        pesquisa = new javax.swing.JTextField();
        jl_copiArquivo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jM_cadastros = new javax.swing.JMenu();
        jM_fotografiaAerea = new javax.swing.JMenuItem();
        jM_cadernetas = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jM_pesquisarfotografias = new javax.swing.JMenuItem();
        jM_pesquisarCaderneta = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jM_ListagemMapas = new javax.swing.JMenuItem();
        jM_ListagemPorFolha = new javax.swing.JMenuItem();
        jM_ConsultaPorGaveta = new javax.swing.JMenuItem();
        jM_ListagemPorTitulo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        JPmenu.setBackground(new java.awt.Color(82, 60, 60));

        javax.swing.GroupLayout JPmenuLayout = new javax.swing.GroupLayout(JPmenu);
        JPmenu.setLayout(JPmenuLayout);
        JPmenuLayout.setHorizontalGroup(
            JPmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JPmenuLayout.setVerticalGroup(
            JPmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jP_Mapas.setBackground(new java.awt.Color(140, 123, 117));
        jP_Mapas.setForeground(new java.awt.Color(82, 60, 60));

        imagemMapa.setBackground(new java.awt.Color(188, 170, 164));

        jP_campos.setBackground(new java.awt.Color(188, 170, 164));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Título:");

        titulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        titulo.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Folha:");

        folha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        editora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Editora:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo:");

        hemisferio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "NORTE", "SUL"}));
        hemisferio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hemisferioActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ano:");

        ano.setFont(new java.awt.Font("Tahoma", 0, 14));

        escala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {" ", "1:50.000", "1:100,000", "1:250:000", "1:500.000", "1:1000.000"}));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Escala:");

        gaveta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        gaveta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ","01", "02", "03", "04", "05", "06", "07", "08","09","10"}));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gaveta:");

        quantidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        quantidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ","1", "2", "3", "4", "5", "6", "7", "8","9","10","11", "12", "13", "14", "15", "16", "17", "18","19","20"}));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Qtd:");

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "CARTA IMAGEM RADAR", "CARTA TOPOGRÁFICA","CARTA TEMÁTICA DE HIDROLOGIA","CARTA PLANIMÉTRICA","BASE PLANIMÉTRICA","MOSAICO SEMI CONTROLADO DE RADAR"}));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Hemisfério:");

        usuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usuario.setForeground(new java.awt.Color(255, 255, 255));
        usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8_User_32px.png"))); // NOI18N

        javax.swing.GroupLayout jP_camposLayout = new javax.swing.GroupLayout(jP_campos);
        jP_campos.setLayout(jP_camposLayout);
        jP_camposLayout.setHorizontalGroup(
            jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_camposLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jP_camposLayout.createSequentialGroup()
                                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(hemisferio, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jP_camposLayout.createSequentialGroup()
                                .addComponent(gaveta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel7)
                                .addGap(33, 33, 33)
                                .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jP_camposLayout.createSequentialGroup()
                        .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(titulo)
                            .addGroup(jP_camposLayout.createSequentialGroup()
                                .addComponent(editora, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(folha, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jP_camposLayout.setVerticalGroup(
            jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_camposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editora, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gaveta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(ano, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jP_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(hemisferio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );

        jP_camposLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ano, escala, gaveta, quantidade});

        jPanel1.setBackground(new java.awt.Color(188, 170, 164));

        carregarImagem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        carregarImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/foto.png"))); // NOI18N
        carregarImagem.setToolTipText("Carregar Mapa");
        carregarImagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        carregarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carregarImagemActionPerformed(evt);
            }
        });

        zoomImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisa.png"))); // NOI18N
        zoomImagem.setToolTipText("Mapas Zoom");
        zoomImagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zoomImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomImagemActionPerformed(evt);
            }
        });

        SalvarMapa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SalvarMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save_14706.png"))); // NOI18N
        SalvarMapa.setToolTipText("Salvar ou Atualizar Dados");
        SalvarMapa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SalvarMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarMapaActionPerformed(evt);
            }
        });

        Deletar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/deletes.png"))); // NOI18N
        Deletar.setToolTipText("Deletar Dados");
        Deletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletarActionPerformed(evt);
            }
        });

        novoMapa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        novoMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        novoMapa.setToolTipText("Novo Cadastro");
        novoMapa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        novoMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoMapaActionPerformed(evt);
            }
        });

        download.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Download_Drive_30714.png"))); // NOI18N
        download.setToolTipText("Download de Arquivos");
        download.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        download.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadActionPerformed(evt);
            }
        });

        jB_Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair.png"))); // NOI18N
        jB_Sair.setToolTipText("Sair");
        jB_Sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB_Sair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jB_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_SairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(carregarImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zoomImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SalvarMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(download, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(novoMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jB_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Deletar, SalvarMapa, download, zoomImagem});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jB_Sair, novoMapa});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(novoMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(download, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SalvarMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zoomImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(carregarImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jB_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Deletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Deletar, SalvarMapa, carregarImagem, download, jB_Sair, novoMapa, zoomImagem});

        tabela.setBackground(new java.awt.Color(188, 170, 164));
        tabela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        tabela.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabela.setModel(modeloTabela);
        tabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabela.setRowHeight(22);
        tabela.setIntercellSpacing(new java.awt.Dimension(3, 0));
        tabela.setName(""); // NOI18N
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jPanel2.setBackground(new java.awt.Color(188, 170, 164));
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
        pesquisarMapas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                .addGap(6, 6, 6)
                .addComponent(pesquisarMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalMapas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pesquisarMapas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jP_MapasLayout = new javax.swing.GroupLayout(jP_Mapas);
        jP_Mapas.setLayout(jP_MapasLayout);
        jP_MapasLayout.setHorizontalGroup(
            jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_MapasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jP_MapasLayout.createSequentialGroup()
                        .addComponent(imagemMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jP_campos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(48, 48, 48)
                .addComponent(jl_copiArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jP_MapasLayout.setVerticalGroup(
            jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_MapasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_MapasLayout.createSequentialGroup()
                        .addGroup(jP_MapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jP_MapasLayout.createSequentialGroup()
                                .addComponent(jl_copiArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jP_campos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addComponent(imagemMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(82, 60, 60));

        jM_cadastros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jM_cadastros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/computer.png"))); // NOI18N
        jM_cadastros.setToolTipText("Cadastro");
        jM_cadastros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jM_fotografiaAerea.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jM_fotografiaAerea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/picture_photo_preview_2628.png"))); // NOI18N
        jM_fotografiaAerea.setText("Fotografias");
        jM_fotografiaAerea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_fotografiaAereaActionPerformed(evt);
            }
        });
        jM_cadastros.add(jM_fotografiaAerea);

        jM_cadernetas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jM_cadernetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/notebook_notes_147.png"))); // NOI18N
        jM_cadernetas.setText("Caderneta de Campo");
        jM_cadernetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_cadernetasActionPerformed(evt);
            }
        });
        jM_cadastros.add(jM_cadernetas);

        jMenuBar1.add(jM_cadastros);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search_Computer_1_36841.png"))); // NOI18N
        jMenu5.setToolTipText("Pesquisar");
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jM_pesquisarfotografias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        jM_pesquisarfotografias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/picture_photo_preview_2628.png"))); // NOI18N
        jM_pesquisarfotografias.setText("Fotografias");
        jM_pesquisarfotografias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_pesquisarfotografiasActionPerformed(evt);
            }
        });
        jMenu5.add(jM_pesquisarfotografias);

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

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_notes_930.png"))); // NOI18N
        jMenu3.setToolTipText("Relatórios");
        jMenu3.setAutoscrolls(true);
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jM_ListagemMapas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemMapas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_512x512_35976.png"))); // NOI18N
        jM_ListagemMapas.setText("Mapas");
        jM_ListagemMapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemMapasActionPerformed(evt);
            }
        });
        jMenu3.add(jM_ListagemMapas);

        jM_ListagemPorFolha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemPorFolha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/folha.png"))); // NOI18N
        jM_ListagemPorFolha.setText("Mapas Por Folha");
        jM_ListagemPorFolha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemPorFolhaActionPerformed(evt);
            }
        });
        jMenu3.add(jM_ListagemPorFolha);

        jM_ConsultaPorGaveta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ConsultaPorGaveta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/gaveta.png"))); // NOI18N
        jM_ConsultaPorGaveta.setText("Mapas Por Gaveta");
        jM_ConsultaPorGaveta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ConsultaPorGavetaActionPerformed(evt);
            }
        });
        jMenu3.add(jM_ConsultaPorGaveta);

        jM_ListagemPorTitulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK));
        jM_ListagemPorTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/titulo.png"))); // NOI18N
        jM_ListagemPorTitulo.setText("Mapas Por Título");
        jM_ListagemPorTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_ListagemPorTituloActionPerformed(evt);
            }
        });
        jMenu3.add(jM_ListagemPorTitulo);
        jMenu3.add(jSeparator1);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/people_6080.png"))); // NOI18N
        jMenuItem5.setText("Caderneta por Geólogo");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/projeto.png"))); // NOI18N
        jMenuItem6.setText("Caderneta por Projeto");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Help_26772.png"))); // NOI18N
        jMenu4.setToolTipText("Ajuda");
        jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jP_Mapas, javax.swing.GroupLayout.PREFERRED_SIZE, 1169, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jP_Mapas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        } catch (Exception ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_carregarImagemActionPerformed

    private void zoomImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomImagemActionPerformed

// envia um bufferedImage por paramentro para o ZoomImage
        if (imagemMapa.getIcon() == null) {
            JOptionPane.showMessageDialog(null, "Selecione o Mapa Clicando na Tabela Abaixo !");
        } else {
            ZoomImage im = new ZoomImage();
            im.initializeImagem(bufferedImagem);
            im.setVisible(true);
        }
    }//GEN-LAST:event_zoomImagemActionPerformed

    private void SalvarMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarMapaActionPerformed

        try {
            if (id == null) {
                try {
                    Cursor cursorAguandando = new Cursor(Cursor.WAIT_CURSOR);
                    setCursor(cursorAguandando);

                    SalvaMapas();

                    Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                    setCursor(normalCursor);
                } catch (Exception ex) {
                    Logger.getLogger(CadMapa.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    verificaCampos();
                    alteraMapas();

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
        try {
            linhaSelecionada();
        } catch (IOException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletarActionPerformed
        if (id == null) {
            JOptionPane.showMessageDialog(null, "Selecione  os Dados Que Deseja Excluir Clicando na Linha da Tabela");
        } else {
            int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir os dados do Mapa? " +mp.getTitulo(),null,+ JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_NO_OPTION ) {
                try {
                    removerMapas();
                    limpaCampos();
                    desabilitaBotoes();
                } catch (Exception ex) {
                    Logger.getLogger(CadMapa.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            int resp2 = JOptionPane.showConfirmDialog(null, "Deseja Também  Excluir as pastas do Mapa? "+mp.getTitulo(),null, + JOptionPane.YES_NO_OPTION);
            if (resp2 == JOptionPane.YES_NO_OPTION) {
                try {
                    removePastaMapDir();

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
        limpaCampos();
        SalvarMapa.setEnabled(false);

    }//GEN-LAST:event_novoMapaActionPerformed

    private void downloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadActionPerformed
        try {
            Cursor cursorAguandando = new Cursor(Cursor.WAIT_CURSOR);
            setCursor(cursorAguandando);

            dounloadPastas();
            limpaCampos();
            Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
            setCursor(normalCursor);
            desabilitaBotoes();
        } catch (IOException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_downloadActionPerformed

    private void hemisferioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hemisferioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hemisferioActionPerformed

    private void jB_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_SairActionPerformed
        dispose();
    }//GEN-LAST:event_jB_SairActionPerformed

    private void jM_ListagemPorTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemPorTituloActionPerformed
        try {
            abriRelatorioMapasPorTitulo();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemPorTituloActionPerformed

    private void jM_ConsultaPorGavetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ConsultaPorGavetaActionPerformed
        try {
            abrirRelatorioMapasPorGaveta();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ConsultaPorGavetaActionPerformed

    private void jM_ListagemPorFolhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemPorFolhaActionPerformed
        try {
            abrirRelatorioMapasPorFolha();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemPorFolhaActionPerformed

    private void jM_ListagemMapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_ListagemMapasActionPerformed
        try {
            abrirRelatorioMapas();
        } catch (JRException | SQLException ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_ListagemMapasActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jM_fotografiaAereaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_fotografiaAereaActionPerformed
        CadMapaIndice fta = new CadMapaIndice();
        fta.setVisible(true);

    }//GEN-LAST:event_jM_fotografiaAereaActionPerformed

    private void jM_cadernetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_cadernetasActionPerformed
        try {
            Cadernetas cad = new Cadernetas();
            cad.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_cadernetasActionPerformed

    private void jM_pesquisarfotografiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarfotografiasActionPerformed
        CadFotografiaAerea fotografias = new CadFotografiaAerea();
        fotografias.setVisible(true);
    }//GEN-LAST:event_jM_pesquisarfotografiasActionPerformed

    private void jM_pesquisarCadernetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_pesquisarCadernetaActionPerformed
        try {
            Cadernetas cadernetas = new Cadernetas();
            cadernetas.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(CadMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_pesquisarCadernetaActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

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
    public javax.swing.JButton Deletar;
    private javax.swing.JPanel JPmenu;
    public javax.swing.JButton SalvarMapa;
    private com.toedter.calendar.JYearChooser ano;
    public javax.swing.JButton carregarImagem;
    private javax.swing.JToggleButton download;
    private javax.swing.JTextField editora;
    private javax.swing.JComboBox<String> escala;
    private javax.swing.JTextField folha;
    private javax.swing.JComboBox<String> gaveta;
    private javax.swing.JComboBox<String> hemisferio;
    private javax.swing.JLabel imagemMapa;
    private javax.swing.JButton jB_Sair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jM_ConsultaPorGaveta;
    private javax.swing.JMenuItem jM_ListagemMapas;
    private javax.swing.JMenuItem jM_ListagemPorFolha;
    private javax.swing.JMenuItem jM_ListagemPorTitulo;
    private javax.swing.JMenu jM_cadastros;
    private javax.swing.JMenuItem jM_cadernetas;
    private javax.swing.JMenuItem jM_fotografiaAerea;
    private javax.swing.JMenuItem jM_pesquisarCaderneta;
    private javax.swing.JMenuItem jM_pesquisarfotografias;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jP_Mapas;
    private javax.swing.JPanel jP_campos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel jl_copiArquivo;
    public javax.swing.JButton novoMapa;
    private javax.swing.JTextField pesquisa;
    private javax.swing.JButton pesquisarMapas;
    private javax.swing.JComboBox<String> quantidade;
    private javax.swing.JTable tabela;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JTextField titulo;
    private javax.swing.JLabel totalMapas;
    private javax.swing.JLabel usuario;
    private javax.swing.JButton zoomImagem;
    // End of variables declaration//GEN-END:variables

}
