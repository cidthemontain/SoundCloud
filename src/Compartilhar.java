import java.io.FileWriter;
import java.io.IOException;

public class Compartilhar {
    private static final String ARQUIVO_LINKS = "Musicas_salvas.csv";

    public void salvarLink(String link) {
        try (FileWriter writer = new FileWriter(ARQUIVO_LINKS, true)) {
            writer.append(link).append("\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar link: " + e.getMessage());
        }
    }
}
