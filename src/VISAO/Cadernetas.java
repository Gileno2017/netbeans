/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import MODELO.CadernetaTableModel;
import CONTROLE.CadernetaDao;
import MODELO.Caderneta;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gileno.macedo
 */
public class Cadernetas extends javax.swing.JFrame implements Serializable{

 
     
    CadernetaTableModel modeloTabela = new CadernetaTableModel();
    Long id;
    Caderneta cad;
    CadernetaTableModel tbm;

    public Cadernetas() {

        initComponents();
        // desabilitaCampos();
        tamanhoColunas();
        totalCadernetas();

    }

    public void tamanhoColunas() {
        tabela.getColumnModel().getColumn(0).setPreferredWidth(190);
        tabela.getColumnModel().getColumn(0).setResizable(true);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(190);
        tabela.getColumnModel().getColumn(1).setResizable(true);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(105);
        tabela.getColumnModel().getColumn(2).setResizable(true);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabela.getColumnModel().getColumn(3).setResizable(true);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(65);
        tabela.getColumnModel().getColumn(4).setResizable(true);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(62);
        tabela.getColumnModel().getColumn(5).setResizable(true);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(85);
        tabela.getColumnModel().getColumn(6).setResizable(true);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

    }

    private void limpaCampos() {
        projeto.setText("");
        geologo.setText("");
        centro_custo.setText("");
        tipo.setSelectedItem(null);
        prateleira.setValue(0);
        quantidade.setValue(0);

    }

    private void salvarCadernetas() throws Exception {
        Caderneta cc = new Caderneta();
        CadernetaDao ccDao = new CadernetaDao();
        cc.setProjeto(projeto.getText().toUpperCase());
        cc.setGeologo(geologo.getText().toUpperCase());
        cc.setPrateleira(Integer.parseInt(prateleira.getValue().toString().toUpperCase()));
        cc.setQtd(Integer.parseInt(quantidade.getValue().toString().toUpperCase()));
        cc.setAno(String.valueOf(ano.getValue()).toUpperCase());
        cc.setCentroCusto(centro_custo.getText().toUpperCase());
        cc.setTipo((String) tipo.getSelectedItem());    
        modeloTabela.AdcionarCaderneta(cc);
       JOptionPane.showMessageDialog(null, "Dados Cadastrado com Sucesso !!");
       totalCadernetas();        
    }

    public void alterarDados() throws Exception {
        Caderneta cc = new Caderneta();         
        cc.setCod_Caderneta(id);
        cc.setProjeto(projeto.getText().toUpperCase());
        cc.setGeologo(geologo.getText().toUpperCase());
        cc.setPrateleira(Integer.parseInt(prateleira.getValue().toString()));
        cc.setQtd(Integer.parseInt(quantidade.getValue().toString().toUpperCase()));
        cc.setAno(String.valueOf(ano.getValue()).toUpperCase());
        cc.setCentroCusto(centro_custo.getText().toUpperCase());
        cc.setTipo((String) tipo.getSelectedItem());   
        limpaCampos();
        modeloTabela.RemoveCaderneta(cad);
        modeloTabela.AdcionarCaderneta(cc);
        tamanhoColunas();
        JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso !!");      
    }

    public void removeCaderneta() throws Exception {
        CadernetaDao dao = new CadernetaDao();
        dao.removeCaderneta(id);
        modeloTabela.RemoveCaderneta(cad);
    }

    public void totalCadernetas() {
        CadernetaDao dao = new CadernetaDao();
        dao.getMapasCount();
        int total = dao.getMapasCount();
        jL_totalCadenetas.setText("" + total);

    }

    public void linhaSelecionada() {
        cad = modeloTabela.getLinhaCaderneta(tabela.getSelectedRow());
        id = cad.getCod_Caderneta();
        projeto.setText(cad.getProjeto());
        geologo.setText(cad.getGeologo());
        prateleira.setValue(cad.getPrateleira());
        quantidade.setValue(cad.getQtd());
        ano.setValue(Integer.parseInt(cad.getAno()));
        centro_custo.setText(cad.getCentroCusto());
        tipo.setSelectedItem(String.valueOf(cad.getTipo()));
        Pesquisar.setText("");
        Salvar.setVisible(false);
        Alterar.setVisible(true);
        totalCadernetas();
     
    }

