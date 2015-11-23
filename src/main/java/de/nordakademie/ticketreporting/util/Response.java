package de.nordakademie.ticketreporting.util;

/**
 * Response class for REST interface
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 22.11.2015
 */


public class Response {

    private boolean success;
    private String message;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        if (data != null) {
            this.data = data;
            this.setSuccess(true);
        }
    }
}
