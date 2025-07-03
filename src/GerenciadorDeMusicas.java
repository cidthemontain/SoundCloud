import java.util.ArrayList;

public abstract class GerenciadorDeMusicas {
    protected ArrayList<Musica> musicas = new ArrayList<>();

    public abstract void adicionarMusica(Musica musica);
    public abstract void removerMusica(Musica musica);
    public abstract void listarMusicas();
}
 