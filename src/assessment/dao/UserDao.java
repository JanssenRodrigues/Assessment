/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment.dao;

import assessment.Model.Plano;
import assessment.Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends AbstractDao<User>{
    PlanoDao planoConn = new PlanoDao();
    PreparedStatement ps;
    @Override
    public User save(User user) {
        User temp = null;
        if(super.openConnection()){
            try {
                ps = conn.prepareStatement("INSERT INTO user (plano, titular) values (?, ?);");            
                ps.setInt(1, user.getPlano().getId());
                ps.setBoolean(2, user.isTitular());
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("User save : SQLException:" + ex.getMessage());
                ex.printStackTrace();
            }
            super.closeConnection();
        }
             
        return temp;
    }

    @Override
    public User update(User user) {
        try {
            ps = conn.prepareStatement("UPDATE user SET nome = (?) WHERE id = (?)");
            ps.setString(1, user.getName());
            ps.setInt(2, user.getId());
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.closeConnection();
        return user;
    }

    @Override
    public boolean delete(int id) {
        try {
            ps = conn.prepareStatement("DELETE FROM user WHERE id = (?)");
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.closeConnection();
        return true;
    }

    @Override
    public List<User> list() {
       return null;
    }

    @Override
    public User find(int id) {
        User user = new User();
        Plano plano = new Plano();
        openConnection();        
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("SELECT * FROM sistema.user where id = (?)");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nome"));
                user.setTitular(rs.getBoolean("titular"));
                plano = planoConn.find(rs.getInt("plano"));
                user.setPlano(plano);
            }else{
                System.err.println("Usuário nao identificado");
            }
        } catch (Exception ex) {
           ex.printStackTrace();
           System.err.println("User find: SQLException:" + ex.getMessage());
           
        }
        super.closeConnection();
        return user;
    }
   
}
