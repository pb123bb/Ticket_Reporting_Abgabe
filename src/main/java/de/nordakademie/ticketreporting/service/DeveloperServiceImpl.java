package de.nordakademie.ticketreporting.service;

import de.nordakademie.ticketreporting.dao.DeveloperDAO;
import de.nordakademie.ticketreporting.exceptions.DaoException;
import de.nordakademie.ticketreporting.exceptions.NoEntityFoundException;
import de.nordakademie.ticketreporting.model.Developer;

import javax.inject.Inject;

/**
 * Class with developer services
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 04.11.2015
 */

public class DeveloperServiceImpl implements DeveloperService{

    /**
     * Injected DAO class
     */
    private DeveloperDAO developerDAO;

    @Inject
    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }



    @Override
    public void saveNewDeveloper(String name, String username, String email, String password) throws DaoException {

        Developer newDeveloper = new Developer();
        newDeveloper.setName(name);
        newDeveloper.setUserName(username);
        newDeveloper.setEmail(email);
        newDeveloper.setPassword(password);
        developerDAO.saveDeveloper(newDeveloper);

    }

    @Override
    public void deleteDeveloper(String username) throws NoEntityFoundException {
        developerDAO.deleteDeveloper(username);
    }





    @Override
    public void updatePasswordForDeveloper(String username, String newPassword) throws DaoException {
        developerDAO.updatePasswordForDeveloper(username, newPassword);

    }

    @Override
    public void updateEmailForDeveloper(String username, String newEmail) throws DaoException {
        developerDAO.updateEmailForDeveloper(username, newEmail);
    }

    @Override
    public void updateNameForDeveloper(String username, String newName) throws DaoException {
        developerDAO.updateNameForDeveloper(username, newName);
    }




    @Override
    public boolean checkPasswordForDeveloper(String username, String password) {
        return developerDAO.checkPasswordForDeveloper(username, password);
    };


}
