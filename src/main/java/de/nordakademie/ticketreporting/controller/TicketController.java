package de.nordakademie.ticketreporting.controller;

import de.nordakademie.ticketreporting.exceptions.DaoException;
import de.nordakademie.ticketreporting.exceptions.NoEntityFoundException;
import de.nordakademie.ticketreporting.model.Ticket;
import de.nordakademie.ticketreporting.util.Response;
import de.nordakademie.ticketreporting.service.TicketService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * REST-Controller class for ticket
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 07.11.2015
 */
@RestController
public class TicketController {

    /**
     * Injected service class
     */
    private TicketService ticketService;

    @Inject
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    /**
     * REST: creates a new ticket by username, description and headline from call
     */
    @RequestMapping(value = "/createTicket", method = RequestMethod.POST)
    public Response addTicket(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String username = body.get("username");
        String description = body.get("description");
        String headline = body.get("headline");


        try {
            ticketService.addNewTicket(username, description, headline);

            response.setSuccess(true);

        } catch (DaoException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }


    /**
     * REST: puts a ticket (by ticketId) into IN_PROGRESS (with developer.username and an annotation
     */
    @RequestMapping(value = "/ticket/putProgress", method = RequestMethod.POST)
    public Response putProgress(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String username = body.get("username");
        String ticketId = body.get("ticketId");
        String annotation = body.get("annotation");


        try {
            ticketService.putInProgress(username, ticketId, annotation);

            response.setSuccess(true);

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }


    /**
     * REST: puts a ticket (by ticketId) into FIXED (with developer.username and an annotation
     */
    @RequestMapping(value = "/ticket/putFixed", method = RequestMethod.POST)
    public Response putFixed(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String username = body.get("username");
        String ticketId = body.get("ticketId");
        String annotation = body.get("annotation");


        try {
            ticketService.putInFixed(username, ticketId, annotation);

            response.setSuccess(true);

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }


    /**
     * REST: puts a ticket (by ticketId) into REOPENED (with developer.username and an annotation
     */
    @RequestMapping(value = "/ticket/putReopened", method = RequestMethod.POST)
    public Response putReopened(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String username = body.get("username");
        String ticketId = body.get("ticketId");
        String annotation = body.get("annotation");


        try {
            ticketService.putInReopened(username, ticketId, annotation);

            response.setSuccess(true);

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }

    /**
     * REST: puts a ticket (by ticketId) into CLOSED (with developer.username and an annotation
     */
    @RequestMapping(value = "/ticket/putClosed", method = RequestMethod.POST)
    public Response putClosed(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String username = body.get("username");
        String ticketId = body.get("ticketId");
        String annotation = body.get("annotation");


        try {
            ticketService.putInClosed(username, ticketId, annotation);

            response.setSuccess(true);

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }


    /**
     * REST: gives all tickets in database
     */
    @RequestMapping(value = "/allTickets", method = RequestMethod.PUT)
    public List<Ticket> findAllTickets() {

        try {
            return ticketService.findAllTickets();
        } catch (NoEntityFoundException e) {
            e.printStackTrace();
        }

        return null;

    };




}
