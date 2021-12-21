package com.springboot.user2.payload;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String firstname;
    private String lastname;
    private String pin;
    private String dob;
    private String doj;

}
