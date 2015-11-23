package de.nordakademie.ticketreporting.dao;

import de.nordakademie.ticketreporting.exceptions.DaoException;
import de.nordakademie.ticketreporting.exceptions.NoEntityFoundException;
import de.nordakademie.ticketreporting.model.Developer;
import de.nordakademie.ticketreporting.model.Ticket;
import org.hibernate.Hibernate;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for ticket entity
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 02.11.2015
 */

public class TicketDAO {
    /**
     * Entity manager
     */
    private EntityManager entityManager;

    @Transactional
    public void saveTicket(Ticket ticket) throws DaoException {
        try {
            entityManager.persist(ticket);
        } catch (PersistenceException | ConstraintViolationException e) {
            throw new DaoException("There was a problem while saving: " + e.getMessage());
        }
    }



    public Ticket findTicketById(Integer id) throws NoEntityFoundException {
        Ticket matchtingTicket = entityManager.find(Ticket.class, id);
        if (matchtingTicket != null) {
            return matchtingTicket;
        } else {
            throw new NoEntityFoundException("There was no Ticket with id " + id );
        }
    }


    public Ticket findTicketByUsername(String username) {
        ArrayList<Ticket> tickList = (ArrayList<Ticket>) entityManager.createQuery("SELECT ticket " +
                "FROM Ticket ticket WHERE ticket.author LIKE :username")
                .setParameter("username", username).getResultList();

        if (tickList.size() > 0) {
            return tickList.get(0);
        } else {
            return null;
        }
    }



    public Ticket findTicketByHeadline(String headline) {
        ArrayList<Ticket> tickList = (ArrayList<Ticket>) entityManager.createQuery("SELECT ticket " +
                "FROM Ticket ticket WHERE ticket.headline LIKE :headline")
                .setParameter("headline", headline).getResultList();

        if (tickList.size() > 0) {
            return tickList.get(0);
        } else {
            return null;
        }
    }


    public List<Ticket> findAllTickets() {

        List<Ticket> ticketList = entityManager.createQuery("select ticket " +
                "from Ticket ticket").getResultList();
        for (Ticket ticket : ticketList) {
            Hibernate.initialize(ticket);
        }
        return ticketList;

    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
