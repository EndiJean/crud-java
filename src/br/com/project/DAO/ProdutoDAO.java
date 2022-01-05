/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.project.DAO;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Fornecedor;
import br.com.project.model.Produto;
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
public class ProdutoDAO {
    
    private Connection con;

    public ProdutoDAO() {
        
        this.con = new ConnectionFactory().getConnection();
    
    }
    
    
    public void cadastrar(Produto produto) {
        try {

            String sql = "INSERT INTO tb_produto (descricao,preco,qtd_estoque,fornecedor_id) "
                    + " values (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQtdEstoque());
            
            stmt.setInt(4, produto.getFornecedor().getIdFornecedor());
            

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);

        }
    }
    
    
    public void alterar(Produto produto) {

        try {

            String sql = "UPDATE tb_produto SET descricao = ?, preco = ?,"
                    + "qtd_estoque = ?,fornecedor_id = ? where id_produto = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQtdEstoque());
            
            stmt.setInt(4, produto.getFornecedor().getIdFornecedor());
            
            stmt.setInt(5, produto.getIdProduto());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);

        }

    }
    
    public void excluir(Produto produto) {
        try {

            String sql = "DELETE FROM tb_produto WHERE id_produto = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, produto.getIdProduto());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);

        }
     }    
    
    public List<Produto> listarProdutos(){
        try {
            
            List<Produto> lista = new ArrayList<>();
        
            String sql = "select p.id_produto, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produto as p "
                + "inner join tb_fornecedor as f on (p.fornecedor_id = f.id_fornecedor)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        
        while (rs.next()){
            Produto obj = new Produto();
            Fornecedor f = new Fornecedor();
            
            obj.setIdProduto(rs.getInt("p.id_produto"));
            obj.setDescricao(rs.getString("p.descricao"));
            obj.setPreco(rs.getDouble("p.preco"));
            obj.setQtdEstoque(rs.getInt("p.qtd_estoque"));
            
            f.setNome(rs.getString("f.nome"));
            
            obj.setFornecedor(f);
            
            lista.add(obj);
            
        }
        
        return lista;
        
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);        
        }    
        return null;
    }
    
    public List<Produto> listarProdutosPorNome(String nome){
        try {
            
            List<Produto> lista = new ArrayList<>();
        
            String sql = "select p.id_produto, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produto as p "
                + "inner join tb_fornecedor as f on (p.fornecedor_id = f.id_fornecedor)"
                    + " where p.descricao like ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
       stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();

        
        while (rs.next()){
            Produto obj = new Produto();
            Fornecedor f = new Fornecedor();
            
            obj.setIdProduto(rs.getInt("p.id_produto"));
            obj.setDescricao(rs.getString("p.descricao"));
            obj.setPreco(rs.getDouble("p.preco"));
            obj.setQtdEstoque(rs.getInt("p.qtd_estoque"));
            
            f.setNome(rs.getString("f.nome"));
            
            obj.setFornecedor(f);
            
            lista.add(obj);
            
        }
        
        return lista;
        
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);        
        }    
        return null;
    }
    
}
