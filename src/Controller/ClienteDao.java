/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cliente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teclab
 */
public class ClienteDao {

    private PreparedStatement pst;
    private ResultSet rs;
    private String consultarCliente = "select * from cliente";
    private String consultarClienteNome = "select * from cliente where cliente.nome like ?";
    private String incluirCliente = "insert into cliente (nome, endereco, bairro, cidade, uf, cep, telefone, email) values (?,?,?,?,?,?,?,?)";
    private String alterarCliente = "update set cliente nome = ?,endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? where cliente.id = ?";
    private String excluirCliente = "delete from cliente where cliente.id = ?";

    public List<Cliente> consultaCliente() {

        List<Cliente> listaCliente = new ArrayList<Cliente>();
        Cliente cliente;

        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultarCliente);
            rs = pst.executeQuery();

            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("Endereco"));
                cliente.setBairro(rs.getString("Bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setCep(rs.getString("Cep"));
                cliente.setUf(rs.getString("Uf"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setEmail(rs.getString("Email"));
                //adiciionamdo na lista
                listaCliente.add(cliente);

            }
            Conexao.desconectar(Conexao.conectar());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCliente;
    }
    
    public List<Cliente> consultaClienteNome (String nome){
        
        List<Cliente> listaCliente = new ArrayList<>();
        Cliente cliente;
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultarClienteNome);
            nome = "%"+nome +"%";
            pst.setString(1, nome);
            rs = pst.executeQuery();
                
            while (rs.next()){
               
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("Endereco"));
                cliente.setBairro(rs.getString("Bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setCep(rs.getString("Cep"));
                cliente.setUf(rs.getString("Uf"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setEmail(rs.getString("Email"));
                
                listaCliente.add(cliente);
                       
            }
             Conexao.desconectar(Conexao.conectar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCliente;
    }

    public boolean incluirCliente(Cliente cliente) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(incluirCliente);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEndereco());
            pst.setString(3, cliente.getBairro());
            pst.setString(4, cliente.getCidade());
            pst.setString(5, cliente.getUf());
            pst.setString(6, cliente.getCep());
            pst.setString(7, cliente.getTelefone());
            pst.setString(8, cliente.getEmail());

            pst.executeUpdate();

            Conexao.desconectar(Conexao.conectar());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean alterarCliente(Cliente cliente) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alterarCliente);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEndereco());
            pst.setString(3, cliente.getBairro());
            pst.setString(4, cliente.getCidade());
            pst.setString(5, cliente.getUf());
            pst.setString(6, cliente.getCep());
            pst.setString(7, cliente.getTelefone());
            pst.setString(8, cliente.getEmail());
            pst.setInt(9, cliente.getId());

            Conexao.desconectar(Conexao.conectar());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluirCliente(Cliente cliente) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(excluirCliente);
          
            pst.setInt(1,cliente.getId());
            
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
