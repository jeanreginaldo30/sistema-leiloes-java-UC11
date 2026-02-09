import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
            
            String senha = "7530"; 
            
            
            
            String url = "jdbc:mysql://localhost/uc11?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            
            
            conn = DriverManager.getConnection(url, "root", senha);
            
        } catch (SQLException erro){
            
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }
        return conn;
    }
    
}