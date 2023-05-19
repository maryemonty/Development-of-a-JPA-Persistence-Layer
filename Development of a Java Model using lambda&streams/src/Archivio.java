import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Archivio {
    private Map<String, Catalogo> archivio;
    private EntityManagerFactory entityManagerFactory;

    public Archivio() {
        this.archivio = new HashMap<>();
        this.entityManagerFactory = Persistence.createEntityManagerFactory("archivio");
    }

    public void aggiungi(Catalogo nuovoElemento) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Catalogo elementoEsistente = entityManager.find(Catalogo.class, nuovoElemento.getIsbn());
            if (elementoEsistente != null) {
                System.out.println("Un elemento con l'ISBN " + nuovoElemento.getIsbn() + " esiste già.");
                return;
            }

            transaction.begin();
            entityManager.persist(nuovoElemento);
            transaction.commit();
            archivio.put(nuovoElemento.getIsbn(), nuovoElemento);
            System.out.println("Elemento aggiunto in archivio. ISBN: " + nuovoElemento.getIsbn() + " - Anno Pubblicazione: " + nuovoElemento.getAnnoPubblicazione());
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void caricaCatalogo() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Catalogo> query = entityManager.createQuery("SELECT c FROM Catalogo c", Catalogo.class);
            List<Catalogo> catalogo = query.getResultList();
            archivio.clear();
            catalogo.forEach(elem -> archivio.put(elem.getIsbn(), elem));
            System.out.println("Catalogo caricato correttamente. Numero di elementi: " + catalogo.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Libro> autore(String autore) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Libro> query = entityManager.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
            query.setParameter("autore", autore);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void salvaCatalogo() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            archivio.values().forEach(entityManager::persist);
            transaction.commit();
            System.out.println("Catalogo salvato correttamente. Numero di elementi: " + archivio.size());
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}