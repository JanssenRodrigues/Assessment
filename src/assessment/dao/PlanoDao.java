package assessment.dao;

import assessment.Model.Plano;
import assessment.Model.User;
import static assessment.dao.AbstractDao.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PlanoDao extends AbstractDao<Plano> {

    @Override
    public Plano save(Plano plano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Plano update(Plano plano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Plano> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Plano find(int id) {
      User user = null;
        openConnection();        
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("SELECT id FROM sistema.user where id = (?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                Plano plano;
                user.setPlano(rs.getInt("plano_id"));
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
