package de.nordakademie.ticketreporting.service;

import de.nordakademie.ticketreporting.exceptions.DaoException;
import de.nordakademie.ticketreporting.exceptions.NoEntityFoundException;
import de.nordakademie.ticketreporting.model.Developer;

/**
 * Interface for class with developer services
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 02.11.2015
 */

public interface DeveloperService {


    /**
     * Saves new developer.
     */
    void saveNewDeveloper(String name, String username, String email, String password) throws DaoException;
    /**
     * Deletes a developer.
     */
    void deleteDeveloper(String username) throws NoEntityFoundException;

    /**
     * Update the password.
     */
    void updatePasswordForDeveloper(String username, String newPassword) throws DaoException;
    /**
     * Update the email.
     */
    void updateEmailForDeveloper(String username, String newEmail) throws DaoException;
    /**
     * Update the name.
     */
    void updateNameForDeveloper(String username, String newName) throws DaoException;

    /**
     * Checks username and password for authorization.
     */
    boolean checkPasswordForDeveloper(String username, String password);


}
