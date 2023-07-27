/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

/**
 *
 * @author clebe
 */
import Controller.ClienteDao;
import Model.Cliente;
import Util.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class FrmCliente extends javax.swing.JInternalFrame {

    private int modo;
    List<Cliente> clientes;
     FrmRegistrarVendas registraVenda;

    public FrmCliente() {
        initComponents();
        jBSelecionarCliente.setEnabled(false);
    }
         
        
        public FrmCliente(FrmRegistrarVendas registraVenda) {
        initComponents();
        jBSelecionarCliente.setEnabled(true);
        this.registraVenda = registraVenda;
    }

    public void limparCampos(){
        jTxtNome.setText("");
        jTxtEndereco.setText("");
        jTxtBairro.setText("");
        jTxtCidade.setText("");
        jtextUF.setSelectedIndex(0);
        jTxtCEp.setText("");
        jTxtTelefone.setText("");
        jTextEmail.setText("");
   }
    
    public void Listar() {
        ClienteDao clienteDao = new ClienteDao();
         clientes = clienteDao.consultaCliente();
        DefaultTableModel dados = (DefaultTableModel) jTableCliente.getModel();
        dados.setNumRows(0);

        for (Cliente cliente : clientes) {
            dados.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),});
        }

    }

    private void habilitarCampos() {
        jTxtNome.setEnabled(true);
        jTxtEndereco.setEnabled(true);
        jTxtBairro.setEnabled(true);
        jTxtCidade.setEnabled(true);
        jTxtCEp.setEnabled(true);
        jTxtTelefone.setEnabled(true);
        jtextUF.setEnabled(true);
        jTextEmail.setEnabled(true);
    }

    private void desabilitarCampos() {
        jTxtNome.setEnabled(false);
        jTxtEndereco.setEnabled(false);
        jTxtBairro.setEnabled(false);
        jTxtCidade.setEnabled(false);
        jTxtCEp.setEnabled(false);
        jTxtTelefone.setEnabled(false);
        jtextUF.setEnabled(false);
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

    public void incluiCliente() {
        if (jTxtNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "informe o nome do Cliente", "Erro", JOptionPane.ERROR_MESSAGE);
            jTxtNome.requestFocus();
        } else {
            Cliente cliente = new Cliente();
            cliente.setNome(jTxtNome.getText().trim());
            cliente.setEndereco(jTxtEndereco.getText().trim());
            cliente.setBairro(jTxtBairro.getText().trim());
            cliente.setCidade(jTxtCidade.getText().trim());
            cliente.setUf(jtextUF.getSelectedItem().toString());
          String cep = jTxtCEp.getText().replace(".", "");
            cep = cep.replace("-", "");
            cliente.setCep(cep);
            
            String telefone = jTxtTelefone.getText().replace("(", "");
            telefone = telefone.replace(")", "");
            telefone = telefone.replace("-", "");
            cliente.setTelefone(telefone);
            cliente.setEmail(jTextEmail.getText());

            ClienteDao clienteDao = new ClienteDao();
            if (clienteDao.incluirCliente(cliente)) {
                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!!!", "confirmado", JOptionPane.INFORMATION_MESSAGE);
                Listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "erro ao cadastrar o Cliente", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
     public void alteraCliente() {
        if (jTxtNome.getText().trim().equals("")) {
            JOptionPane.showConfirmDialog(this, "Informe o nome do Cliente", "Error", JOptionPane.ERROR_MESSAGE);
            jTxtNome.requestFocus();
        } else {
            Cliente cliente = new Cliente();
            cliente.setId(clientes.get(jTableCliente.getSelectedRow()).getId());
            cliente.setNome(jTxtNome.getText().trim());
            cliente.setEndereco(jTxtEndereco.getText().trim());
            cliente.setBairro(jTxtBairro.getText().trim());
            cliente.setCidade(jTxtCidade.getText().trim());
            cliente.setUf(jtextUF.getSelectedItem().toString());
            String cep = jTxtCEp.getText().replace(".", "");
            cep = cep.replace("-", "");
            cliente.setCep(cep);
            String telefone = jTxtTelefone.getText().replace("(", "");
            telefone = telefone.replace(")", "");
            telefone = telefone.replace(".", "");
            telefone = telefone.replace(" ", "");
            telefone = telefone.replace("-", "");

            cliente.setTelefone(telefone);
            cliente.setEmail(jTextEmail.getText());

            ClienteDao clienteDAO = new ClienteDao();
            if (clienteDAO.alterarCliente(cliente)) {
                JOptionPane.showMessageDialog(this, "Cliente Alterado com Sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                Listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Informe o n ome do Cliente!!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
     }

    private void excluirCliente() {
        ClienteDao clienteDao = new ClienteDao();
        if (clienteDao.excluirCliente(clientes.get(jTableCliente.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Dados do cliente excluídos com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            Listar();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTxtFiltroNome = new javax.swing.JTextField();
        jBtnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTxtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtEndereco = new javax.swing.JTextField();
        Bairro = new javax.swing.JLabel();
        jTxtBairro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtCidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtextUF = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTxtCEp = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jTxtTelefone = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextEmail = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jBSelecionarCliente = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Java GUI Swing - TADS 2023 ! ! !");
        setPreferredSize(new java.awt.Dimension(800, 600));
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
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cliente");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Filtro por Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTxtFiltroNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFiltroNomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTxtFiltroNome, gridBagConstraints);

        jBtnPesquisar.setMnemonic('p');
        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jBtnPesquisar, gridBagConstraints);

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "id", "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCliente.setPreferredSize(new java.awt.Dimension(700, 600));
        jTableCliente.getTableHeader().setReorderingAllowed(false);
        jTableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCliente);
        if (jTableCliente.getColumnModel().getColumnCount() > 0) {
            jTableCliente.getColumnModel().getColumn(0).setMinWidth(1);
            jTableCliente.getColumnModel().getColumn(0).setMaxWidth(200);
            jTableCliente.getColumnModel().getColumn(1).setMinWidth(1);
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
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        jTxtNome.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtNome, gridBagConstraints);

        jLabel4.setText("Endereço");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        jTxtEndereco.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtEndereco, gridBagConstraints);

        Bairro.setText("Bairro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(Bairro, gridBagConstraints);

        jTxtBairro.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtBairro, gridBagConstraints);

        jLabel6.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        jTxtCidade.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtCidade, gridBagConstraints);

        jLabel5.setText("UF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel5, gridBagConstraints);

        jtextUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SE", "TO", " " }));
        jtextUF.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jtextUF, gridBagConstraints);

        jLabel7.setText("CEP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel7, gridBagConstraints);

        try {
            jTxtCEp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTxtCEp.setEnabled(false);
        jTxtCEp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCEpActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtCEp, gridBagConstraints);

        jLabel8.setText("Telefone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel8, gridBagConstraints);

        try {
            jTxtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTxtTelefone.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtTelefone, gridBagConstraints);

        jLabel9.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel9, gridBagConstraints);

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

        jBSelecionarCliente.setText("SelecionarCliente");
        jBSelecionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSelecionarClienteActionPerformed(evt);
            }
        });
        jPanel4.add(jBSelecionarCliente);

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

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        String nome = "%" + jTxtFiltroNome.getText() + "%";
        ClienteDao clienteDao = new ClienteDao();
        clientes = clienteDao.consultaClienteNome(nome);

        DefaultTableModel dados = (DefaultTableModel) jTableCliente.getModel();

        dados.setRowCount(0);
        for (Cliente cliente : clientes) {
            dados.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),});
        }
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        habilitarCampos();
        limparCampos();
        habilitarBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            incluiCliente();
        } else if (modo == Constantes.EDIT_MODE) {
            alteraCliente();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        if (jTableCliente.getSelectedRow() != -1) {
            habilitarCampos();
            habilitarBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecioneo um cliente da Lista", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        if (jTableCliente.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirma exclusão de cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluirCliente();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um cliente na Lista");
            }
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jTableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClienteMouseClicked
   if (jTableCliente.getSelectedRow() != -1) {
            int indice = jTableCliente.getSelectedRow();
            jTxtNome.setText(clientes.get(indice).getNome());
            jTxtEndereco.setText(clientes.get(indice).getEndereco());
            jTxtBairro.setText(clientes.get(indice).getBairro());
            jTxtCidade.setText(clientes.get(indice).getCidade());
            jtextUF.setSelectedItem(clientes.get(indice).getUf());
            jTxtCEp.setText(clientes.get(indice).getCep());
            jTxtTelefone.setText(clientes.get(indice).getTelefone());
            jTextEmail.setText(clientes.get(indice).getEmail());
        }
    }//GEN-LAST:event_jTableClienteMouseClicked

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limparCampos();
        desabilitarBotoes();
        desabilitarCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTxtCEpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCEpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCEpActionPerformed

    private void jTxtFiltroNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFiltroNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFiltroNomeActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Listar();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jBSelecionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSelecionarClienteActionPerformed
    
    if (jTableCliente.getSelectedRow() != -1){
            registraVenda.setCliente(clientes.get(jTableCliente.getSelectedRow()));
            this.dispose();
            registraVenda.toFront();
        }else{
            JOptionPane.showMessageDialog(this, "Selecione um cliente da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    
        }//GEN-LAST:event_jBSelecionarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bairro;
    private javax.swing.JButton jBSelecionarCliente;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSalvar;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTxtBairro;
    private javax.swing.JFormattedTextField jTxtCEp;
    private javax.swing.JTextField jTxtCidade;
    private javax.swing.JTextField jTxtEndereco;
    private javax.swing.JTextField jTxtFiltroNome;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JFormattedTextField jTxtTelefone;
    private javax.swing.JComboBox<String> jtextUF;
    // End of variables declaration//GEN-END:variables
}
