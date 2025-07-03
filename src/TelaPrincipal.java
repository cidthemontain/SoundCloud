    import javax.swing.*;
    import java.awt.*;
    import java.util.ArrayList;

    public class TelaPrincipal extends JFrame {
        private Historico historico = new Historico();
        private ArrayList<Playlist> playlists = new ArrayList<>();

        public static void main(String[] args) {
            new TelaPrincipal();
        }

        public TelaPrincipal() {
            super("SoundCloud 2");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 600);
            setLayout(new GridLayout(7, 1, 10, 10));

            JButton btnAdicionarMusica = new JButton("Adicionar Música");
            JButton btnExibirFavoritas = new JButton("Exibir Favoritas");
            JButton btnExibirHistorico = new JButton("Exibir Histórico");
            JButton btnGerarLink = new JButton("Gerar Link Compartilhamento");
            JButton btnEditarMusica = new JButton("Editar Música Favorita");
            JButton btnExcluirMusica = new JButton("Excluir Música Favorita");
            JButton btnExibirPlaylists = new JButton("Suas playlists");

            btnAdicionarMusica.addActionListener(e -> adicionarMusica());
            btnExibirFavoritas.addActionListener(e -> exibirFavoritas());
            btnExibirHistorico.addActionListener(e -> exibirHistorico());
            btnGerarLink.addActionListener(e -> gerarLinkCompartilhamento());
            btnEditarMusica.addActionListener(e -> editarMusica());
            btnExcluirMusica.addActionListener(e -> excluirMusica());
            btnExibirPlaylists.addActionListener(e -> exibirPlaylists());

            add(btnAdicionarMusica);
            add(btnExibirFavoritas);
            add(btnExibirHistorico);
            add(btnGerarLink);
            add(btnEditarMusica);
            add(btnExcluirMusica);
            add(btnExibirPlaylists);

            setVisible(true);



            Playlist desespero = new Playlist("desespero");
            Playlist mudancas = new Playlist("mudanças?");

            playlists.add(desespero);
            playlists.add(mudancas);
        }

        private void adicionarMusica() {
            String nome = JOptionPane.showInputDialog("Nome da música:");
            String autor = JOptionPane.showInputDialog("Autor:");
            String duracaoStr = JOptionPane.showInputDialog("Duração (em minutos):");
            double duracao;
            try {
                duracao = Double.parseDouble(duracaoStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Duração inválida. Use apenas números.");
                return;
            }

            int favoritaOption = JOptionPane.showConfirmDialog(this, "É favorita?", "Favorita", JOptionPane.YES_NO_OPTION);
            boolean favorita = favoritaOption == JOptionPane.YES_OPTION;

            Musica musica = new Musica(nome, autor, duracao, favorita, historico);

            if (!playlists.isEmpty()) {
                int resposta = JOptionPane.showConfirmDialog(this, "Deseja adicionar a uma playlist?", "Adicionar", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    String[] nomes = playlists.stream().map(Playlist::getNome).toArray(String[]::new);
                    String escolha = (String) JOptionPane.showInputDialog(this, "Escolha a playlist:", "Playlists", JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]);
                    playlists.stream().filter(p -> p.getNome().equals(escolha)).findFirst().ifPresent(p -> p.adicionarMusica(musica));
                }
            }

            JOptionPane.showMessageDialog(this, "Música adicionada com sucesso!");
        }

        private void exibirFavoritas() {
            StringBuilder sb = new StringBuilder();
            for (Musica m : PlaylistFavoritas.getPlaylist().getMusicas()) {
                sb.append(m.exibirInformacoes()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhuma música favorita.");
        }

        private void exibirHistorico() {
            StringBuilder sb = new StringBuilder();
            for (String nome : historico.getHistorico()) {
                sb.append(nome).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Histórico vazio.");
        }

        private void gerarLinkCompartilhamento() {
            String nome = JOptionPane.showInputDialog("Digite o nome da música para gerar link:");
            for (Musica m : PlaylistFavoritas.getPlaylist().getMusicas()) {
                if (m.getNome().equalsIgnoreCase(nome)) {
                    m.gerarLinkCompartilhamento();
    
                    Compartilhar compartilhador = new Compartilhar();
                    compartilhador.salvarLink(m.getLinkCompartilhar());

                    JOptionPane.showMessageDialog(this, "Link gerado e salvo: " + m.getLinkCompartilhar());
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Música não encontrada nas favoritas.");
        }

        private void editarMusica() {
            String nome = JOptionPane.showInputDialog("Digite o nome da música para editar:");
            for (Musica m : PlaylistFavoritas.getPlaylist().getMusicas()) {
                if (m.getNome().equalsIgnoreCase(nome)) {
                    String novoNome = JOptionPane.showInputDialog("Novo nome:", m.getNome());
                    String novoAutor = JOptionPane.showInputDialog("Novo autor:", m.getAutor());
                    m.setNome(novoNome);
                    m.setAutor(novoAutor);
                    JOptionPane.showMessageDialog(this, "Música editada com sucesso!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Música não encontrada nas favoritas.");
        }

        private void excluirMusica() {
            String nome = JOptionPane.showInputDialog("Digite o nome da música para excluir:");
            Musica paraRemover = null;
            for (Musica m : PlaylistFavoritas.getPlaylist().getMusicas()) {
                if (m.getNome().equalsIgnoreCase(nome)) {
                    paraRemover = m;
                    break;
                }
            }
            if (paraRemover != null) {
                PlaylistFavoritas.getPlaylist().getMusicas().remove(paraRemover);
                JOptionPane.showMessageDialog(this, "Música removida das favoritas.");
            } else {
                JOptionPane.showMessageDialog(this, "Música não encontrada nas favoritas.");
            }
        }

        private void exibirPlaylists() {
            if (playlists.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma playlist cadastrada.");
                return;
            }
            JPanel panel = new JPanel(new GridLayout(playlists.size(), 1));
            for (Playlist p : playlists) {
                JButton btn = new JButton(p.getNome());
                btn.addActionListener(e -> {
                    String[][] matriz = p.getMatrizMusicas();
                    StringBuilder sb = new StringBuilder("Suas playlists:\n\n");
                    for (String[] linha : matriz) {
                        for (String musica : linha) {
                            sb.append(musica).append("\t");
                        }
                        sb.append("\n");
                    }
                    JOptionPane.showMessageDialog(this, sb.toString());
                });
                panel.add(btn);
            }
            JOptionPane.showMessageDialog(this, panel, "Playlists", JOptionPane.PLAIN_MESSAGE);
        }
    }
