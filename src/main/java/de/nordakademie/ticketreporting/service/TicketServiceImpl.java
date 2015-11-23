package de.nordakademie.ticketreporting.service;

import de.nordakademie.ticketreporting.dao.TicketDAO;
import de.nordakademie.ticketreporting.exceptions.DaoException;
import de.nordakademie.ticketreporting.exceptions.NoEntityFoundException;
import de.nordakademie.ticketreporting.model.ChangeOfState;
import de.nordakademie.ticketreporting.model.State;
import de.nordakademie.ticketreporting.model.Ticket;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class with developer services
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 04.11.2015
 */

public class TicketServiceImpl implements TicketService{

    /**
     * Injected DAO class
     */
    private TicketDAO ticketDAO;

    @Override
    public void addNewTicket(String author, String description, String headline) throws DaoException {

        //Sets params for the Ticket
        Ticket ticket = new Ticket();
        ticket.setAuthor(author);
        ticket.setCreated(new Date());
        ticket.setDescription(description);
        ticket.setHeadline(headline);
        ticket.setEditable(true);

        //Creates an initial Change of State
        ChangeOfState initialChangeOfState = new ChangeOfState();
            initialChangeOfState.setAnnotation("Opened");
            initialChangeOfState.setChanged(new Date());
            initialChangeOfState.setName(State.OPENED);
            initialChangeOfState.setSpecialist(ticket.getAuthor());

        //Creates the ChangeOfStateList
        List initialChangeOfStateList = new ArrayList<ChangeOfState>();
        initialChangeOfStateList.add(initialChangeOfState);

        ticket.setListOfStates(initialChangeOfStateList);

        ticketDAO.saveTicket(ticket);


    }

    @Override
    public void putInProgress(String username, String id, String annotation) throws NoEntityFoundException {

        Ticket ticket = ticketDAO.findTicketById(Integer.parseInt(id));
        ChangeOfState cos = new ChangeOfState();
        cos.setAnnotation(annotation);
        cos.setName(State.IN_PROGRESS);
        cos.setSpecialist(username);
        cos.setChanged(new Date());
        List<ChangeOfState> cosList = ticket.getListOfStates();
        cosList.add(cos);
    }

    @Override
    public void putInFixed(String username, String id, String annotation) throws NoEntityFoundException {

        Ticket ticket = ticketDAO.findTicketById(Integer.parseInt(id));
        ChangeOfState cos = new ChangeOfState();
        cos.setAnnotation(annotation);
        cos.setName(State.REOPENED);
        cos.setSpecialist(username);
        cos.setChanged(new Date());
        List<ChangeOfState> cosList = ticket.getListOfStates();
        cosList.add(cos);
    }

    @Override
    public void putInReopened(String username, String id, String annotation) throws NoEntityFoundException {

        Ticket ticket = ticketDAO.findTicketById(Integer.parseInt(id));
        ChangeOfState cos = new ChangeOfState();
        cos.setAnnotation(annotation);
        cos.setName(State.REOPENED);
        cos.setSpecialist(username);
        cos.setChanged(new Date());
        List<ChangeOfState> cosList = ticket.getListOfStates();
        cosList.add(cos);
    }

    @Override
    public void putInClosed(String username, String id, String annotation) throws NoEntityFoundException {

        Ticket ticket = ticketDAO.findTicketById(Integer.parseInt(id));
        ChangeOfState cos = new ChangeOfState();
        cos.setAnnotation(annotation);
        cos.setName(State.REOPENED);
        cos.setSpecialist(username);
        cos.setChanged(new Date());
        List<ChangeOfState> cosList = ticket.getListOfStates();
        cosList.add(cos);
        ticket.setEditable(false);
    }


    @Override
    public Ticket findTicketByTicketID(String id) throws NoEntityFoundException {
        Ticket matchedTicket = ticketDAO.findTicketById(Integer.parseInt(id));

        if(matchedTicket != null){
            return matchedTicket;
        }
        else{
            throw new NoEntityFoundException("No entity found");
        }
    }

    @Override
    public Ticket findTicketByAuthor(String username) throws NoEntityFoundException {
        Ticket matchedTicket = ticketDAO.findTicketByUsername(username);

        if(matchedTicket != null){
            return matchedTicket;
        }
        else{
            throw new NoEntityFoundException("No entity found");
        }
    }

    @Override
    public Ticket findTicketByHeadline(String headline) throws NoEntityFoundException {
        Ticket matchedTicket =  ticketDAO.findTicketByHeadline(headline);

        if(matchedTicket != null){
            return matchedTicket;
        }
        else{
            throw new NoEntityFoundException("No entity found");
        }


    }

    @Override
    public boolean checkForInProgress(String id) throws NoEntityFoundException {

        Ticket matchedTicket = ticketDAO.findTicketById(Integer.parseInt(id));

        if(matchedTicket != null){
            List<ChangeOfState> cosList = matchedTicket.getListOfStates();
                if(cosList.get(cosList.size()).getName().equals(State.OPENED) ||
                        cosList.get(cosList.size()).getName().equals(State.REOPENED)){
                    return true;
                }
                else{
                    return false;
                }

        }

        return false;
    }

    @Override
    public boolean checkForFixedOrRejected(String specialist, String id) throws NoEntityFoundException {

        Ticket matchedTicket = ticketDAO.findTicketById(Integer.parseInt(id));

        if (matchedTicket != null){

            List<ChangeOfState> cosList = matchedTicket.getListOfStates();
            ChangeOfState newestChangeEntry = cosList.get(cosList.size());

            if (newestChangeEntry.getName().equals(State.IN_PROGRESS) &&
                    newestChangeEntry.getSpecialist().equals(specialist)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkForClosedOrReopened(String author, String id) throws NoEntityFoundException {

        Ticket matchedTicket = ticketDAO.findTicketById(Integer.parseInt(id));

        if (matchedTicket != null){

            List<ChangeOfState> cosList = matchedTicket.getListOfStates();
            ChangeOfState newestChangeEntry = cosList.get(cosList.size());

            if ((newestChangeEntry.getName().equals(State.FIXED) || newestChangeEntry.getName().equals(State.REJECTED))
                    && matchedTicket.getAuthor().equals(author))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkForEditable(String id) throws NoEntityFoundException {

        try{
            return ticketDAO.findTicketById(Integer.parseInt(id)).getEditable();
        }
        catch (Exception e){
            return false;
        }
    }


    @Override
    public List<Ticket> findAllTickets() throws NoEntityFoundException {

        List<Ticket> ticketList = ticketDAO.findAllTickets();
        if (ticketList.size() > 0) {
            return ticketList;
        } else {
            throw new NoEntityFoundException("No Tickets could be found.");
        }
    }




    @Inject
    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

}
