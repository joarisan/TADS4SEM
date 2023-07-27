/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author clebe
 */
public class Conexao {

    public static Connection conectar() {
        Connection conexao = null;
        //criando um drive  correspondente ao banco
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenamdo informaçãoes referente ao banco de dados 
        String url = "jdbc:mysql://localhost:3306/cvendas";// useTimezone=trues&serveTimezone=utc;
        String user = "root";
        String senha = "root";
// estabelecer a conexao com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, senha);
            return conexao;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return null;

    }

    public static void desconectar(Connection conexao) {
        try {
            if ((conexao != null) && (!conexao.isClosed())) {
                conexao.close();
                // JOptionPane.showMessageDialog(null, "Descontado do Banco de Dados!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não conseguiu descontar do Banco de Dados!!!");
        }
    }
}