    public void PesquisaCadernetaPorId() {
        CadernetaDao dao = new CadernetaDao();
        Caderneta caderneta = dao.consultaCadernetas(Long.valueOf(Pesquisar.getText()));
        projeto.setText(caderneta.getProjeto());
        geologo.setText(caderneta.getGeologo());
        centro_custo.setText(caderneta.getCentroCusto());
        tipo.setSelectedItem(caderneta.getTipo());
        prateleira.setValue(caderneta.getPrateleira());
        quantidade.setValue(caderneta.getQtd());
        ano.setValue(Integer.parseInt(caderneta.getAno()));
        Pesquisar.setText("");
        totalCadernetas();
        tamanhoColunas();

    }

    public void ConsultaGeologoporNome() {
        modeloTabela.consultaCadernetaPorNome(Pesquisar.getText().toUpperCase());
        tamanhoColunas();
    }

    public void desabilitaCampos() {
        projeto.setVisible(false);
        geologo.setVisible(false);
        prateleira.setVisible(false);
        centro_custo.setVisible(false);
        tipo.setVisible(false);
        quantidade.setVisible(false);
        ano.setVisible(false);

    }
    
    public boolean verificaCampos(){
            if(!projeto.getText().equals("") &&!geologo.getText().equals("")
                    &&!prateleira.getValue().equals("")&&!centro_custo.getText().equals("")
                    &&!tipo.getSelectedItem().equals("")&&!quantidade.getValue().equals("")){
                   return true;
                 }
                  JOptionPane.showMessageDialog(null, "Preencha Todos os Campos");
                  return false;
         }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        lprojeto = new javax.swing.JLabel();
        projeto = new javax.swing.JTextField();
        lnome = new javax.swing.JLabel();
        geologo = new javax.swing.JTextField();
        c_custo = new javax.swing.JLabel();
        centro_custo = new javax.swing.JTextField();
        jLTipo = new javax.swing.JLabel();
        lquantidade = new javax.swing.JLabel();
        quantidade = new javax.swing.JSpinner();
        jLAno = new javax.swing.JLabel();
        ano = new com.toedter.calendar.JYearChooser();
        jLProjeto5 = new javax.swing.JLabel();
        prateleira = new javax.swing.JSpinner();
        tipo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jB_Pesquisar = new javax.swing.JButton();
        Pesquisar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jL_totalCadenetas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        Salvar = new javax.swing.JButton();
        Deletar = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadernetas de Campo");
        setLocation(new java.awt.Point(420, 150));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Cadastro de Caderneta de Campo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(102, 0, 102))); // NOI18N

        lprojeto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lprojeto.setText("Projeto:");

        projeto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        projeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projetoActionPerformed(evt);
            }
        });

        lnome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lnome.setText("Geólogo:");

        geologo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        geologo.setToolTipText("Inserir Somente Letras ");
        geologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geologoActionPerformed(evt);
            }
        });

        c_custo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c_custo.setText("Centro de Custo");

        centro_custo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLTipo.setText("Tipo:");

        lquantidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lquantidade.setText("Qtd:");

        quantidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quantidade.setModel(new javax.swing.SpinnerNumberModel(0, 0, 15, 1));

        jLAno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLAno.setText("Ano:");

        ano.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLProjeto5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLProjeto5.setText("Prateleira:");

        prateleira.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        prateleira.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        tipo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "GEOLOGIA", "GEOQUIMICA" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLTipo)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lprojeto)
                        .addComponent(lnome, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(lquantidade))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projeto)
                    .addComponent(geologo)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jLAno)
                        .addGap(18, 18, 18)
                        .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLProjeto5)
                        .addGap(18, 18, 18)
                        .addComponent(prateleira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(c_custo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(centro_custo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(projeto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lprojeto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnome)
                    .addComponent(geologo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTipo)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_custo)
                    .addComponent(centro_custo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lquantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLAno))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLProjeto5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(prateleira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ano, centro_custo, geologo, prateleira, projeto, quantidade, tipo});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {c_custo, jLAno, jLProjeto5, jLTipo, lnome, lprojeto});

        geologo.getAccessibleContext().setAccessibleName("");

        jPanel2.setBackground(new java.awt.Color(130, 60, 122));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jB_Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisa.jpg"))); // NOI18N
        jB_Pesquisar.setToolTipText("Pesquisar Por Geólogo");
        jB_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_PesquisarActionPerformed(evt);
            }
        });

        Pesquisar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Pesquisar.setToolTipText("Informe o Nome do Geologo");
        Pesquisar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisarActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total de Cadernetas Cadastradas:");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jL_totalCadenetas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jL_totalCadenetas.setForeground(new java.awt.Color(255, 255, 255));
        jL_totalCadenetas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jB_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jL_totalCadenetas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_totalCadenetas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jB_Pesquisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Pesquisar, jL_totalCadenetas, jLabel2});

        jLabel1.setBackground(new java.awt.Color(130, 60, 122));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/caderneta.PNG"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        tabela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        tabela.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabela.setModel(modeloTabela);
        tabela.setAlignmentX(0.9F);
        tabela.setAlignmentY(0.9F);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        Salvar.setBackground(new java.awt.Color(255, 255, 255));
        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.jpg"))); // NOI18N
        Salvar.setToolTipText("Salvar Dados");
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        Deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/apaga.jpg"))); // NOI18N
        Deletar.setToolTipText("Deletar Dados");
        Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletarActionPerformed(evt);
            }
        });

        Alterar.setBackground(new java.awt.Color(255, 255, 255));
        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone editar2_1.jpg"))); // NOI18N
        Alterar.setToolTipText("Alterar Dados");
        Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\gileno.macedo\\Documents\\NetBeansProjects\\ArqMap\\src\\imagens\\novo2.jpg")); // NOI18N
        jButton2.setToolTipText("Novo Cadastro");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.jpg"))); // NOI18N
        jButton3.setToolTipText("Sair do Cadastro de Cadernetas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Alterar, Deletar, Salvar, jButton2, jButton3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Alterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Deletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton3});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PesquisarActionPerformed

    private void projetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projetoActionPerformed

    private void jB_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_PesquisarActionPerformed
         ConsultaGeologoporNome();
         tamanhoColunas();
         Alterar.setVisible(true);
         Salvar.setVisible(false);
        
    }//GEN-LAST:event_jB_PesquisarActionPerformed

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
       if(verificaCampos()){
           try {
               salvarCadernetas();
           } catch (Exception ex) {
               Logger.getLogger(Cadernetas.class.getName()).log(Level.SEVERE, null, ex);
           }
            limpaCampos();
       }
    }//GEN-LAST:event_SalvarActionPerformed

    private void geologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geologoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_geologoActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        linhaSelecionada();
    }//GEN-LAST:event_tabelaMouseClicked

    private void DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletarActionPerformed
        if (id == null) {
            JOptionPane.showMessageDialog(null, "Selecione  os Dados Que Deseja Excluir, Clicando na Linha da Tabela");
        } else {
            int resp = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir Estes Dados ?   " + JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_NO_OPTION) {
                try {
                    removeCaderneta();
                    limpaCampos();
                } catch (Exception ex) {
                    Logger.getLogger(Cadernetas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }//GEN-LAST:event_DeletarActionPerformed

    private void AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterarActionPerformed
        if (id == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma Caderneta na Tabela Abaixo para Alterar os Dados");
        } else {
            try {
                alterarDados();
                limpaCampos();
            } catch (Exception ex) {
                Logger.getLogger(Cadernetas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_AlterarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpaCampos();
        Alterar.setVisible(false);
        Salvar.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setVisible(false);


    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Cadernetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadernetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadernetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadernetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadernetas().setVisible(true);
                //adernetaTableModel tm = new CadernetaTableModel();

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Deletar;
    private javax.swing.JTextField Pesquisar;
    private javax.swing.JButton Salvar;
    private com.toedter.calendar.JYearChooser ano;
    private javax.swing.JLabel c_custo;
    private javax.swing.JTextField centro_custo;
    private javax.swing.JTextField geologo;
    private javax.swing.JButton jB_Pesquisar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLAno;
    private javax.swing.JLabel jLProjeto5;
    private javax.swing.JLabel jLTipo;
    private javax.swing.JLabel jL_totalCadenetas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lnome;
    private javax.swing.JLabel lprojeto;
    private javax.swing.JLabel lquantidade;
    private javax.swing.JSpinner prateleira;
    private javax.swing.JTextField projeto;
    private javax.swing.JSpinner quantidade;
    private javax.swing.JTable tabela;
    private javax.swing.JComboBox<String> tipo;
    // End of variables declaration//GEN-END:variables
}
