package de.nordakademie.ticketreporting.service;

import de.nordakademie.ticketreporting.exceptions.DaoException;
import de.nordakademie.ticketreporting.exceptions.NoEntityFoundException;
import de.nordakademie.ticketreporting.model.Developer;
import de.nordakademie.ticketreporting.model.Ticket;

import java.util.List;

/**
 * Interface for class with ticket services
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 02.11.2015
 */

public interface TicketService {


    /**
     * Creates a new ticket.
     */
    void addNewTicket(String author, String description, String headline) throws DaoException;

    /**
     * Puts a ticket (by ticketId) in the State IN_PROGRESS.
     */
    void putInProgress(String username, String id, String annotation) throws NoEntityFoundException;
    /**
     * Puts a ticket (by ticketId) in the State FIXED.
     */
    void putInFixed(String username, String id, String annotation) throws NoEntityFoundException;
    /**
     * Puts a ticket (by ticketId) in the State REOPENED.
     */
    void putInReopened(String username, String id, String annotation) throws NoEntityFoundException;
    /**
     * Puts a ticket (by ticketId) in the State CLOSED.
     */
    void putInClosed(String username, String id, String annotation) throws NoEntityFoundException;

    /**
     * Gives a ticket by its id.
     */
    Ticket findTicketByTicketID(String id) throws NoEntityFoundException;
    /**
     * Gives a ticket by its author.
     */
    Ticket findTicketByAuthor(String username) throws NoEntityFoundException;
    /**
     * Gives a ticket by its headline.
     */
    Ticket findTicketByHeadline(String headline) throws NoEntityFoundException;

    /**
     * Checks if a ticket (by ticketID) can be put IN_PROGESS
     */
    boolean checkForInProgress(String id) throws NoEntityFoundException;
    /**
     * Checks if a ticket (by ticketID) can be put REJECTED or FIXED
     */
    boolean checkForFixedOrRejected(String specialist, String id) throws NoEntityFoundException;

    /**
     * Checks if a ticket (by ticketID) can be put CLOSED or REOPENED
     */
    boolean checkForClosedOrReopened(String autor, String id) throws NoEntityFoundException;
    /**
     * Checks if a ticket (by ticketID) is editable
     */
    boolean checkForEditable(String id) throws NoEntityFoundException;

    /**
     * Gives a List of all tickets in database
     */
    List<Ticket> findAllTickets() throws NoEntityFoundException;
}
