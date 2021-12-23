package com.springboot.user2.payload;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String id;
    private String firstname;
    private String lastname;
    private String pin;
    private Date dob;
    private Date doj;
    private String deleteflag;

}
