package com.example.usersmanagementsoftware.Controller;

import com.example.usersmanagementsoftware.ApiResponse.ApiResponse;
import com.example.usersmanagementsoftware.Model.User;
import com.example.usersmanagementsoftware.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    // 1. Declare a dependency for UserService using Dependency Injection
    private final UserService userService;

    // 2. CRUD
    // 2.1 Get
    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    // 2.2 Post
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("New User Added."));
    }

    // 2.3 Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User Updated."));
    }

    // 2.4 Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User Deleted."));
    }

    // 3. Extra endpoints
    // 3.1 Login
    @GetMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username, @PathVariable String password) {
        userService.login(username, password);
        return ResponseEntity.status(200).body(new ApiResponse("Login Completed."));
    }

    // 3.2 getUserByEmail
    @GetMapping("/get/user-email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    // 3.3 getAllUsersByRole
    @GetMapping("/get/users-role/{role}")
    public ResponseEntity getAllUsersByRole(@PathVariable String role) {
        return ResponseEntity.status(200).body(userService.getAllUsersByRole(role));
    }

    // 3.4 getAllUsersWithSpecificAgeOrAbove
    @GetMapping("/get/users-age/{age}")
    public ResponseEntity getAllUsersWithSpecificAgeOrAbove(@PathVariable Integer age) {
        return ResponseEntity.status(200).body(userService.getAllUsersWithSpecificAgeOrAbove(age));
    }
}
