package com.springboot.user2.controller;

import com.springboot.user2.entity.User;
import com.springboot.user2.payload.UserDto;
import com.springboot.user2.payload.UserResponse;
import com.springboot.user2.service.UserService;
import com.springboot.user2.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // create user rest api
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    // get all users rest api
    @GetMapping
    public UserResponse getAllUsers(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return userService.getAllUsers(pageNo, pageSize, sortBy, sortDir);

    }

    // get user by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(userService.getUserById(id));

    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public List<User> getUserProfile(@RequestParam("firstname") String firstName,@RequestParam("lastname") String lastname,@RequestParam("pin") String pin) {

        return userService.searchUser(firstName, lastname, pin);

    }

    @RequestMapping(value = "/getUserSortedDOJ", method = RequestMethod.GET)

    public List<User> getUserProfileSortedDOJ() {

        return userService.getUserSortedDOJ();

    }

    @RequestMapping(value = "/getUserSortedDOB", method = RequestMethod.GET)

    public List<User> getUserProfileSortedDOB() {

        return userService.getUserSortedDOB();

    }



    // update user by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable(name = "id") String id){
        UserDto userResponse = userService.updateUser(userDto, id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    // delete user rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") String id){
        userService.deleteUserById(id);

        return new ResponseEntity<>("User entity deleted successfully", HttpStatus.OK);

    }
  /*  @RequestMapping(value = "/users/firstname/{firstname}")
    public List<User> getUsersByFirstname(@PathVariable String firstname){
        return userService.getUsersByFirstname(firstname);
    } */

    @RequestMapping(method=RequestMethod.DELETE,path="/softDelete/{id}")
    public void softDeleteUser(@PathVariable String id)

    {

        Optional<User> user=userService.findUserById(id);
        if(user==null)
        {
            throw new ResourceAccessException("id"+id);
        }

        userService.softDeleteUser(id);
    }

}
