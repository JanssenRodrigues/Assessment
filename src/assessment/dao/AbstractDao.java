package assessment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDao<T> {
    public static Connection conn = null;
    
    public AbstractDao(){
        
    }
    public boolean openConnection(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sistema", "root", "");
            System.out.println("Conexão realizada com sucesso");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Falhou");
            return false;
        }
        return true;
    }
    
    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public abstract T save(T t);
    public abstract T update(T t);
    public abstract boolean delete(int id);
    public abstract List<T> list();
    public abstract T find(int id);
}
