import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conectaDAO dao = new conectaDAO();
        conn = dao.connectDB();
        
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão: Não foi possível conectar ao banco.");
            return;
        }
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.execute();
            prep.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        }
    }
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        String sql = "SELECT * FROM produtos";
        
        try {
            conectaDAO dao = new conectaDAO();
            conn = dao.connectDB();
            
            if (conn == null){
                return listagem; 
            }
            
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            listagem = new ArrayList<>();
            
            while(resultset.next()){
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                
                listagem.add(p);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        }
        
        return listagem;
    }
    
    
    public void venderProduto(int id) {
        
        conectaDAO dao = new conectaDAO();
        conn = dao.connectDB();
        
        if(conn == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexão ao tentar vender.");
            return;
        }
        
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            
            prep.execute();
            
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        }
    }
    
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        
        String sql = "SELECT * FROM produtos WHERE status = ?";
        
        try {
            conectaDAO dao = new conectaDAO();
            conn = dao.connectDB();
            
            if (conn == null){
                return listagem; 
            }
            
            prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            resultset = prep.executeQuery();
            
            listagem = new ArrayList<>();
            
            while(resultset.next()){
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                
                listagem.add(p);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        }
        
        return listagem;
    }

}