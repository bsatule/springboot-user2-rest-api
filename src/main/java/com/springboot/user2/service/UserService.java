package com.springboot.user2.service;

import com.springboot.user2.entity.User;
import com.springboot.user2.payload.UserDto;
import com.springboot.user2.payload.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);

    UserDto getUserById(String id);

    UserDto updateUser(UserDto userDto, String id);

    public List<User> searchUser(String firstname,String lastname,String pin);

    void deleteUserById(String id);

    List<User> getUserSortedDOJ();

    List<User> getUserSortedDOB();

    Optional<User> findUserById(String id);

    void softDeleteUser(String id);
}
