package com.springboot.user2.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Column(name = "pin")
    private String pin;

    @Column(name = "dob", nullable = false, length = 50)
    private Date dob;

    @Column(name = "doj", nullable = false, length = 50)
    private Date doj;

    @Column(name="deleteflag",length = 2)
    private String deleteflag;


    public User() {}

    public User(String id, String firstname, String lastname ,String pin, Date dob, Date doj, String deleteflag ) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.doj = doj;
        this.pin = pin;
        this.deleteflag=deleteflag;
    }
}
