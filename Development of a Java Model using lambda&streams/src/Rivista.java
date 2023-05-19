import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "rivista")
public class Rivista extends Catalogo {
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Rivista() {

    }

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
