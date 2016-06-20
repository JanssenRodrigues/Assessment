/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment.Model;

import java.sql.Array;
import java.util.List;

public class Plano {
    public int id;
    public List<Canal> canais;
    public Array[] canais2;

    public Array[] getCanais2() {
        return canais2;
    }

    public void setCanais2(Array[] canais2) {
        this.canais2 = canais2;
    }

    public String name;

    public List<Canal> getCanais() {
        return canais;
    }

    public void setCanais(List<Canal> Canais) {
        this.canais = Canais;
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
