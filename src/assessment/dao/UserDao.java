/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment.dao;

import assessment.Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends AbstractDao<User>{

    @Override
    public boolean openConnection() {
        return super.openConnection(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public User save(User user) {
        User temp = null;
        if(super.openConnection()){
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement("INSERT INTO user (plano, isTitular) values (?, ?);");            
                ps.setInt(1, user.getPlano().getId());
                ps.setBoolean(2, user.isTitular());
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            super.closeConnection();
        }
             
        return temp;
    }

    @Override
    public User update(User t) {
       return null;
    }

    @Override
    public boolean delete(int id) {
      return true;
    }

    @Override
    public List<User> list() {
       return null;
    }

    @Override
    public User find(int id) {
        openConnection();
        User user = new User();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("select id from sistema.user where id = (?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ps.execute();
            user.setId(rs.getInt("id"));
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        closeConnection();
        return user;
    }

    public User find(Scanner id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
