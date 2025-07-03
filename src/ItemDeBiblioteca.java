public abstract class ItemDeBiblioteca {
    protected String nome;

    public ItemDeBiblioteca(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract String exibirInformacoes();
}
