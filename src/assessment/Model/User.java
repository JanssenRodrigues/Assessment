package assessment.Model;

import assessment.Assessment;
import java.sql.Date;
import java.util.ArrayList;

public class User extends Assessment{
    private int id;
    private String name;
    private Plano plano;
    private boolean titular;   
    private Date nascimento;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", plano=" + plano + ", titular=" + titular + ", nascimento=" + nascimento + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

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
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }   
}
