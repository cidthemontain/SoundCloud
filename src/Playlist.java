import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {

        musicas.add(musica);

    }

    public void adicionarMusica(String nome, String autor, double duracao, boolean favorita, Historico historico) {
        Musica nova = new Musica(nome, autor, duracao, favorita, historico);
        adicionarMusica(nova);
    }

    public void removerMusica(Musica musica) {
        musicas.remove(musica);
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public String getNome() {
        return nome;
    }

    public String[][] getMatrizMusicas() {
        String[][] matriz = new String[4][2];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (index < musicas.size()) {
                    matriz[i][j] = musicas.get(index).getNome();
                } else {
                    matriz[i][j] = "-----";
                }
                index++;
            }
        }
        return matriz;
    }
}
