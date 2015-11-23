package de.nordakademie.ticketreporting.exceptions;

/**
 * Exception for a non-existing entity
 * @author anft (Quelle)
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 04.11.2015
 */
public class NoEntityFoundException extends Exception {

    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 5316034896563232676L;

    /**
     * Default constructor.
     */
    public NoEntityFoundException() {
        super();
    }

    /**
     * Constructor with message.
     *
     * @param message The message.
     */
    public NoEntityFoundException(String message) {
        super(message);
    }
}

