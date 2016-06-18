/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment.Model;

/**
 *
 * @author WIBSA-PC144
 */
public class User {
    public int id;
    public Plano plano;
    private boolean Titular;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public boolean isTitular() {
        return Titular;
    }

    public void setTitular(boolean isTitular) {
        this.Titular = isTitular;
    }

    public boolean plano(String canal_Esporte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
