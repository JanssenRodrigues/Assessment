package assessment.Model;

import java.util.List;

public class Canal {
    private String nome;
    private String tipo;
    private int id;
    private int numeroCanal;
    private String nomeCanal;
    private List<Plano> planos;

    public Canal() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }
    
    public Canal(String tipo, int numeroCanal, String nomeCanal) {
        this.tipo = tipo;
        this.numeroCanal = numeroCanal;
        this.nomeCanal = nomeCanal;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroCanal() {
        return numeroCanal;
    }

    public void setNumeroCanal(int numeroCanal) {
        this.numeroCanal = numeroCanal;
    }

    public String getNomeCanal() {
        return nomeCanal;
    }

    public void setNomeCanal(String nomeCanal) {
        this.nomeCanal = nomeCanal;
    }
  
}
