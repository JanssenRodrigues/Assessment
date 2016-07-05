/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment.Model;

import java.util.ArrayList;

public class Plano {
    private int id;
    private String name;
    private ArrayList<String> canais2 = new ArrayList<String>();

    public ArrayList<String> getCanais2() {
        return canais2;
    }

    public void setCanais2(ArrayList<String> canais2) {
        this.canais2 = canais2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
