package de.nordakademie.ticketreporting.dao;

import de.nordakademie.ticketreporting.exceptions.DaoException;
import de.nordakademie.ticketreporting.exceptions.NoEntityFoundException;
import de.nordakademie.ticketreporting.model.Developer;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;

/**
 * DAO class for Developer entity
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 02.11.2015
 */
public class DeveloperDAO {

    /**
     * Entity manager
     */
    private EntityManager entityManager;

    @Transactional
    public void saveDeveloper(Developer developer) throws DaoException {
        try {
            entityManager.persist(developer);
        } catch (PersistenceException | ConstraintViolationException e) {
            throw new DaoException("There was a problem while saving: " + e.getMessage());
        }
    }

    public Developer findDeveloperByName(String name) {
        ArrayList<Developer> devList = (ArrayList<Developer>) entityManager.createQuery("SELECT developer " +
                "FROM Developer developer WHERE developer.name LIKE :name")
                .setParameter("name", name).getResultList();
        if (devList.size() > 0) {
            return devList.get(0);
        } else {
            return null;
        }
    }


    public Developer findDeveloperById(Integer id) throws NoEntityFoundException{
        Developer matchingDeveloper = entityManager.find(Developer.class, id);
        if (matchingDeveloper != null) {
            return matchingDeveloper;
        } else {
            throw new NoEntityFoundException("There was no delevoper with id " + id );
        }
    }


    public Developer findDeveloperByUsername(String username) {
        ArrayList<Developer> devList = (ArrayList<Developer>) entityManager.createQuery("SELECT developer " +
                "FROM Developer developer WHERE developer.userName LIKE :username")
                .setParameter("username", username).getResultList();

        if (devList.size() > 0) {
            return devList.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteDeveloper(String username) throws NoEntityFoundException {
        Developer matchingDeveloper = findDeveloperByUsername(username);
        if (matchingDeveloper != null) {
            entityManager.remove(matchingDeveloper);
        } else {
            throw new NoEntityFoundException("The developer wasn't found in database");
        }
    }


    public boolean checkPasswordForDeveloper(String username, String enteredPassword){


        Developer matchingDeveloper = findDeveloperByUsername(username);

        if (matchingDeveloper != null && matchingDeveloper.getPassword().equals(enteredPassword)) {
            return true;
        } else {
            return false;
        }


    };


    @Transactional
    public void updateEmailForDeveloper(String username, String newEmail) throws DaoException{

        Developer matchingDeveloper = findDeveloperByUsername(username);

        if(matchingDeveloper != null){
            matchingDeveloper.setEmail(newEmail);
        }
        else
        {
            throw new DaoException("Couldn't update E-Mail-Adress");
        }

    };


    @Transactional
    public void updatePasswordForDeveloper(String username, String newPassword) throws DaoException {

        Developer matchingDeveloper = findDeveloperByUsername(username);

        if(matchingDeveloper != null){
            matchingDeveloper.setPassword(newPassword);
        }
        else
        {
            throw new DaoException("Couldn't update password");
        }

    };

    @Transactional
    public void updateNameForDeveloper(String username, String newName) throws DaoException {

        Developer matchingDeveloper = findDeveloperByUsername(username);

        if(matchingDeveloper != null){
            matchingDeveloper.setName(newName);
        }
        else
        {
            throw new DaoException("Couldn't update name");
        }

    };


    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}