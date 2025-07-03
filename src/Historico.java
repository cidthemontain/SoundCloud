import java.util.ArrayList;

public class Historico {
    private ArrayList<String> historico;

    public Historico() {
        historico = new ArrayList<>();
    }

    public void adicionarAoHistorico(String nome) {
        historico.add(nome);
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }
}
