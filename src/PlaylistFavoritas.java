import java.util.ArrayList;


public class PlaylistFavoritas extends GerenciadorDeMusicas {
    private static PlaylistFavoritas instancia = new PlaylistFavoritas();

    private PlaylistFavoritas() {}

    public static PlaylistFavoritas getPlaylist() {
        return instancia;
    }

    @Override
    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    @Override
    public void removerMusica(Musica musica) {
        musicas.remove(musica);
    }

    @Override
    public void listarMusicas() {
        for (Musica m : musicas) {
            System.out.println(m.exibirInformacoes());
        }
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }
}
