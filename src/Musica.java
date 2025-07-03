import java.util.UUID;

public class Musica extends ItemDeBiblioteca implements Compartilhavel, Exibivel { //EXTENDS = HERANCA \ IMPLEMENTS = INTERFACE
    private String autor;
    private double duracao;
    private boolean favorita;
    private Historico historico;
    private String linkCompartilhar;
    private String id;

    public Musica(String nome, String autor, double duracao, boolean favorita, Historico historico) {
        super(nome); // CHAMA O CONSTRUTOR DA SUPERCLASSE ITEMDEBIBLIOTECA E DEFINE O NOME DA PORRA DA MUSICA
        this.autor = autor;
        this.duracao = duracao;
        this.favorita = favorita;
        this.historico = historico;
        this.id = UUID.randomUUID().toString();
        historico.adicionarAoHistorico(nome);

        if (favorita) {
            PlaylistFavoritas.getPlaylist().adicionarMusica(this);
        }
    }

    // Getters e Setters
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public String getLinkCompartilhar() {
        return linkCompartilhar;
    }

    @Override
    public String gerarLinkCompartilhamento() {
        String urlNome = nome.toLowerCase().replaceAll(" ", "-");
        String idLimpo = id.replaceAll("-", "");
        this.linkCompartilhar = urlNome + "-" + idLimpo;
        return this.linkCompartilhar;
    }

    @Override
    public String exibirInformacoes() {
        return exibirDetalhes();
    }

    private String exibirDetalhes() {
        return "Nome: " + nome + ", Autor: " + autor + ", Duração: " + duracao + " min, Favorita: " + favorita;
    }
}
