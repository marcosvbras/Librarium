/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.view;

import br.com.bookriddle.controller.EmprestimoDao;
import br.com.bookriddle.controller.LivroDao;
import br.com.bookriddle.interfaces.ScreenConfig;
import br.com.bookriddle.interfaces.ListagemModel;
import br.com.bookriddle.model.Emprestimo;
import br.com.bookriddle.model.Livro;
import br.com.bookriddle.model.Supervisor;
import br.com.bookriddle.utilities.DateConvert;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class FrmListaEmprestimo extends javax.swing.JFrame implements ScreenConfig, ListagemModel {

    /**
     * Creates new form FrmPrincipal
     */
    private DefaultTableModel modelo;
    private ArrayList<Emprestimo> listEmprestimo;
    private String[] campo = new String[]{"todos", "livro.titulo", "funcionario.nome"};
    private Supervisor supervisor;
    private Date date;

    public FrmListaEmprestimo() {
        initComponents();
    }

    public FrmListaEmprestimo(Supervisor supervisor) {
        frameConfig();
        initComponents();
        this.supervisor = supervisor;
        modelo = (DefaultTableModel) tabela_emprestimo.getModel();
        EmprestimoDao eDao = new EmprestimoDao();
        listEmprestimo = (ArrayList<Emprestimo>) eDao.buscarTodos("where (status_emprestimo != 0) and (status_devolucao != 0) order by data_emprestimo");
        eDao.closeConnection();
        atualizarTabela();
    }

    @Override
    public void frameConfig() {
        FrmListaEmprestimo.this.setUndecorated(true);
        this.setExtendedState(FrmMenu.MAXIMIZED_BOTH);

        try {
            this.setIconImage(ImageIO.read(new File("src/br/com/bookriddle/imagens/liphia_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizarTabela() {
        modelo.setRowCount(0);

        for (Emprestimo e : listEmprestimo) {
            String funcionario = e.getInfo()[0];
            String livro = e.getInfo()[1];
            String dataEmprestimoFormatada = DateConvert.convertToDayMonthYear(e.getDataEmprestimo());
            String dataDevolucaoFormatada = DateConvert.convertToDayMonthYear(e.getDataDevolucao());
            modelo.addRow(new String[]{funcionario, livro, dataEmprestimoFormatada, dataDevolucaoFormatada});
        }
    }

    @Override
    public boolean isSelected() {
        int count = tabela_emprestimo.getSelectedRowCount();

        if (count > 0) {
            if (count == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Selecione apenas um empréstimo");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um empréstimo");
            return false;
        }
    }

    @Override
    public void setList(ArrayList<?> list) {
        this.listEmprestimo = (ArrayList<Emprestimo>) list;
        atualizarTabela();
    }

    @Override
    public void verDetalhes() {
        if (isSelected()) {
            Emprestimo emprestimo = listEmprestimo.get(tabela_emprestimo.getSelectedRow());
            new FrmEmprestimoDireto(supervisor, emprestimo, this).setVisible(true);
        }
    }

    private void buscarEmprestimos() {
        String busca = txt_busca.getText();
        EmprestimoDao eDao = null;
        
        String complemento = "";
        int i = cb_complemento.getSelectedIndex();
        if(i == 0) {
            complemento = " order by data_emprestimo";
        } else {
            complemento = " order by data_emprestimo desc";
        }

        int idx = cb_condicao.getSelectedIndex();
        String clause = null;

        if (!busca.equals("")) {
            if (idx == 0) {
                clause = "where (" + campo[1] + " like '%" + busca + "%' or " + campo[2]
                        + " like '%" + busca + "%') and (status_emprestimo != 0) and (status_devolucao != 0)";
            } else {
                clause = "where (" + campo[idx] + " like '%" + busca + "%') and (status_emprestimo != 0) and (status_devolucao != 0)";
            }
            
            clause += complemento;
            
            eDao = new EmprestimoDao();
            listEmprestimo = (ArrayList<Emprestimo>) eDao.buscarTodos(clause);
            eDao.closeConnection();
        } else {
            eDao = new EmprestimoDao();
            listEmprestimo = (ArrayList<Emprestimo>) eDao.buscarTodos("where (status_emprestimo != 0) and (status_devolucao != 0)" + complemento);
            eDao.closeConnection();
        }

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_emprestimo = new javax.swing.JTable();
        lbl_titulo = new javax.swing.JLabel();
        btn_plus1 = new javax.swing.JPanel();
        btn_voltar = new javax.swing.JPanel();
        lbl_voltar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_excluir = new javax.swing.JPanel();
        lbl_excluir = new javax.swing.JLabel();
        btn_detalhes = new javax.swing.JPanel();
        lbl_detalhes = new javax.swing.JLabel();
        btn_devolver = new javax.swing.JPanel();
        lbl_devolver = new javax.swing.JLabel();
        btn_novo = new javax.swing.JPanel();
        lbl_novo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cb_condicao = new javax.swing.JComboBox();
        txt_busca = new java.awt.TextField();
        cb_complemento = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liphia - Busca de Livros");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(237, 250, 251));

        tabela_emprestimo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tabela_emprestimo.setModel(new javax.swing.table.DefaultTableModel(
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
        tabela_emprestimo.getTableHeader().setReorderingAllowed(false);
        tabela_emprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_emprestimoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela_emprestimo);
        if (tabela_emprestimo.getColumnModel().getColumnCount() > 0) {
            tabela_emprestimo.getColumnModel().getColumn(0).setResizable(false);
            tabela_emprestimo.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabela_emprestimo.getColumnModel().getColumn(1).setResizable(false);
            tabela_emprestimo.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabela_emprestimo.getColumnModel().getColumn(2).setResizable(false);
            tabela_emprestimo.getColumnModel().getColumn(3).setResizable(false);
        }

        lbl_titulo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(51, 51, 51));
        lbl_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titulo.setText("Controle de Empréstimos");

        btn_plus1.setBackground(new java.awt.Color(227, 6, 19));
        btn_plus1.setPreferredSize(new java.awt.Dimension(196, 29));

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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/logo_senai.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(227, 6, 19));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/white_brlogo_small.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Riddle Project 2015");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SENAI Belo Horizonte CETEL César Rodrigues");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(123, 123, 123))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        btn_excluir.setBackground(new java.awt.Color(227, 6, 19));
        btn_excluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_excluirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_excluirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_excluirMouseExited(evt);
            }
        });

        lbl_excluir.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_excluir.setForeground(new java.awt.Color(255, 255, 255));
        lbl_excluir.setText("Excluir");

        javax.swing.GroupLayout btn_excluirLayout = new javax.swing.GroupLayout(btn_excluir);
        btn_excluir.setLayout(btn_excluirLayout);
        btn_excluirLayout.setHorizontalGroup(
            btn_excluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_excluirLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(lbl_excluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_excluirLayout.setVerticalGroup(
            btn_excluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_excluir, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        btn_detalhes.setBackground(new java.awt.Color(227, 6, 19));
        btn_detalhes.setPreferredSize(new java.awt.Dimension(196, 29));
        btn_detalhes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_detalhesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_detalhesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_detalhesMouseExited(evt);
            }
        });

        lbl_detalhes.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_detalhes.setForeground(new java.awt.Color(255, 255, 255));
        lbl_detalhes.setText("Detalhes do Empréstimo");

        javax.swing.GroupLayout btn_detalhesLayout = new javax.swing.GroupLayout(btn_detalhes);
        btn_detalhes.setLayout(btn_detalhesLayout);
        btn_detalhesLayout.setHorizontalGroup(
            btn_detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_detalhesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_detalhes)
                .addGap(73, 73, 73))
        );
        btn_detalhesLayout.setVerticalGroup(
            btn_detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_detalhes, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        btn_devolver.setBackground(new java.awt.Color(227, 6, 19));
        btn_devolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_devolverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_devolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_devolverMouseExited(evt);
            }
        });

        lbl_devolver.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_devolver.setForeground(new java.awt.Color(255, 255, 255));
        lbl_devolver.setText("Devolver Livro");

        javax.swing.GroupLayout btn_devolverLayout = new javax.swing.GroupLayout(btn_devolver);
        btn_devolver.setLayout(btn_devolverLayout);
        btn_devolverLayout.setHorizontalGroup(
            btn_devolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_devolverLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_devolver)
                .addGap(130, 130, 130))
        );
        btn_devolverLayout.setVerticalGroup(
            btn_devolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_devolver, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        btn_novo.setBackground(new java.awt.Color(227, 6, 19));
        btn_novo.setPreferredSize(new java.awt.Dimension(196, 29));
        btn_novo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_novoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_novoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_novoMouseExited(evt);
            }
        });

        lbl_novo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_novo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_novo.setText("Novo Empréstimo");

        javax.swing.GroupLayout btn_novoLayout = new javax.swing.GroupLayout(btn_novo);
        btn_novo.setLayout(btn_novoLayout);
        btn_novoLayout.setHorizontalGroup(
            btn_novoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_novoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_novo)
                .addGap(112, 112, 112))
        );
        btn_novoLayout.setVerticalGroup(
            btn_novoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_novo, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btn_plus1Layout = new javax.swing.GroupLayout(btn_plus1);
        btn_plus1.setLayout(btn_plus1Layout);
        btn_plus1Layout.setHorizontalGroup(
            btn_plus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
            .addComponent(btn_devolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_detalhes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
            .addComponent(btn_excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_voltar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_novo, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        );
        btn_plus1Layout.setVerticalGroup(
            btn_plus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_plus1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(btn_devolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_detalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Pesquisar por");

        cb_condicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_condicao.setForeground(new java.awt.Color(51, 51, 51));
        cb_condicao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Livro", "Funcionário" }));
        cb_condicao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_condicaoItemStateChanged(evt);
            }
        });

        txt_busca.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_busca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_busca.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                txt_buscaTextValueChanged(evt);
            }
        });

        cb_complemento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_complemento.setForeground(new java.awt.Color(51, 51, 51));
        cb_complemento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mais Antigos", "Mais Recentes" }));
        cb_complemento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_complementoItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Ordenar por");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_plus1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_condicao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbl_titulo)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_condicao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_busca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(btn_plus1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
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

    private void btn_detalhesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_detalhesMouseClicked
        verDetalhes();
    }//GEN-LAST:event_btn_detalhesMouseClicked

    private void btn_detalhesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_detalhesMouseEntered
        btn_detalhes.setBackground(new Color(237, 250, 251));
        lbl_detalhes.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_detalhesMouseEntered

    private void btn_detalhesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_detalhesMouseExited
        btn_detalhes.setBackground(new Color(227, 6, 19));
        lbl_detalhes.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_detalhesMouseExited

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

    private void btn_devolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_devolverMouseClicked
        if (isSelected()) {
            Emprestimo emprestimo = listEmprestimo.get(tabela_emprestimo.getSelectedRow());
            LivroDao lDao = new LivroDao();
            Livro livro = (Livro) lDao.buscar("where id = " + emprestimo.getIdLivro());

            int result = JOptionPane.showConfirmDialog(null, "Deseja devolver o livro '" + livro.getTitulo() + "'?");

            if (result == 0) {
                livro.setQuantidade(livro.getQuantidade() + 1);
                lDao.setLivro(livro);
                lDao.editar();
                lDao.closeConnection();

                emprestimo.setStatusDevolucao(0);
                EmprestimoDao eDao = new EmprestimoDao(emprestimo);
                boolean resultado = eDao.editar();
                if (resultado) {
                    JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso.");
                }
                listEmprestimo = (ArrayList<Emprestimo>) eDao.buscarTodos("where (status_emprestimo != 0) and (status_devolucao != 0) order by data_emprestimo");
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_btn_devolverMouseClicked

    private void btn_devolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_devolverMouseEntered
        btn_devolver.setBackground(new Color(237, 250, 251));
        lbl_devolver.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_devolverMouseEntered

    private void btn_devolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_devolverMouseExited
        btn_devolver.setBackground(new Color(227, 6, 19));
        lbl_devolver.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_devolverMouseExited

    private void btn_excluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseClicked
        if (isSelected()) {
            int result = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este empréstimo?");

            if (result == 0) {
                int row = tabela_emprestimo.getSelectedRow();
                Emprestimo emprestimo = listEmprestimo.get(row);
                EmprestimoDao eDao = new EmprestimoDao(emprestimo);
                eDao.excluir();
                listEmprestimo = (ArrayList<Emprestimo>) eDao.buscarTodos("where (status_emprestimo != 0) and (status_devolucao != 0) order by data_emprestimo");
                eDao.closeConnection();

                // Retornando o livro para o "estoque"
                LivroDao lDao = new LivroDao();
                Livro livro = (Livro) lDao.buscar("where id = " + emprestimo.getIdLivro());
                livro.setQuantidade(livro.getQuantidade() + 1);
                lDao.setLivro(livro);
                lDao.editar();
                lDao.closeConnection();
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_btn_excluirMouseClicked

    private void btn_excluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseEntered
        btn_excluir.setBackground(new Color(237, 250, 251));
        lbl_excluir.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_excluirMouseEntered

    private void btn_excluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseExited
        btn_excluir.setBackground(new Color(227, 6, 19));
        lbl_excluir.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_excluirMouseExited

    private void btn_novoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_novoMouseClicked
        new FrmListaLivro(supervisor).setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_novoMouseClicked

    private void btn_novoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_novoMouseEntered
        btn_novo.setBackground(new Color(237, 250, 251));
        lbl_novo.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_novoMouseEntered

    private void btn_novoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_novoMouseExited
        btn_novo.setBackground(new Color(227, 6, 19));
        lbl_novo.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_novoMouseExited

    private void tabela_emprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_emprestimoMouseClicked
        if (evt.getClickCount() == 2) {
            verDetalhes();
        }
    }//GEN-LAST:event_tabela_emprestimoMouseClicked

    private void cb_condicaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_condicaoItemStateChanged
        buscarEmprestimos();
    }//GEN-LAST:event_cb_condicaoItemStateChanged

    private void txt_buscaTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_txt_buscaTextValueChanged
        buscarEmprestimos();
    }//GEN-LAST:event_txt_buscaTextValueChanged

    private void cb_complementoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_complementoItemStateChanged
        buscarEmprestimos();
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
            java.util.logging.Logger.getLogger(FrmListaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmListaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmListaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmListaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new FrmListaEmprestimo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_detalhes;
    private javax.swing.JPanel btn_devolver;
    private javax.swing.JPanel btn_excluir;
    private javax.swing.JPanel btn_novo;
    private javax.swing.JPanel btn_plus1;
    private javax.swing.JPanel btn_voltar;
    private javax.swing.JComboBox cb_complemento;
    private javax.swing.JComboBox cb_condicao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_detalhes;
    private javax.swing.JLabel lbl_devolver;
    private javax.swing.JLabel lbl_excluir;
    private javax.swing.JLabel lbl_novo;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_voltar;
    private javax.swing.JTable tabela_emprestimo;
    private java.awt.TextField txt_busca;
    // End of variables declaration//GEN-END:variables
}
