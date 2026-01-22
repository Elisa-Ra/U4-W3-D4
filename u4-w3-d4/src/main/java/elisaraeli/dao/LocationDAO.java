package elisaraeli.dao;

import elisaraeli.entities.Location;
import elisaraeli.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LocationDAO {

    private final EntityManager entityManager;

    public LocationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Location newLocation) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(newLocation);

        transaction.commit();

        System.out.println("La location \"" + newLocation.getNome() + "\" è stata correttamente salvata!");
    }

    public Location findById(long locationId) {
        Location found = entityManager.find(Location.class, locationId);
        if (found == null) throw new NotFoundException(locationId);
        return found;
    }

    public void findByIdAndDelete(long locationId) {
        Location found = this.findById(locationId);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("La location con id: " + locationId + " è stata eliminata con successo!");
    }
}