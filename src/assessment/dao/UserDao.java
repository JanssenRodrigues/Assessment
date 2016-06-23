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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends AbstractDao<User>{
    
    @Override
    public User save(User user) {
        User temp = null;
        if(super.openConnection()){
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement("INSERT INTO user (plano, titular) values (?, ?);");            
                ps.setInt(1, user.getPlano().getId());
                ps.setBoolean(2, user.isTitular());
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("User save : SQLException:" + ex.getMessage());
                ex.printStackTrace();
            }
            super.closeConnection();
        }
             
        return temp;
    }

    @Override
    public User update(User user) {
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
        User user = null;
        openConnection();        
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("SELECT * FROM sistema.user where id = (?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setTitular(rs.getBoolean("titular"));
                Plano plano;
                //user.setPlano(rs.get("plano"));
            }else{
                System.out.println("usuario nao identificado");
            }
            //System.out.println(rs.getInt("id"));
            //ps.execute();
        } catch (Exception ex) {
           ex.printStackTrace();
           System.out.println("User find: SQLException:" + ex.getMessage());
           
        }
        closeConnection();
        return user;
    }
   
}
