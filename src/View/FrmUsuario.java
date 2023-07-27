/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ClienteDao;
import Controller.Conexao;
import Controller.UsuarioDao;
import Model.Cliente;
import Model.Usuario;
import Util.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author teclab
 */
public class FrmUsuario extends javax.swing.JFrame {

    private int modo;
    List<Usuario> usuarios;
    FrmRegistrarVendas registraVenda;

    /**
     * Creates new form FrmUsuario
     */
    public FrmUsuario() {
        initComponents();

    }

    public void Listar() {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarios = usuarioDao.consultarUsuario();
        DefaultTableModel dados = (DefaultTableModel) jTableUsuario.getModel();
        dados.setNumRows(0);

        for (Usuario usuario : usuarios) {
            dados.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),});
        }

    }

    public void incluirUsuario() {
        if (jTextFieldUsuarioNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "informe o nome do Usuario", "Erro", JOptionPane.ERROR_MESSAGE);
            jTextFieldUsuarioNome.requestFocus();
        } else {
            Usuario usuario = new Usuario();
            usuario.setNome(jTextFieldUsuarioNome.getText().trim());
            usuario.setLogin(jTextFieldUsuarioLogin.getText().trim());
            usuario.setSenha(jPasswordFieldSenha.getText().trim()); // observar
            usuario.setEmail(jTextFieldUsuarioEmail.getText().trim());
            usuario.setPerfil((String) jComboBoxPerfil.getSelectedItem()); // observar

            UsuarioDao usuarioDao = new UsuarioDao();
            if (usuarioDao.incluirUsuario(usuario)) {
                JOptionPane.showMessageDialog(this, "usuario cadastrado com sucesso!!!", "confirmado", JOptionPane.INFORMATION_MESSAGE);
                Listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "erro ao cadastrar o usuario", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void alterarUsuario() {
        if (jTextFieldUsuarioNome.getText().trim().equals("")) {
            JOptionPane.showConfirmDialog(this, "Informe o nome do Usuario", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldUsuarioNome.requestFocus();
        } else {
            Usuario usuario = new Usuario();
            usuario.setId(usuarios.get(jTableUsuario.getSelectedRow()).getId());
            usuario.setNome(jTextFieldUsuarioNome.getText().trim());
            usuario.setLogin(jTextFieldUsuarioLogin.getText().trim());
            // usuario.setSenha(JpSenha.getText().trim()); //// resolver
            usuario.setEmail(jTextFieldUsuarioEmail.getText().trim());
            usuario.setPerfil(jComboBoxPerfil.getActionCommand()); ////

            UsuarioDao usuarioDao = new UsuarioDao();
            if (usuarioDao.alterarUsuario(usuario)) {
                JOptionPane.showMessageDialog(this, "usuario Alterado com Sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                Listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Informe o nome do usuario!!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void excluirUsuario() {
        UsuarioDao usuarioDao = new UsuarioDao();
        if (usuarioDao.excluirUsuario(usuarios.get(jTableUsuario.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Dados do usuario excluídos com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            Listar();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limparCampos() {
        jTextFieldUsuarioNome.setText("");
        jTextFieldUsuarioLogin.setText("");
        jTextFieldUsuarioEmail.setText("");
    }

    private void habilitarCampos() {
        jTextFieldUsuarioNome.setEnabled(true);
        jTextFieldUsuarioLogin.setEnabled(true);
        jPasswordFieldSenha.setEnabled(true);
        jTextFieldUsuarioEmail.setEnabled(true);
        jComboBoxPerfil.setEnabled(true);
    }

    private void desabilitarCampos() {
        jTextFieldUsuarioNome.setEnabled(false);
        jTextFieldUsuarioLogin.setEnabled(false);
        jPanel1.setEnabled(false);
        jTextFieldUsuarioEmail.setEnabled(false);
        jComboBoxPerfil.setEnabled(false);
    }

    private void desabilitarBotoes() {
        jButtonUsuarioNovo.setEnabled(true);
        jButtonUsuarioAlterar.setEnabled(true);
        jButtonUsuarioExcluir.setEnabled(true);
        jButtonUsuarioSalvar.setEnabled(false);
        jButtonUsuarioCancelar.setEnabled(false);

    }

    private void habilitarBotoes() {
        jTextFieldUsuarioLogin.setEnabled(false);
        jTextFieldUsuarioLogin.setEnabled(false);
        jTextFieldUsuarioLogin.setEnabled(false);
        jTextFieldUsuarioLogin.setEnabled(true);
        jTextFieldUsuarioLogin.setEnabled(true);

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
        jTextFieldFiltroNome = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuario = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldUsuarioNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldUsuarioLogin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldUsuarioEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxPerfil = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jButtonUsuarioNovo = new javax.swing.JButton();
        jButtonUsuarioAlterar = new javax.swing.JButton();
        jButtonUsuarioExcluir = new javax.swing.JButton();
        jButtonUsuarioSalvar = new javax.swing.JButton();
        jButtonUsuarioCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Java GUI Swing - TADS 2023 ! ! !");
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Filtro Por Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 500;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTextFieldFiltroNome, gridBagConstraints);

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jButtonPesquisar, gridBagConstraints);

        jTableUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Perfil"
            }
        ));
        jScrollPane1.setViewportView(jTableUsuario);
        if (jTableUsuario.getColumnModel().getColumnCount() > 0) {
            jTableUsuario.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 300));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        jTextFieldUsuarioNome.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextFieldUsuarioNome, gridBagConstraints);

        jLabel4.setText("Login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        jTextFieldUsuarioLogin.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextFieldUsuarioLogin, gridBagConstraints);

        jLabel5.setText("Senha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel5, gridBagConstraints);

        jPasswordFieldSenha.setText("jPasswordField1");
        jPasswordFieldSenha.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jPasswordFieldSenha, gridBagConstraints);

        jLabel6.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        jTextFieldUsuarioEmail.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextFieldUsuarioEmail, gridBagConstraints);

        jLabel7.setText("Perfil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jLabel7, gridBagConstraints);

        jComboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuário Comum", "Usuário Admin" }));
        jComboBoxPerfil.setEnabled(false);
        jComboBoxPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPerfilActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jComboBoxPerfil, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonUsuarioNovo.setText("Novo");
        jButtonUsuarioNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuarioNovoActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonUsuarioNovo);

        jButtonUsuarioAlterar.setText("Alterar");
        jButtonUsuarioAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuarioAlterarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonUsuarioAlterar);

        jButtonUsuarioExcluir.setText("Excluir");
        jButtonUsuarioExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuarioExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonUsuarioExcluir);

        jButtonUsuarioSalvar.setText("Salvar");
        jButtonUsuarioSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuarioSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonUsuarioSalvar);

        jButtonUsuarioCancelar.setText("Cancelar");
        jButtonUsuarioCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuarioCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonUsuarioCancelar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUsuarioNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuarioNovoActionPerformed
        habilitarCampos();
        limparCampos();
        habilitarBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_jButtonUsuarioNovoActionPerformed

    private void jButtonUsuarioAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuarioAlterarActionPerformed
        if (jTableUsuario.getSelectedRow() != -1) {
            habilitarCampos();
            habilitarBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Fornecedor  da Lista", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonUsuarioAlterarActionPerformed

    private void jButtonUsuarioExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuarioExcluirActionPerformed
        if (jTableUsuario.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirma exclusão de usuario?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluirUsuario();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um usuario na Lista");
            }
        }
    }//GEN-LAST:event_jButtonUsuarioExcluirActionPerformed

    private void jButtonUsuarioSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuarioSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            incluirUsuario();
        } else if (modo == Constantes.EDIT_MODE) {
            alterarUsuario();
        }
    }//GEN-LAST:event_jButtonUsuarioSalvarActionPerformed

    private void jButtonUsuarioCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuarioCancelarActionPerformed
        limparCampos();
        desabilitarBotoes();
        desabilitarCampos();    }//GEN-LAST:event_jButtonUsuarioCancelarActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
 String nome = "%" + jTextFieldFiltroNome.getText() + "%";
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarios = usuarioDao.consultarUsuarioNome(nome);

        DefaultTableModel dados = (DefaultTableModel) jTableUsuario.getModel();

        dados.setRowCount(0);
        for (Usuario usuario : usuarios) {
            dados.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),});
        }
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jComboBoxPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPerfilActionPerformed

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
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonUsuarioAlterar;
    private javax.swing.JButton jButtonUsuarioCancelar;
    private javax.swing.JButton jButtonUsuarioExcluir;
    private javax.swing.JButton jButtonUsuarioNovo;
    private javax.swing.JButton jButtonUsuarioSalvar;
    private javax.swing.JComboBox<String> jComboBoxPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsuario;
    private javax.swing.JTextField jTextFieldFiltroNome;
    private javax.swing.JTextField jTextFieldUsuarioEmail;
    private javax.swing.JTextField jTextFieldUsuarioLogin;
    private javax.swing.JTextField jTextFieldUsuarioNome;
    // End of variables declaration//GEN-END:variables
}
