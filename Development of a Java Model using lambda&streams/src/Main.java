import java.util.List;
import org.apache.log4j.BasicConfigurator;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Archivio catalogo = new Archivio();

        Libro libro = new Libro("9781416554998", "The Secret", 2006, 226, "Rhonda Byrne", "Letterario");
        Libro libro2 = new Libro("22222", "Sono troppo forte", 2006, 226, "uaaaa", "sono sul db");
        Libro libro3 = new Libro("1", "Libro uscito nel 2024 perché", 2024, 226, "sta troppo", "avanti");
        Rivista rivista = new Rivista("11112", "Cioè", 1980, 30, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("2023", "scritto su Java, letto sul db", 1980, 1000, Periodicita.SETTIMANALE);

        catalogo.aggiungi(libro);
        catalogo.aggiungi(libro2);
        catalogo.aggiungi(libro3);
        catalogo.aggiungi(rivista);
        catalogo.aggiungi(rivista2);

        catalogo.salvaCatalogo();

        catalogo.caricaCatalogo();

        List<Libro> ricercaAutore = catalogo.autore("Rhonda Byrne");

        ricercaAutore.forEach(elem -> System.out.println("Titolo: " + elem.getTitolo()));
    }
}