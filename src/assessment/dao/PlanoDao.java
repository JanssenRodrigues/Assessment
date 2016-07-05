package assessment.dao;

import assessment.Model.Plano;
import static assessment.dao.AbstractDao.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanoDao extends AbstractDao<Plano> {
    PreparedStatement ps;
    
    @Override
    public Plano save(Plano plano) {
        try {
            ps = conn.prepareStatement("INSERT INTO plano (nome) VALUES (?)");
            ps.setString(1, plano.getName());
            ps.executeQuery();
         } catch (SQLException ex) {
            Logger.getLogger(PlanoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.closeConnection();
        return plano;
    }

    @Override
    public Plano update(Plano plano) {
        try {
            ps = conn.prepareStatement("UPDATE plano SET nome = (?) WHERE id = (?)");
            ps.setString(1, plano.getName());
            ps.setInt(2, plano.getId());
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PlanoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.closeConnection();
        return plano;
    }

    @Override
    public boolean delete(int id) {
        try {
            ps = conn.prepareStatement("DELETE FROM plano WHERE id = (?)");
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PlanoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.closeConnection();
        return true;
    }

    @Override
    public List<Plano> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Plano find(int id) {
      Plano plano = new Plano();
        openConnection();        
        PreparedStatement ps, ps2;
        ResultSet rs, rs2;
        try {
            ps = conn.prepareStatement("SELECT * FROM plano where id = (?)");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                plano.setId(rs.getInt("id"));
                plano.setName(rs.getString("nome"));
                ArrayList<String> provCanais = new ArrayList<String>();
                provCanais.add(rs.getString("canais"));
                plano.setCanais2(provCanais);
            }else{
                System.out.println("usuario nao identificado");
            }
        } catch (Exception ex) {
           ex.printStackTrace();
           System.err.println("User find: SQLException:" + ex.getMessage());
           
        }
        super.closeConnection();
        return plano;
    }
    
}
