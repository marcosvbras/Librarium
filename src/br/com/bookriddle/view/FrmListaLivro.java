/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.view;

import br.com.bookriddle.controller.LivroDao;
import br.com.bookriddle.interfaces.ScreenConfig;
import br.com.bookriddle.interfaces.ListagemModel;
import br.com.bookriddle.model.Livro;
import br.com.bookriddle.model.Supervisor;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class FrmListaLivro extends javax.swing.JFrame implements ScreenConfig, ListagemModel {

    /**
     * Creates new form FrmPrincipal
     */
    private DefaultTableModel modelo;
    private Livro livro;
    private ArrayList<Livro> listLivro;
    private String[] campo = {"todos", "titulo", "autor", "editora", "area"};
    private String complemento = "";
    public static final String DISPONIVEL = "Disponível";
    public static final String INDISPONIVEL = "Indisponível";
    private Supervisor supervisor;

    public FrmListaLivro() {
        frameConfig();
        initComponents();
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public FrmListaLivro(Supervisor supervisor) {
        frameConfig();
        initComponents();
        this.supervisor = supervisor;
        modelo = (DefaultTableModel) tabela_livro.getModel();

        if (this.supervisor == null) {
            btn_novo.setVisible(false);
            btn_emprestar.setVisible(false);
        } else {
            btn_reservar.setVisible(false);
        }

        LivroDao dao = new LivroDao();
        listLivro = (ArrayList<Livro>) dao.buscarTodos("where status_livro != 0 order by titulo");
        dao.closeConnection();
        atualizarTabela();
    }

    @Override
    public void atualizarTabela() {
        modelo.setRowCount(0);
        if (listLivro != null) {
            for (Livro l : listLivro) {
                String disponibilidade = null;

                if (l.getQuantidade() > 0) {
                    disponibilidade = DISPONIVEL;
                } else {
                    disponibilidade = INDISPONIVEL;
                }
                modelo.addRow(new String[]{l.getTitulo(), l.getAutor(), l.getArea(), l.getEditora(), disponibilidade});
            }
        }
    }

    @Override
    public boolean isSelected() {
        int count = tabela_livro.getSelectedRowCount();

        if (count > 0) {
            if (count == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Selecione apenas um livro");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um livro");
            return false;
        }
    }

    @Override
    public void frameConfig() {
        FrmListaLivro.this.setUndecorated(true);
        this.setExtendedState(FrmMenu.MAXIMIZED_BOTH);

        try {
            this.setIconImage(ImageIO.read(new File("src/br/com/bookriddle/imagens/liphia_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setList(ArrayList<?> list) {
        this.listLivro = (ArrayList<Livro>) list;
        atualizarTabela();
    }
    
    private void buscarLivros() {
        String busca = txt_busca.getText();
        LivroDao dao = null;
        int idx = cb_condicao.getSelectedIndex();
        String clause = null;

        if (!busca.equals("")) {
            if (idx == 0) {
                clause = "where (" + campo[1] + " like '%" + busca + "%' or " + campo[2]
                    + " like '%" + busca + "%' or " + campo[3] + " like '%" + busca + "%' or "
                    + campo[4] + " like '%" + busca + "%') and (status_livro != 0)" + complemento + " order by titulo";
            } else {
                clause = "where (" + campo[idx] + " like '%" + busca + "%') and (status_livro != 0)" + complemento + " order by titulo";
            }

            dao = new LivroDao();
            listLivro = (ArrayList<Livro>) dao.buscarTodos(clause);
            dao.closeConnection();
        } else {
            dao = new LivroDao();
            listLivro = (ArrayList<Livro>) dao.buscarTodos("where status_livro != 0 " + complemento + " order by titulo");
        }
        
        atualizarTabela();
    }
    
    @Override
    public void verDetalhes() {
        if (isSelected()) {
            int row = tabela_livro.getSelectedRow();
            Livro livro = listLivro.get(row);
            if (supervisor == null) {
                new FrmVerLivro(livro, this).setVisible(true);
            } else {
                new FrmEditarLivro(livro, supervisor, this).setVisible(true);
            }
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cb_condicao = new javax.swing.JComboBox();
        txt_busca = new java.awt.TextField();
        jLabel11 = new javax.swing.JLabel();
        cb_disponibilidade = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_livro = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btn_plus3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_detalhes = new javax.swing.JPanel();
        lbl_detalhes = new javax.swing.JLabel();
        btn_reservar = new javax.swing.JPanel();
        lbl_reservar = new javax.swing.JLabel();
        btn_novo = new javax.swing.JPanel();
        lbl_novo = new javax.swing.JLabel();
        btn_emprestar = new javax.swing.JPanel();
        lbl_emprestar = new javax.swing.JLabel();
        btn_voltar = new javax.swing.JPanel();
        lbl_voltar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liphia - Busca de Livros");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(237, 250, 251));

        jPanel2.setBackground(new java.awt.Color(237, 250, 251));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Pesquisar por");

        cb_condicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_condicao.setForeground(new java.awt.Color(51, 51, 51));
        cb_condicao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Título", "Autor", "Editora", "Área" }));
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

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Disponibilidade");

        cb_disponibilidade.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_disponibilidade.setForeground(new java.awt.Color(51, 51, 51));
        cb_disponibilidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Disponível", "Indisponível" }));
        cb_disponibilidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_disponibilidadeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_condicao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_disponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_condicao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_disponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txt_busca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabela_livro.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tabela_livro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título", "Autor", "Área", "Editora", "Disponibilidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_livro.setRowHeight(20);
        tabela_livro.getTableHeader().setReorderingAllowed(false);
        tabela_livro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_livroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela_livro);
        if (tabela_livro.getColumnModel().getColumnCount() > 0) {
            tabela_livro.getColumnModel().getColumn(0).setResizable(false);
            tabela_livro.getColumnModel().getColumn(0).setPreferredWidth(350);
            tabela_livro.getColumnModel().getColumn(1).setResizable(false);
            tabela_livro.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabela_livro.getColumnModel().getColumn(2).setResizable(false);
            tabela_livro.getColumnModel().getColumn(3).setResizable(false);
            tabela_livro.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabela_livro.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Controle de Livros");

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

        btn_detalhes.setBackground(new java.awt.Color(227, 6, 19));
        btn_detalhes.setMaximumSize(new java.awt.Dimension(196, 29));
        btn_detalhes.setMinimumSize(new java.awt.Dimension(196, 29));
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
        lbl_detalhes.setText("Detalhes");

        javax.swing.GroupLayout btn_detalhesLayout = new javax.swing.GroupLayout(btn_detalhes);
        btn_detalhes.setLayout(btn_detalhesLayout);
        btn_detalhesLayout.setHorizontalGroup(
            btn_detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_detalhesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_detalhes)
                .addGap(153, 153, 153))
        );
        btn_detalhesLayout.setVerticalGroup(
            btn_detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_detalhes, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        btn_reservar.setBackground(new java.awt.Color(227, 6, 19));
        btn_reservar.setMaximumSize(new java.awt.Dimension(200, 54));
        btn_reservar.setMinimumSize(new java.awt.Dimension(200, 54));
        btn_reservar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_reservarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reservarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reservarMouseExited(evt);
            }
        });

        lbl_reservar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_reservar.setForeground(new java.awt.Color(255, 255, 255));
        lbl_reservar.setText("Reservar");

        javax.swing.GroupLayout btn_reservarLayout = new javax.swing.GroupLayout(btn_reservar);
        btn_reservar.setLayout(btn_reservarLayout);
        btn_reservarLayout.setHorizontalGroup(
            btn_reservarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_reservarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_reservar)
                .addGap(152, 152, 152))
        );
        btn_reservarLayout.setVerticalGroup(
            btn_reservarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_reservar, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        btn_novo.setBackground(new java.awt.Color(227, 6, 19));
        btn_novo.setMaximumSize(new java.awt.Dimension(208, 54));
        btn_novo.setMinimumSize(new java.awt.Dimension(208, 54));
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
        lbl_novo.setText("Novo Livro");

        javax.swing.GroupLayout btn_novoLayout = new javax.swing.GroupLayout(btn_novo);
        btn_novo.setLayout(btn_novoLayout);
        btn_novoLayout.setHorizontalGroup(
            btn_novoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_novoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_novo)
                .addGap(146, 146, 146))
        );
        btn_novoLayout.setVerticalGroup(
            btn_novoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_novo, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        btn_emprestar.setBackground(new java.awt.Color(227, 6, 19));
        btn_emprestar.setMaximumSize(new java.awt.Dimension(200, 54));
        btn_emprestar.setMinimumSize(new java.awt.Dimension(200, 54));
        btn_emprestar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_emprestarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_emprestarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_emprestarMouseExited(evt);
            }
        });

        lbl_emprestar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_emprestar.setForeground(new java.awt.Color(255, 255, 255));
        lbl_emprestar.setText("Realizar Empréstimo");

        javax.swing.GroupLayout btn_emprestarLayout = new javax.swing.GroupLayout(btn_emprestar);
        btn_emprestar.setLayout(btn_emprestarLayout);
        btn_emprestarLayout.setHorizontalGroup(
            btn_emprestarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_emprestarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_emprestar)
                .addGap(88, 88, 88))
        );
        btn_emprestarLayout.setVerticalGroup(
            btn_emprestarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_emprestar, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
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

        javax.swing.GroupLayout btn_plus3Layout = new javax.swing.GroupLayout(btn_plus3);
        btn_plus3.setLayout(btn_plus3Layout);
        btn_plus3Layout.setHorizontalGroup(
            btn_plus3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
            .addComponent(btn_detalhes, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
            .addComponent(btn_reservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_novo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_emprestar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        btn_plus3Layout.setVerticalGroup(
            btn_plus3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_plus3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(77, 77, 77)
                .addComponent(btn_reservar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_detalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_emprestar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_plus3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(btn_plus3, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
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

    private void txt_buscaTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_txt_buscaTextValueChanged
        buscarLivros();
    }//GEN-LAST:event_txt_buscaTextValueChanged

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

    private void btn_novoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_novoMouseClicked
        new FrmCadastroLivro(this).setVisible(true);
    }//GEN-LAST:event_btn_novoMouseClicked

    private void btn_novoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_novoMouseEntered
        btn_novo.setBackground(new Color(237, 250, 251));
        lbl_novo.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_novoMouseEntered

    private void btn_novoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_novoMouseExited
        btn_novo.setBackground(new Color(227, 6, 19));
        lbl_novo.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_novoMouseExited

    private void btn_reservarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reservarMouseClicked
        if (isSelected()) {
            int row = tabela_livro.getSelectedRow();
            Livro livro = listLivro.get(row);
            new FrmReserva(livro, this).setVisible(true);
        }
    }//GEN-LAST:event_btn_reservarMouseClicked

    private void btn_reservarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reservarMouseEntered
        btn_reservar.setBackground(new Color(237, 250, 251));
        lbl_reservar.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_reservarMouseEntered

    private void btn_reservarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reservarMouseExited
        btn_reservar.setBackground(new Color(227, 6, 19));
        lbl_reservar.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_reservarMouseExited

    private void cb_condicaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_condicaoItemStateChanged
        buscarLivros();
    }//GEN-LAST:event_cb_condicaoItemStateChanged

    private void cb_disponibilidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_disponibilidadeItemStateChanged
        int idx = cb_disponibilidade.getSelectedIndex();
        
        switch(idx) {
            case 0: complemento = "";
                break;
            case 1: complemento = " and (quantidade > 0 )";
                break;
            case 2: complemento = " and (quantidade = 0) ";
        }
        
        buscarLivros();
    }//GEN-LAST:event_cb_disponibilidadeItemStateChanged

    private void btn_emprestarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_emprestarMouseClicked
        if(isSelected()) {
            Livro livro = listLivro.get(tabela_livro.getSelectedRow());
            
            if(livro.getQuantidade() != 0) {
                new FrmEmprestimoDireto(livro, supervisor).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Não há exemplares deste livro disponíveis para empréstimo.");
            }
        }
    }//GEN-LAST:event_btn_emprestarMouseClicked

    private void btn_emprestarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_emprestarMouseEntered
        btn_emprestar.setBackground(new Color(237, 250, 251));
        lbl_emprestar.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_emprestarMouseEntered

    private void btn_emprestarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_emprestarMouseExited
        btn_emprestar.setBackground(new Color(227, 6, 19));
        lbl_emprestar.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_emprestarMouseExited

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

    private void tabela_livroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_livroMouseClicked
        if(evt.getClickCount() == 2) {
            verDetalhes();
        }
    }//GEN-LAST:event_tabela_livroMouseClicked

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
            java.util.logging.Logger.getLogger(FrmListaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmListaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmListaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmListaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmListaLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_detalhes;
    private javax.swing.JPanel btn_emprestar;
    private javax.swing.JPanel btn_novo;
    private javax.swing.JPanel btn_plus3;
    private javax.swing.JPanel btn_reservar;
    private javax.swing.JPanel btn_voltar;
    private javax.swing.JComboBox cb_condicao;
    private javax.swing.JComboBox cb_disponibilidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_detalhes;
    private javax.swing.JLabel lbl_emprestar;
    private javax.swing.JLabel lbl_novo;
    private javax.swing.JLabel lbl_reservar;
    private javax.swing.JLabel lbl_voltar;
    private javax.swing.JTable tabela_livro;
    private java.awt.TextField txt_busca;
    // End of variables declaration//GEN-END:variables
}
