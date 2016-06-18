/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment.Model;

import java.util.List;

public class Plano {
    private int id;
    private String name;
    private List<Canal> Canais;

    public List<Canal> getCanais() {
        return Canais;
    }

    public void setCanais(List<Canal> Canais) {
        this.Canais = Canais;
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
