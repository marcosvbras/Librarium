/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.view;

import br.com.bookriddle.controller.EmprestimoDao;
import br.com.bookriddle.interfaces.ListagemModel;
import br.com.bookriddle.interfaces.ScreenConfig;
import br.com.bookriddle.model.Emprestimo;
import br.com.bookriddle.utilities.DateConvert;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class FrmRelatorios extends javax.swing.JFrame implements ScreenConfig, ListagemModel {

    /**
     * Creates new form FrmPrincipal
     */
    
    private ArrayList<Emprestimo> listEmprestimo;
    private DefaultTableModel model;
    private Date data_de, data_ate;
    
    public FrmRelatorios() {
        frameConfig();
        initComponents();
        model = (DefaultTableModel)tabela_emprestimo1.getModel();
        EmprestimoDao eDao = new EmprestimoDao();
        listEmprestimo = (ArrayList<Emprestimo>)eDao.buscarTodos("where status_emprestimo != 0 order by data_emprestimo");
        eDao.closeConnection();
        atualizarTabela();
        
        calendar_de.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                data_de = calendar_de.getDate();
                buscarInformacoes();
            }
        });
        
        calendar_ate.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                data_ate = calendar_ate.getDate();
                buscarInformacoes();
            }
        });
    }
    
    @Override
    public void frameConfig() {
        FrmRelatorios.this.setUndecorated(true);
        this.setExtendedState(FrmRelatorios.MAXIMIZED_BOTH);
        
        try {
            this.setIconImage(ImageIO.read(new File("src/br/com/bookriddle/imagens/liphia_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void atualizarTabela() {
        model.setRowCount(0);
        
        for (Emprestimo e : listEmprestimo) {
            String funcionario = e.getInfo()[0];
            String livro = e.getInfo()[1];
            String dataEmprestimoFormatada = DateConvert.convertToDayMonthYear(e.getDataEmprestimo());
            String dataDevolucaoFormatada = DateConvert.convertToDayMonthYear(e.getDataDevolucao());
            model.addRow(new String[]{funcionario, livro, dataEmprestimoFormatada, dataDevolucaoFormatada});
        }
    }

    @Override
    public void verDetalhes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSelected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setList(ArrayList<?> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void buscarInformacoes() {
        int idx = cb_condicao.getSelectedIndex();
        String complemento1 = "";
        String complemento2 = "";
        
        if(data_de != null) {
            if(data_ate != null) {
                complemento1 = " and (data_emprestimo between '" + DateConvert.convertToYearMonthDay(data_de) 
                        + "' and '" + DateConvert.convertToYearMonthDay(data_ate) + "')";
            } else {
                complemento1 = " and (data_emprestimo >= '" + DateConvert.convertToYearMonthDay(data_de) + "')";
            }
        }

        int i = cb_complemento.getSelectedIndex();
        if(i == 0) {
            complemento2 = " order by data_emprestimo";
        } else {
            complemento2 = " order by data_emprestimo desc";
        }
        
        String clause = null;
        
        switch(idx) {
            case 0: clause = "where status_emprestimo != 0" + complemento1 + complemento2;
                break;
            case 1: clause = "where (status_emprestimo != 0) and (status_devolucao != 0)" + complemento1 + complemento2;
                break;
        }
        
        EmprestimoDao eDao = new EmprestimoDao();
        listEmprestimo = (ArrayList<Emprestimo>)eDao.buscarTodos(clause);
        eDao.closeConnection();
        atualizarTabela();
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_plus3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_voltar = new javax.swing.JPanel();
        lbl_voltar = new javax.swing.JLabel();
        btn_resumo = new javax.swing.JPanel();
        lbl_resumo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cb_condicao = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_emprestimo1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        calendar_de = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        calendar_ate = new com.toedter.calendar.JDateChooser();
        cb_complemento = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liphia - Busca de Livros");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(237, 250, 251));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Relatórios");

        btn_plus3.setBackground(new java.awt.Color(227, 6, 19));
        btn_plus3.setPreferredSize(new java.awt.Dimension(196, 29));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/logo_senai.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(227, 6, 19));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/white_brlogo_small.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Riddle Project 2015");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SENAI Belo Horizonte CETEL César Rodrigues");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(123, 123, 123))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        btn_voltar.setBackground(new java.awt.Color(227, 6, 19));
        btn_voltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_voltarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_voltarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_voltarMouseExited(evt);
            }
        });

        lbl_voltar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_voltar.setForeground(new java.awt.Color(255, 255, 255));
        lbl_voltar.setText("Voltar");

        javax.swing.GroupLayout btn_voltarLayout = new javax.swing.GroupLayout(btn_voltar);
        btn_voltar.setLayout(btn_voltarLayout);
        btn_voltarLayout.setHorizontalGroup(
            btn_voltarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_voltarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_voltar)
                .addGap(173, 173, 173))
        );
        btn_voltarLayout.setVerticalGroup(
            btn_voltarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_voltar, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        btn_resumo.setBackground(new java.awt.Color(227, 6, 19));
        btn_resumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_resumoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_resumoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_resumoMouseExited(evt);
            }
        });

        lbl_resumo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_resumo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_resumo.setText("Resumo de Atividades");

        javax.swing.GroupLayout btn_resumoLayout = new javax.swing.GroupLayout(btn_resumo);
        btn_resumo.setLayout(btn_resumoLayout);
        btn_resumoLayout.setHorizontalGroup(
            btn_resumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_resumoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_resumo)
                .addGap(83, 83, 83))
        );
        btn_resumoLayout.setVerticalGroup(
            btn_resumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_resumo, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btn_plus3Layout = new javax.swing.GroupLayout(btn_plus3);
        btn_plus3.setLayout(btn_plus3Layout);
        btn_plus3Layout.setHorizontalGroup(
            btn_plus3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
            .addComponent(btn_voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_resumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        btn_plus3Layout.setVerticalGroup(
            btn_plus3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_plus3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(69, 69, 69)
                .addComponent(btn_resumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Listar por");

        cb_condicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_condicao.setForeground(new java.awt.Color(51, 51, 51));
        cb_condicao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos os empréstimos", "Devoluções pendentes" }));
        cb_condicao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_condicaoItemStateChanged(evt);
            }
        });

        tabela_emprestimo1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tabela_emprestimo1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Funcionário", "Livro", "Data de Empréstimo", "Devolução Estimada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_emprestimo1.getTableHeader().setReorderingAllowed(false);
        tabela_emprestimo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_emprestimo1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela_emprestimo1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("À partir de");

        calendar_de.setBackground(new java.awt.Color(237, 250, 251));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Até");

        calendar_ate.setBackground(new java.awt.Color(237, 250, 251));

        cb_complemento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_complemento.setForeground(new java.awt.Color(51, 51, 51));
        cb_complemento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mais Antigos", "Mais Recentes" }));
        cb_complemento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_complementoItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Ordenar por");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(btn_plus3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_condicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(calendar_de, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(calendar_ate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_plus3, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_condicao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(calendar_de, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(calendar_ate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_voltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_voltarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btn_voltarMouseClicked

    private void btn_voltarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_voltarMouseEntered
        btn_voltar.setBackground(new Color(237, 250, 251));
        lbl_voltar.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_voltarMouseEntered

    private void btn_voltarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_voltarMouseExited
        btn_voltar.setBackground(new Color(227, 6, 19));
        lbl_voltar.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_voltarMouseExited

    private void cb_condicaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_condicaoItemStateChanged
        buscarInformacoes();
    }//GEN-LAST:event_cb_condicaoItemStateChanged

    private void tabela_emprestimo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_emprestimo1MouseClicked
        if(evt.getClickCount() == 2) {
            verDetalhes();
        }
    }//GEN-LAST:event_tabela_emprestimo1MouseClicked

    private void btn_resumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resumoMouseClicked
        new FrmResumo().setVisible(true);
    }//GEN-LAST:event_btn_resumoMouseClicked

    private void btn_resumoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resumoMouseEntered
        btn_resumo.setBackground(new Color(237, 250, 251));
        lbl_resumo.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_resumoMouseEntered

    private void btn_resumoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resumoMouseExited
        btn_resumo.setBackground(new Color(227, 6, 19));
        lbl_resumo.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_resumoMouseExited

    private void cb_complementoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_complementoItemStateChanged
        buscarInformacoes();
    }//GEN-LAST:event_cb_complementoItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmRelatorios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_plus3;
    private javax.swing.JPanel btn_resumo;
    private javax.swing.JPanel btn_voltar;
    private com.toedter.calendar.JDateChooser calendar_ate;
    private com.toedter.calendar.JDateChooser calendar_de;
    private javax.swing.JComboBox cb_complemento;
    private javax.swing.JComboBox cb_condicao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_resumo;
    private javax.swing.JLabel lbl_voltar;
    private javax.swing.JTable tabela_emprestimo1;
    // End of variables declaration//GEN-END:variables
}
