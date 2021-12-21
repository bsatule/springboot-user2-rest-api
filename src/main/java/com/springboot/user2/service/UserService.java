package com.springboot.user2.service;

import com.springboot.user2.payload.UserDto;
import com.springboot.user2.payload.UserResponse;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);

    UserDto getUserById(long id);

    UserDto updateUser(UserDto userDto, long id);

    void deleteUserById(long id);
}
