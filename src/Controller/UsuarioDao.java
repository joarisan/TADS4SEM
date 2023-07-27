/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuario;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDao {
    
    private PreparedStatement pst;
    private ResultSet rs;
    private String consultarUsuario = "select * from usuario";
    private String consultarUsuarioNome = "select * from usuario where usuario.nome like ?";
    private String incluirUsuario = "insert into usuario (nome, login, senha, email, perfil) values (?,?,md5(?),?,?)";
    private String alterarUsuario = "update set usuario nome = ?,login = ?, senha = md5(?), email = ?, perfil = ?, where cliente.id = ?";
    private String excluirUsuario = "delete from usuario where usuario.id = ?";

   
    
    
    public List<Usuario> consultarUsuario() {

        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        Usuario usuario;

        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultarUsuario);
            rs = pst.executeQuery();

            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("Login"));
                usuario.setSenha(rs.getString("Senha"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPerfil(rs.getString("Perfil"));
                
                
                //adiciionamdo na lista
                listaUsuario.add(usuario);

            }
            Conexao.desconectar(Conexao.conectar());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuario;
    }
    
    public List<Usuario> consultarUsuarioNome (String nome){
        
        List<Usuario> listaUsuario = new ArrayList<>();
        Usuario usuario;
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultarUsuarioNome);
            nome = "%"+nome +"%";
            pst.setString(1, nome);
            rs = pst.executeQuery();
                
            while (rs.next()){
               
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("Login"));
                usuario.setSenha(rs.getString("Senha"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPerfil(rs.getString("Perfil"));
                
                listaUsuario.add(usuario);
                       
            }
             Conexao.desconectar(Conexao.conectar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuario;
    }

    public boolean incluirUsuario(Usuario usuario) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(incluirUsuario);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getLogin());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getEmail());
            pst.setString(5, usuario.getPerfil());
          

            pst.executeUpdate();

            Conexao.desconectar(Conexao.conectar());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean alterarUsuario (Usuario usuario) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alterarUsuario);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getLogin());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getEmail());
            pst.setString(5, usuario.getPerfil());
            pst.setInt(6, usuario.getId());

            Conexao.desconectar(Conexao.conectar());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluirUsuario(Usuario usuario) {
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(excluirUsuario);
          
            pst.setInt(1,usuario.getId());
            
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private String verificarUsuario = "SELECT * FROM usuario WHERE login LIKE ? AND senha LIKE md5(?);";

    public List<Usuario> verificarUsuario(Usuario usuario) {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(verificarUsuario);
            pst.setString(1, usuario.getLogin());
            pst.setString(2, usuario.getSenha());
            rs = pst.executeQuery();
            if(rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setPerfil(rs.getString("perfil"));
                usuario.setEmail(rs.getString("email"));
                listaUsuario.add(usuario);
            }           
            Conexao.desconectar(Conexao.conectar());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaUsuario;
    }
}

    
    

    

