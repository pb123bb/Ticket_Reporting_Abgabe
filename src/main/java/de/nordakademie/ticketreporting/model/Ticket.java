package de.nordakademie.ticketreporting.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Ticket model class
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 29.10.2015
 */

@Entity
@Table(name = "TICKET")
public class Ticket implements Serializable {

    /**
     * List with ChangeOfState objects indicating the history of the ticket
     */
    private List<ChangeOfState> listOfStates;
    /**
     * Headline
     */
    private String headline;
    /**
     * Description
     */
    private String description;
    /**
     * Auto-created ticketID
     */
    private Integer ticketID;
    /**
     * User name of author
     */
    private String author;
    /**
     * Date of create
     */
    private Date created;
    /**
     * Indicating whether ticket is editable, default = true
     */
    private Boolean editable;


    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getTicketID() {return ticketID;}
    public void setTicketID(Integer ticketID) {this.ticketID = ticketID;}

    @ElementCollection
    @Column
    public List<ChangeOfState> getListOfStates() {return listOfStates;}
    public void setListOfStates(List<ChangeOfState> listOfStates) {this.listOfStates = listOfStates;}

    @Basic
    @Column
    public String getHeadline() {return headline;}
    public void setHeadline(String headline) {this.headline = headline;}

    @Basic
    @Column
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    @Basic
    @Column(nullable = false)
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getCreated() {return created;}
    public void setCreated(Date created) {this.created = created;}

    @Basic
    @Column
    public Boolean getEditable() {return editable;}
    public void setEditable(Boolean editable) {this.editable = editable;}

}
