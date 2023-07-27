/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Fornecedor;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PC
 */
public class ProdutoDao {
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    //private AcessoBD acessoBD = new AcessoBD();
    private String consultaProduto = "select p.*, f.* from produto p join fornecedor f on p.fornecedor_id = f.id";
    private String consultaProdutoNome = "select p.*, f.* from produto p join fornecedor f on p.fornecedor_id = f.id where p.nome like ?";
    private String incluiProduto = "insert into produto (nome, fornecedor_id, qtde_estoque, valor) values(?, ?, ?, ?)";
    private String alteraProduto = "update produto set nome = ?, fornecedor_id = ?, qtde_estoque = ?, valor = ? where produto.id = ?";
    private String excluiProduto = "delete from produto where produto.id = ?";

    public List<Produto> consultaProduto() {
        List<Produto> listaProdutos = new ArrayList<Produto>();
        Produto produto;
        try {
           Conexao.conectar();
            pst = Conexao.conectar(). prepareStatement(consultaProduto);
            rs = pst.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("p.id"));
                produto.setNome(rs.getString("p.nome"));
                produto.setQtdeEstoque(rs.getInt("p.qtde_estoque"));
                produto.setValor(rs.getDouble("p.valor"));

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("f.id"));
                fornecedor.setNome(rs.getString("f.nome"));
                fornecedor.setEndereco(rs.getString("f.endereco"));
                fornecedor.setBairro(rs.getString("f.bairro"));
                fornecedor.setCidade(rs.getString("f.cidade"));
                fornecedor.setCep(rs.getString("f.cep"));
                fornecedor.setUf(rs.getString("f.uf"));
                fornecedor.setTelefone(rs.getString("f.telefone"));
                fornecedor.setEmail(rs.getString("f.email"));

                produto.setFornecedor(fornecedor);

                listaProdutos.add(produto);
            }
            Conexao.desconectar(Conexao.conectar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

    public List<Produto> consultaProduto(String nome) {
        List<Produto> listaProdutos = new ArrayList<Produto>();
        Produto produto;
        try {
           Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultaProdutoNome);
            nome = "%" + nome + "%";
            pst.setString(1, nome);
            rs = pst.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("p.id"));
                produto.setNome(rs.getString("p.nome"));
                produto.setQtdeEstoque(rs.getInt("p.qtde_estoque"));
                produto.setValor(rs.getDouble("p.valor"));

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("f.id"));
                fornecedor.setNome(rs.getString("f.nome"));
                fornecedor.setEndereco(rs.getString("f.endereco"));
                fornecedor.setBairro(rs.getString("f.bairro"));
                fornecedor.setCidade(rs.getString("f.cidade"));
                fornecedor.setCep(rs.getString("f.cep"));
                fornecedor.setUf(rs.getString("f.uf"));
                fornecedor.setTelefone(rs.getString("f.telefone"));
                fornecedor.setEmail(rs.getString("f.email"));

                produto.setFornecedor(fornecedor);

                listaProdutos.add(produto);
            }
            Conexao.desconectar(Conexao.conectar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

    public boolean incluiProduto(Produto produto) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(incluiProduto);

            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getFornecedor().getId());
            pst.setInt(3, produto.getQtdeEstoque());
            pst.setDouble(4, produto.getValor());

            pst.executeUpdate();

            Conexao.desconectar(Conexao.conectar());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean alteraProduto(Produto produto) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alteraProduto);

            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getFornecedor().getId());
            pst.setInt(3, produto.getQtdeEstoque());
            pst.setDouble(4, produto.getValor());
            pst.setInt(5, produto.getId());

            pst.executeUpdate();

            Conexao.desconectar(Conexao.conectar());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean excluiProduto(Produto produto) {
        try {
           Conexao.desconectar(Conexao.conectar());
            pst = Conexao.conectar().prepareStatement(excluiProduto);

            pst.setInt(1, produto.getId());

            pst.executeUpdate();

            Conexao.desconectar(Conexao.conectar());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}


