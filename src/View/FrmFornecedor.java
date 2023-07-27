/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Controller.FornecedorDao;

import Model.Fornecedor;
import Util.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class FrmFornecedor extends javax.swing.JInternalFrame {

    private int modo;
    List<Fornecedor> lista;
    private FrmProduto frmProduto;

    /**
     * Creates new form FmForenecedor
     */
    public FrmFornecedor() {
        initComponents();
        jButtonSelFornecedor.setVisible(false);
    }
    public FrmFornecedor(FrmProduto frmProduto){
        initComponents();
        jButtonSelFornecedor.setVisible(true);
        this.frmProduto = frmProduto;
    }

    public void Listar() {
        FornecedorDao fornecedorDao = new FornecedorDao();
        lista = fornecedorDao.consultaFornecedor();
        DefaultTableModel dados = (DefaultTableModel) jTableFornecedor.getModel();
        dados.setNumRows(0);

        for (Fornecedor fornecedor : lista) {
            dados.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome(),});
        }
    }

    public void limparCampos() {
        jTextNome.setText("");
        jTextEndereco.setText("");
        jTextBairro.setText("");
        jTextCidade.setText("");
        JTextUf.setSelectedIndex(0);
        jTextCep.setText("");
        jTextTelefone.setText("");
        jTextEmail.setText("");
    }

    private void habilitarCampos() {
        jTextNome.setEnabled(true);
        jTextEndereco.setEnabled(true);
        jTextBairro.setEnabled(true);
        jTextCidade.setEnabled(true);
        jTextCep.setEnabled(true);
        jTextTelefone.setEnabled(true);
        JTextUf.setEnabled(true);
        jTextEmail.setEnabled(true);
    }

    private void desabilitarCampos() {
        jTextNome.setEnabled(false);
        jTextEndereco.setEnabled(false);
        jTextBairro.setEnabled(false);
        jTextCidade.setEnabled(false);
        jTextCep.setEnabled(false);
        jTextTelefone.setEnabled(false);
        JTextUf.setEnabled(false);
        jTextEmail.setEnabled(false);

    }

    private void desabilitarBotoes() {
        jButtonSalvar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonNovo.setEnabled(true);
        jButtonAlterar.setEnabled(true);
        jButtonExcluir.setEnabled(true);

    }

    private void habilitarBotoes() {
        jButtonSalvar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonNovo.setEnabled(false);
        jButtonAlterar.setEnabled(false);
        jButtonExcluir.setEnabled(false);
    }

    public void incluiFornecedor() {
        if (jTextNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "informe o nome do Fornecedor", "Erro", JOptionPane.ERROR_MESSAGE);
            jTextNome.requestFocus();
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(jTextNome.getText().trim());
            fornecedor.setEndereco(jTextEndereco.getText().trim());
            fornecedor.setBairro(jTextBairro.getText().trim());
            fornecedor.setCidade(jTextCidade.getText().trim());
            fornecedor.setUf(JTextUf.getSelectedItem().toString());
            String cep = jTextCep.getText().replace(".", "");
            cep = cep.replace("-", "");
            fornecedor.setCep(cep);

            String telefone = jTextTelefone.getText().replace("(", "");
            telefone = telefone.replace(")", "");
            telefone = telefone.replace("-", "");
            fornecedor.setTelefone(telefone);
            fornecedor.setEmail(jTextEmail.getText());

            FornecedorDao fornecedorDao = new FornecedorDao();
            if (fornecedorDao.incluirFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso!!!", "confirmado", JOptionPane.INFORMATION_MESSAGE);
                Listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "erro ao cadastrar o Fornecedor", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void alteraFornecedor() {
        if (jTextNome.getText().trim().equals("")) {
            JOptionPane.showConfirmDialog(this, "Informe o nome do Fornecedor", "Error", JOptionPane.ERROR_MESSAGE);
            jTextNome.requestFocus();
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(lista.get(jTableFornecedor.getSelectedRow()).getId());
            fornecedor.setNome(jTextNome.getText().trim());
            fornecedor.setEndereco(jTextEndereco.getText().trim());
            fornecedor.setBairro(jTextBairro.getText().trim());
            fornecedor.setCidade(jTextCidade.getText().trim());
            fornecedor.setUf(JTextUf.getSelectedItem().toString());
            String cep = jTextCep.getText().replace(".", "");
            cep = cep.replace("-", "");
            fornecedor.setCep(cep);
            String telefone = jTextTelefone.getText().replace("(", "");
            telefone = telefone.replace(")", "");
            telefone = telefone.replace(".", "");
            telefone = telefone.replace(" ", "");
            telefone = telefone.replace("-", "");

            fornecedor.setTelefone(telefone);
            fornecedor.setEmail(jTextEmail.getText());

            FornecedorDao fornecedorDao = new FornecedorDao();
            if (fornecedorDao.alterarFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Fornecedor Alterado com Sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                Listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Informe o nome do Fornecedor!!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private void excluirFornecedor() {
        FornecedorDao fornecedorDao = new FornecedorDao();
        if (fornecedorDao.excluirFornecedor(lista.get(jTableFornecedor.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Dados do Fornecedor  excluídos com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            Listar();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTFornecedorFiltro = new javax.swing.JTextField();
        jBPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFornecedor = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextEndereco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextBairro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextCidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        JTextUf = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextCep = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextTelefone = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextEmail = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButtonSelFornecedor = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Java GUI - Swuing - TADS 2022/3");
        setPreferredSize(new java.awt.Dimension(700, 400));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setLayout(new javax.swing.OverlayLayout(jPanel1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fornecedor");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Filtro pelo Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTFornecedorFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFornecedorFiltroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTFornecedorFiltro, gridBagConstraints);

        jBPesquisar.setText("Pesquisar");
        jBPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jBPesquisar, gridBagConstraints);

        jTableFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Fornecedor"
            }
        ));
        jTableFornecedor.getTableHeader().setReorderingAllowed(false);
        jTableFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFornecedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFornecedor);
        if (jTableFornecedor.getColumnModel().getColumnCount() > 0) {
            jTableFornecedor.getColumnModel().getColumn(0).setMinWidth(1);
            jTableFornecedor.getColumnModel().getColumn(0).setMaxWidth(200);
            jTableFornecedor.getColumnModel().getColumn(1).setMinWidth(1);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jLabel3, gridBagConstraints);

        jTextNome.setEnabled(false);
        jTextNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextNome, gridBagConstraints);

        jLabel5.setText("Endereço");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel5, gridBagConstraints);

        jTextEndereco.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextEndereco, gridBagConstraints);

        jLabel6.setText("Bairro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        jTextBairro.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextBairro, gridBagConstraints);

        jLabel7.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel7, gridBagConstraints);

        jTextCidade.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextCidade, gridBagConstraints);

        jLabel8.setText("UF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel8, gridBagConstraints);

        JTextUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SE", "TO", " " }));
        JTextUf.setEnabled(false);
        JTextUf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextUfActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(JTextUf, gridBagConstraints);

        jLabel9.setText("Cep");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel9, gridBagConstraints);

        try {
            jTextCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextCep.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextCep, gridBagConstraints);

        jLabel10.setText("Telefone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        jPanel3.add(jLabel10, gridBagConstraints);

        try {
            jTextTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #.####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextTelefone.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextTelefone, gridBagConstraints);

        jLabel11.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel11, gridBagConstraints);

        jTextEmail.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextEmail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonSelFornecedor.setText("Selecionar Fornecedor");
        jButtonSelFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelFornecedorActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonSelFornecedor);

        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonNovo);

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonAlterar);

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonExcluir);

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.setEnabled(false);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonSalvar);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setEnabled(false);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonCancelar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTextUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextUfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextUfActionPerformed

    private void jBPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarActionPerformed
        String nome = "%" + jTFornecedorFiltro.getText() + "%";
        FornecedorDao fornecedorDao = new FornecedorDao();
        lista = fornecedorDao.consultaFornecedorNome(nome);

        DefaultTableModel dados = (DefaultTableModel) jTableFornecedor.getModel();

        dados.setRowCount(0);
        for (Fornecedor fornecedor : lista) {
            dados.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome(),});

        }
     }//GEN-LAST:event_jBPesquisarActionPerformed

    private void jTFornecedorFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFornecedorFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFornecedorFiltroActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        habilitarCampos();
        limparCampos();
        habilitarBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            incluiFornecedor();
        } else if (modo == Constantes.EDIT_MODE) {
            alteraFornecedor();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNomeActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limparCampos();
        desabilitarBotoes();
        desabilitarCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTableFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFornecedorMouseClicked
        if (jTableFornecedor.getSelectedRow() != -1) {
            int indice = jTableFornecedor.getSelectedRow();
            jTextNome.setText(lista.get(indice).getNome());
            jTextEndereco.setText(lista.get(indice).getEndereco());
            jTextBairro.setText(lista.get(indice).getBairro());
            jTextCidade.setText(lista.get(indice).getCidade());
            JTextUf.setSelectedItem(lista.get(indice).getUf());
            jTextCep.setText(lista.get(indice).getCep());
            jTextTelefone.setText(lista.get(indice).getTelefone());
            jTextEmail.setText(lista.get(indice).getEmail());
        }
    }//GEN-LAST:event_jTableFornecedorMouseClicked

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        if (jTableFornecedor.getSelectedRow() != -1) {
            habilitarCampos();
            habilitarBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Fornecedor  da Lista", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        if (jTableFornecedor.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirma exclusão de cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluirFornecedor();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um cliente na Lista");
            }
        }

    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Listar();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButtonSelFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelFornecedorActionPerformed
      if(jTableFornecedor.getSelectedRow()!= -1){
        frmProduto.setFornecedor(lista.get(jTableFornecedor.getSelectedRow()));
        this.dispose();
        frmProduto.toFront();
        
    }else{
            JOptionPane.showMessageDialog(this,"Selecionar um fornecedor", "Erro",JOptionPane.ERROR_MESSAGE);
            }

    }//GEN-LAST:event_jButtonSelFornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JTextUf;
    private javax.swing.JButton jBPesquisar;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSelFornecedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFornecedorFiltro;
    private javax.swing.JTable jTableFornecedor;
    private javax.swing.JTextField jTextBairro;
    private javax.swing.JFormattedTextField jTextCep;
    private javax.swing.JTextField jTextCidade;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextEndereco;
    private javax.swing.JTextField jTextNome;
    private javax.swing.JFormattedTextField jTextTelefone;
    // End of variables declaration//GEN-END:variables
}
