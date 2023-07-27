/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Fornecedor;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class FornecedorDao {

    private PreparedStatement pst;
    private ResultSet rs;
    private String consultarFornecedor = "select * from fornecedor";
    private String consultarFornecedorNome = "select * from fornecedor where fornecedor.nome like?";
    private String incluirFornecedor = "insert into fornecedor (nome, endereco,bairro, cidade, uf, cep, telefone, email)values (?,?,?,?,?,?,?,?)";
    private String alterarFornecedor = "update set fornecedor nome = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? where fornecedor.id = ?";
    private String excluirFornecedor = "delete from fornecedor where fornecedor.id = ? ";

    public List<Fornecedor> consultaFornecedor() {

        List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
        Fornecedor fornecedor;

        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultarFornecedor);
            rs = pst.executeQuery();

            while (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));

                listaFornecedor.add(fornecedor);

            }
            Conexao.desconectar(Conexao.conectar());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFornecedor;
    }

    public List<Fornecedor> consultaFornecedorNome(String nome) {

        List<Fornecedor> listaFornecedor = new ArrayList<>();
        Fornecedor fornecedor;

        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultarFornecedorNome);
         
            nome = "%" + nome + "%";
            pst.setString(1, nome);
            rs = pst.executeQuery();

            while (rs.next()) {

                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));

                listaFornecedor.add(fornecedor);

            }
            Conexao.desconectar(Conexao.conectar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFornecedor;
    }

    public boolean incluirFornecedor(Fornecedor fornecedor) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(incluirFornecedor);
            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getEndereco());
            pst.setString(3, fornecedor.getBairro());
            pst.setString(4, fornecedor.getCidade());
            pst.setString(5, fornecedor.getUf());
            pst.setString(6, fornecedor.getCep());
            pst.setString(7, fornecedor.getTelefone());
            pst.setString(8, fornecedor.getEmail());

            pst.executeUpdate();

            Conexao.desconectar(Conexao.conectar());
            return true;

        } catch (Exception e) {
        }
        return false;
    }
 
    
 public boolean alterarFornecedor(Fornecedor fornecedor){
      try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alterarFornecedor);
            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getEndereco());
            pst.setString(3, fornecedor.getBairro());
            pst.setString(4, fornecedor.getCidade());
            pst.setString(5, fornecedor.getUf());
            pst.setString(6, fornecedor.getCep());
            pst.setString(7, fornecedor.getTelefone());
            pst.setString(8, fornecedor.getEmail());
            pst.setInt(9, fornecedor.getId());

            Conexao.desconectar(Conexao.conectar());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
 }
    public boolean excluirFornecedor(Fornecedor fornecedor) {

        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(excluirFornecedor);

            pst.setInt(1, fornecedor.getId());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}