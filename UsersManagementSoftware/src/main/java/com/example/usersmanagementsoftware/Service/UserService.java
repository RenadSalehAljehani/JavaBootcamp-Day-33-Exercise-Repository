package com.example.usersmanagementsoftware.Service;

import com.example.usersmanagementsoftware.ApiResponse.ApiException;
import com.example.usersmanagementsoftware.Model.User;
import com.example.usersmanagementsoftware.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    // 1. Declare a dependency for UserRepository using Dependency Injection
    private final UserRepository userRepository;

    // 2.CRUD
    // 2.1 Get
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 2.2 Post
    public void addUser(User user) {
        userRepository.save(user);
    }

    // 2.3 Update
    public void updateUser(Integer id, User user) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null) {
            throw new ApiException("User Not Found.");
        }
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());
        userRepository.save(oldUser);
    }

    // 2.4 Delete
    public void deleteUser(Integer id) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null) {
            throw new ApiException("User Not Found.");
        }
        userRepository.delete(oldUser);
    }

    // 3. Extra endpoints
    // 3.1 Endpoint to check if username and password are correct
    public void login(String username, String password) {
        User user = userRepository.checkUserAccount(username, password);
        if (user == null) {
            throw new ApiException("Username or Password Not Correct.");
        }
    }

    // 3.2 Endpoint to get user by email
    public User getUserByEmail(String email) {
        User user = userRepository.giveMeUserByEmail(email);
        if (user == null) {
            throw new ApiException("No User With Such Email Address.");
        }
        return user;
    }

    // 3.3 Endpoint to get all users with specific role
    public List<User> getAllUsersByRole(String role) {
        List<User> users = userRepository.findUsersByRole(role);
        if (users.isEmpty()) {
            throw new ApiException("No Users With '" + role + "' Role Has Been Found.");
        }
        return users;
    }

    // 3.4 Endpoint to get all users with specific age or above
    public List<User> getAllUsersWithSpecificAgeOrAbove(Integer age) {
        List<User> users = userRepository.giveMeUsersWithSpecificAgeOrAbove(age);
        if (users.isEmpty()) {
            throw new ApiException("No Users With Age (" + age + ") or Above Has Been Found.");
        }
        return users;
    }
}
