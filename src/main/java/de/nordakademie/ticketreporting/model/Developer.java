package de.nordakademie.ticketreporting.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Developer model class
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 29.10.2015
 */


@Entity
@Table(name = "DEVELOPER")
public class Developer implements Serializable{

    /**
     * Name of developer, e.g. "John Doe"
     */
    private String name;
    /**
     * Unique username of developer
     */
    private String userName;
    /**
     * E-mail adress
     */
    private String email;
    /**
     * Password
     */
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    @Column(name="NAME", nullable = false)
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Column(name="USERNAME", nullable = false, unique=true)
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    @Column(name="EMAIL")
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Column(name="PASSWORD", nullable = false)
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
