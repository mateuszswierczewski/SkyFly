package pl.mswierczewski.skyfly.repositories;

import org.springframework.stereotype.Repository;
import pl.mswierczewski.skyfly.models.ContactDetails;
import pl.mswierczewski.skyfly.models.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PassengerRepository implements RepositoryCRUD<Passenger, Long> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Passenger getById(Long id) {
        return entityManager.find(Passenger.class, id);
    }

    @Override
    public boolean save(Passenger passenger) {
        ContactDetails contactDetails = passenger.getContactDetails();
        if(contactDetails != null && contactDetails.getId() == null){
            entityManager.persist(contactDetails);
        }
        entityManager.persist(passenger);
        return true;
    }

    @Override
    public boolean update(Passenger object) {
        return false;
    }

    @Override
    public boolean delete(Passenger object) {
        return false;
    }
}
