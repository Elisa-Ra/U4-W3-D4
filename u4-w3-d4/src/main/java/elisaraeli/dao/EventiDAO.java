package elisaraeli.dao;

import elisaraeli.entities.Concerto;
import elisaraeli.entities.Evento;
import elisaraeli.entities.GenereConcerto;
import elisaraeli.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EventiDAO {
    // DAO (Data Access Object) è un Design Pattern -> classe speciale che ci serve per interagire con il DB.
    // L'EntityManager semplifica l'uso delle operazioni CRUD (Create, Read, Update, Delete).

    private final EntityManager entityManager;

    public EventiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Evento newEvent) {
        // 1. Chiediamo all'EntityManager di creare una nuova transazione
        EntityTransaction transaction = entityManager.getTransaction();

        // 2. Facciamo partire la transazione
        transaction.begin();

        // 3. Aggiungiamo il newEvento al PersistenceContext, in quanto esso è nuovo e non ancora MANAGED.
        // Si fa con il metodo .persist() dell'EntityManager (in questo punto l'oggetto non è ancora parte del DB)
        entityManager.persist(newEvent);

        // 4. Il COMMIT rende effettiva l'operazione, (newEvento diventa una nuova riga nel DB)
        transaction.commit();

        // 5. Log di avvenuto salvataggio
        System.out.println("L'evento" + newEvent.getTitolo() + " è stato correttamente salvato!");

    }

    public Evento findById(long eventoId) {
        Evento found = entityManager.find(Evento.class, eventoId);
        if (found == null) throw new NotFoundException(eventoId);
        return found;
    }

    public void findByIdAndDelete(long eventoId) {
        // 1. Cerco l'evento tramite l'id nel DB
        Evento found = this.findById(eventoId);

        // 2. L'EntityManager crea una nuova transazione
        EntityTransaction transaction = entityManager.getTransaction();

        // 3. Faccio partire la transazione
        transaction.begin();

        // 4. Rimuoviamo dal Persistence Context l'oggetto in questione tramite metodo .remove() dell'EntityManager
        // (l'oggetto non è ancora eliminato effettivamente dal DB)
        entityManager.remove(found);

        // 5. COMMIT per rendere effettiva l'operazione, (l'oggetto viene cancellato dal DB).
        transaction.commit();

        // 6. Log di avvenuta cancellazione
        System.out.println("L'evento con id: " + eventoId + " è stato eliminato con successo!");
    }

    public List<Concerto> getConcertiInStreaming(boolean streaming) {
        TypedQuery<Concerto> query = entityManager.createQuery(
                "SELECT c FROM Concerto c WHERE c.inStreaming = :streaming",
                Concerto.class
        );
        query.setParameter("streaming", streaming);
        return query.getResultList();
    }

    public List<Concerto> getConcertiPerGenere(GenereConcerto genere) {
        TypedQuery<Concerto> query = entityManager.createQuery(
                "SELECT c FROM Concerto c WHERE c.genere = :genere",
                Concerto.class
        );
        query.setParameter("genere", genere);
        return query.getResultList();
    }

}