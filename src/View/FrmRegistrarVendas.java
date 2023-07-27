/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Controller.VendaDao;
import Model.Cliente;
import Model.ItensVenda;
import Model.Produto;
import Model.Venda;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Usuário
 */
public class FrmRegistrarVendas extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmRegistrarVendas
     */
    public FrmRegistrarVendas() {
        initComponents();
        defineModelo();
        itensVenda = new ArrayList<ItensVenda>();

    }
    private DefaultTableModel tableModel;
    private List<ItensVenda> itensVenda;
    private Produto produto;
    private Cliente cliente;

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
        jTextProduto.setText(produto.getNome());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void defineModelo() {
        tableModel = (DefaultTableModel) jTableRegistrarVendas.getModel();
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            jFormattedValorTotal.setFormatterFactory(new DefaultFormatterFactory(formatterValor));

            DecimalFormat formatoEstoque = new DecimalFormat("#,###");
            NumberFormatter formatterEstoque = new NumberFormatter(formatoEstoque);
            formatterEstoque.setValueClass(Integer.class);
            jFormattedQuantidade.setFormatterFactory(new DefaultFormatterFactory(formatterEstoque));

            jTableRegistrarVendas.getColumnModel().getColumn(0).setPreferredWidth(400);
        } catch (Exception ex) {
            Logger.getLogger(FrmRegistrarVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void listar() {
       
        int numeroLinhas = jTableRegistrarVendas.getRowCount();
        for (int i = 0; i < numeroLinhas; i++) {
            tableModel.removeRow(0);
        }
        double valorTotal = 0.0;
        
        for (int i = 0; i < itensVenda.size(); i++) {
            tableModel.insertRow(i, new Object[]{itensVenda.get(i).getProduto().getNome(),
                itensVenda.get(i).getQtde(),
                itensVenda.get(i).getProduto().getValor(),
                itensVenda.get(i).getProduto().getValor() * itensVenda.get(i).getQtde()
            });
            valorTotal += itensVenda.get(i).getProduto().getValor() * itensVenda.get(i).getQtde();
        }
        jFormattedValorTotal.setValue(valorTotal);

    }

    

    private void incluirProduto() {
        if (produto == null) {
            JOptionPane.showMessageDialog(this, "Selecionar o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            jBConsultarProduto.requestFocus();
        } else if (jFormattedQuantidade.getText() == null) {
            JOptionPane.showMessageDialog(this, "Informe a quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            ItensVenda item = new ItensVenda();
            item.setProduto(produto);
            item.setQtde((Integer) jFormattedQuantidade.getValue());
            item.setValor(produto.getValor());

            itensVenda.add(item);
            listar();
        }
    }

    private void excluirProduto() {
        if (jTableRegistrarVendas.getSelectedRow() != -1) {
            itensVenda.remove(jTableRegistrarVendas.getSelectedRow());
            listar();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registarVenda() {
        if (getCliente() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (itensVenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Insira itens na venda!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Venda venda = new Venda();
            venda.setCliente(getCliente());

            Calendar dataAtual = Calendar.getInstance();
            venda.setDataVenda(new java.sql.Date(dataAtual.getTime().getTime()));

            venda.setItensVenda(itensVenda);

            VendaDao vendaBD = new VendaDao();
            if (vendaBD.registrarVenda(venda)) {
                JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextCliente = new javax.swing.JTextField();
        jBConsultarCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextProduto = new javax.swing.JTextField();
        jBConsultarProduto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jBIncluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRegistrarVendas = new javax.swing.JTable();
        jFormattedQuantidade = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedValorTotal = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jBExcluirItem = new javax.swing.JButton();
        jBSalvar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Java GUI Swuing TADS - 2022/3!!!");
        setPreferredSize(new java.awt.Dimension(600, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrar Vendas");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextClienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTextCliente, gridBagConstraints);

        jBConsultarCliente.setText("....");
        jBConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultarClienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jBConsultarCliente, gridBagConstraints);

        jLabel3.setText("Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTextProduto, gridBagConstraints);

        jBConsultarProduto.setText("...");
        jBConsultarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultarProdutoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jBConsultarProduto, gridBagConstraints);

        jLabel4.setText("Qtd");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        jBIncluir.setText("Incluir");
        jBIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIncluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jBIncluir, gridBagConstraints);

        jTableRegistrarVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "Qtda", "VlrUnit", "VlrTotal"
            }
        ));
        jScrollPane1.setViewportView(jTableRegistrarVendas);
        if (jTableRegistrarVendas.getColumnModel().getColumnCount() > 0) {
            jTableRegistrarVendas.getColumnModel().getColumn(0).setMinWidth(0);
            jTableRegistrarVendas.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 664;
        gridBagConstraints.ipady = 183;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 11);
        jPanel2.add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jFormattedQuantidade, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Valor Total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 527, 0, 0);
        jPanel3.add(jLabel5, gridBagConstraints);

        jFormattedValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedValorTotalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jFormattedValorTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jBExcluirItem.setText("Excluir Item");
        jBExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirItemActionPerformed(evt);
            }
        });
        jPanel4.add(jBExcluirItem);

        jBSalvar.setText("Salvar");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(jBSalvar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextClienteActionPerformed

    private void jFormattedValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedValorTotalActionPerformed

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        registarVenda();
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jBConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarClienteActionPerformed
        FrmCliente clienteFrame = new FrmCliente(this);
        clienteFrame.setVisible(true);
        this.getDesktopPane().add(clienteFrame);
        clienteFrame.toFront();
    }//GEN-LAST:event_jBConsultarClienteActionPerformed

    private void jBConsultarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarProdutoActionPerformed
        FrmProduto produtoFrame = new FrmProduto(this);
        produtoFrame.setVisible(true);
        this.getDesktopPane().add(produtoFrame);
        produtoFrame.toFront();    }//GEN-LAST:event_jBConsultarProdutoActionPerformed

    private void jBIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIncluirActionPerformed
        incluirProduto();

    }//GEN-LAST:event_jBIncluirActionPerformed

    private void jBExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirItemActionPerformed
        excluirProduto();    }//GEN-LAST:event_jBExcluirItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBConsultarCliente;
    private javax.swing.JButton jBConsultarProduto;
    private javax.swing.JButton jBExcluirItem;
    private javax.swing.JButton jBIncluir;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JFormattedTextField jFormattedQuantidade;
    private javax.swing.JFormattedTextField jFormattedValorTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRegistrarVendas;
    private javax.swing.JTextField jTextCliente;
    private javax.swing.JTextField jTextProduto;
    // End of variables declaration//GEN-END:variables
}
