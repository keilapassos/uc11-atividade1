/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import com.mysql.cj.conf.PropertyKey;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        conn = new conectaDAO().connectDB();

        try {
            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, produto.getNome());
            prepStmt.setInt(2, produto.getValor());
            prepStmt.setString(3, produto.getStatus());

            prepStmt.execute();
            prepStmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro);
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT * FROM produtos";

        conn = new conectaDAO().connectDB();
        listagem = new ArrayList<>();

        try {
            PreparedStatement prepStmt = conn.prepareStatement(sql);
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));

                listagem.add(produto);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro);
        }
        return listagem;
    }

    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        conn = new conectaDAO().connectDB();

        try {
            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
            prepStmt.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + erro);
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        conn = new conectaDAO().connectDB();
        listagem = new ArrayList<>();

        try {
            PreparedStatement prepStmt = conn.prepareStatement(sql);
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));

                listagem.add(produto);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + erro);
        }
        
        return listagem;
    }

}
