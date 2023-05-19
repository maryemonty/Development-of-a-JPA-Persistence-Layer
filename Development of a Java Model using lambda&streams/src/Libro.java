import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro extends Catalogo {
    private String autore;
    private String genere;

    public Libro() {
    }

    public Libro(String isbn, String titolo, int annoPubblicazione, int numPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
