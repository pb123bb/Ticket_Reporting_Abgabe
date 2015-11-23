package de.nordakademie.ticketreporting.exceptions;

/**
 * DAO exception for problems within a DAO
 * @author anft (Quelle)
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 04.11.2015
 */

public class DaoException extends Exception {


    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = -1666797491228308498L;

    /**
     * Default constructor.
     */
    public DaoException() {
        super();
    }

    /**
     * Constructor with message.
     *
     * @param message The message.
     */
    public DaoException(String message) {
        super(message);
    }
}

