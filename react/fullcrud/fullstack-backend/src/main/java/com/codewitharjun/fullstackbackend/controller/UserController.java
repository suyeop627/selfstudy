package com.codewitharjun.fullstackbackend.controller;

import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.User;
import com.codewitharjun.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/user")
  User newUser(@RequestBody User newUser) {
    return userRepository.save(newUser);
  }

  @GetMapping("/users")
  Iterable<User> getAllUsers() {
    System.out.println("UserController.getAllUsers");
    return userRepository.findAll();
  }

  @GetMapping("/user/{id}")
  User getUserById(@PathVariable Long id) {
    System.out.println("UserController.getUserById");
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  @PutMapping("/user/{id}")
    //rest api에서 업데이트 할때 사용
  User updateUser(@RequestBody User newUser, @PathVariable Long id) {
    System.out.println("UserController.updateUser");
    return userRepository.findById(id).map(user -> {
      user.setName(newUser.getName());
      user.setUsername(newUser.getUsername());
      user.setEmail(newUser.getEmail());
      return userRepository.save(user);
    }).orElseThrow(() -> new UserNotFoundException(id));
  }
    @DeleteMapping("/user/{id}")
    //rest api에서 삭제할 때 사용
    String deleteUser (@PathVariable Long id){
      System.out.println("UserController.deleteUser");
      if (!userRepository.existsById(id)) {
        throw new UserNotFoundException(id);
      }
      userRepository.deleteById(id);
      return "User with id " + id + " has been deleted success.";
    }
  }

