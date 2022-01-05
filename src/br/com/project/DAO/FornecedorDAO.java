/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.project.DAO;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Endi Jean
 */
public class FornecedorDAO {
    
    private Connection con;

    public FornecedorDAO() {
    
        this.con = new ConnectionFactory().getConnection();
    
    }
    
    
    public List<Fornecedor> listarFornecedores(String nome) {

        try {

            //Criar lista
            List<Fornecedor> lista = new ArrayList<>();

            String sql = "SELECT * FROM tb_Fornecedor WHERE nome LIKE ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setIdFornecedor(rs.getInt("id_fornecedor"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));

                lista.add(obj);

            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);
            return null;
        }
     
     }
    
    
    public void cadastrarFornecedor(Fornecedor fornecedor){
        
        try {
            
            String sql = "insert into tb_fornecedor "
                + "(nome, cnpj, email, celular, telefone, cep, endereco, numero, bairro, cidade, uf) "
                + "values (?,?,?,?,?,?,?,?,?,?,?);";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getCelular());
            stmt.setString(5, fornecedor.getTelefone());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setInt(8, fornecedor.getNumero());
            stmt.setString(9, fornecedor.getBairro());
            stmt.setString(10, fornecedor.getCidade());
            stmt.setString(11, fornecedor.getUf());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Novo registro salvo");
            
        
        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, erroSql);
        } 
        
    }
    
     public void excluirFornecedores(Fornecedor obj) {
        try {

            String sql = "DELETE FROM tb_fornecedor WHERE id_fornecedor = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getIdFornecedor());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);

        }

    }
    
    public List<Fornecedor> listarFornecedor() {

        try {

            //Criar lista
            List<Fornecedor> lista = new ArrayList<>();

            String sql = ("SELECT * FROM tb_fornecedor");

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setIdFornecedor(rs.getInt("id_fornecedor"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));;
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));

                lista.add(obj);

            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);
            return null;
        }

    }
    
    public void alterarFornecedor(Fornecedor obj) {

        try {

            String sql = "UPDATE tb_fornecedor SET nome = ?, cnpj = ?,"
                    + "email = ?,celular = ?,telefone = ?,cep = ?,endereco = ?,"
                    + "numero = ?,bairro = ?,cidade = ?,"
                    + "uf = ? WHERE id_fornecedor = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getCelular());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getBairro());
            stmt.setString(10, obj.getCidade());
            stmt.setString(11, obj.getUf());
            stmt.setInt(12, obj.getIdFornecedor());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);

        }

    }
    
    public Fornecedor consultaPorNome(String nome) {

        try {

            String sql = "SELECT * FROM tb_fornecedor WHERE nome = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            Fornecedor obj = new Fornecedor();

            if (rs.next()) {

                obj.setIdFornecedor(rs.getInt("id_fornecedor"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setCelular(rs.getString("celular"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));

            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");

            return null;

        }
      
      }
    
}
