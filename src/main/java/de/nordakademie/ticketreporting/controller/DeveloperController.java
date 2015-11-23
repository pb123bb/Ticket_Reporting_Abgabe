package de.nordakademie.ticketreporting.controller;

import de.nordakademie.ticketreporting.exceptions.DaoException;
import de.nordakademie.ticketreporting.service.DeveloperService;
import de.nordakademie.ticketreporting.util.Response;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.util.Map;

/**
 * REST-Controller class for ticket
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 07.11.2015
 */

@RestController
public class DeveloperController {

    /**
     *Injected service class
     */
    private DeveloperService developerService;


    /**
     * REST: checks if password and username are correct
     */
    @RequestMapping(value = "/developer/checkPassword", method = RequestMethod.POST)
    public Response checkPassword(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String username = body.get("username");
        String password = body.get("password");

        try{
            if (developerService.checkPasswordForDeveloper(username, password)) {
                response.setSuccess(true);
            } else {
                response.setMessage("Wrong credentials.");
                response.setSuccess(false);
            }
        } catch (Exception e) {
            response.setMessage("Wrong credentials.");
            response.setSuccess(false);

        } finally {
            return response;
        }
    }

    /**
     * REST: creates a new developer by name, username, email and password from call
     */
    @RequestMapping(value = "/developer/create", method = RequestMethod.PUT)
    public Response addDeveloper(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String name = body.get("name");
        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");


        try {
            developerService.saveNewDeveloper(name, username, email, password);

            response.setSuccess(true);

        } catch (DaoException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }

    /**
     * REST: deletes developer by its username
     */
    @RequestMapping(value = "/developer/delete", method = RequestMethod.POST)
    public Response deleteDeveloper(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String username = body.get("username");


        try {
            developerService.deleteDeveloper(username);

            response.setSuccess(true);

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }

    /**
     * REST: updates a developer by its username (update of newName, newPassword and newEmail)
     */
    @RequestMapping(value = "/developer/update", method = RequestMethod.POST)
    public Response updateDeveloper(@RequestBody Map<String, String> body) {
        Response response = new Response();

        String username = body.get("username");
        String newName = body.get("newName");
        String newPassword = body.get("newPassword");
        String newEmail = body.get("newEmail");


        try {
            if(!(newName.equals(""))){
                developerService.updateNameForDeveloper(username, newName);
            }

            if(!(newName.equals(""))) {
                developerService.updatePasswordForDeveloper(username, newPassword);
            }

            if(!(newName.equals(""))) {
                developerService.updateEmailForDeveloper(username, newEmail);
            }

            response.setSuccess(true);

        } catch (DaoException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }




        @Inject
        public void setDeveloperService(DeveloperService developerService) {
            this.developerService = developerService;
        }
    }








