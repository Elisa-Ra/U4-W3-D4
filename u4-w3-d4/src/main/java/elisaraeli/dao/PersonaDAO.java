package elisaraeli.dao;

import elisaraeli.entities.Persona;
import elisaraeli.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PersonaDAO {

    private final EntityManager entityManager;

    public PersonaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Persona newPersona) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(newPersona);

        transaction.commit();

        System.out.println("La persona " + newPersona.getNome() + " " + newPersona.getCognome() + " è stata correttamente salvata!");
    }

    public Persona findById(long personaId) {
        Persona found = entityManager.find(Persona.class, personaId);
        if (found == null) throw new NotFoundException(personaId);
        return found;
    }

    public void findByIdAndDelete(long personaId) {
        Persona found = this.findById(personaId);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("La persona con id: " + personaId + " è stata eliminata con successo!");
    }
}