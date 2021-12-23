package com.springboot.user2.service.impl;

import com.springboot.user2.entity.User;
import com.springboot.user2.exception.ResourceNotFoundException;
import com.springboot.user2.payload.UserDto;
import com.springboot.user2.payload.UserResponse;
import com.springboot.user2.repository.UserRepository;
import com.springboot.user2.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // convert DTO to entity

        User user = mapToEntity(userDto);

        User newUser = userRepository.save(user);

        // convert entity to dto
        UserDto userResponse = mapToDto(newUser);

        return userResponse;
    }

    @Override
    public UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<User> users = userRepository.findAll(pageable);

        // get content from page object
        List<User> listOfUsers = users.getContent();
        List<UserDto> content = listOfUsers.stream().map(user -> mapToDto(user)).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(content);
        userResponse.setPageNo(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLast(users.isLast());

        return userResponse;
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return mapToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String id) {
        // get post by id from the database
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPin(userDto.getPin());
        user.setDob(userDto.getDob());
        user.setDoj(userDto.getDoj());

        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public List<User> searchUser(String firstName, String surName, String pinCode) {
        List<User> user=userRepository.findByFirstNameOrLastNameOrPin(firstName, surName,pinCode);
        return user;
    }

    @Override
    public void deleteUserById(String id) {
        // get post by id from the database
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);

    }

    @Override
    public List<User> getUserSortedDOJ() {
        List<User> user=userRepository.findByOrderByDateOfJoiningAsc();
        return user;
    }

    @Override
    public List<User> getUserSortedDOB() {
        List<User> user=userRepository.findByOrderByDateOfBirthAsc();
        return user;
    }

    @Override
    public Optional<User> findUserById(String id) {
        return Optional.empty();
    }

    @Override
    public void softDeleteUser(String id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent())
        {
            user.get().setDeleteflag("Y");
            userRepository.save(user.get());
        }

    }

    // convert entity into dto
    private UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setPin(user.getPin());
        userDto.setDob(user.getDob());
        userDto.setDoj(user.getDoj());

        return userDto;

    }
    // convert dto to entity
    private User mapToEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setFirstname(userDto.getLastname());
        user.setPin(userDto.getPin());
        user.setDob(userDto.getDob());
        user.setDoj(userDto.getDoj());

        return user;

    }
}
