package assessment.Model;

import java.util.List;

public class Canal {
    String tipo;
    int numeroCanal;
    String nomeCanal;
    private List<Plano> planos;

    public Canal() {
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
