package za.ac.cput.controller.admin;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.impl.UserServiceImpl;
import za.ac.cput.service.IUserService;
import za.ac.cput.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3315")
@RestController
@RequestMapping("api/admin/users")
public class AdminUserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/list/all")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.read(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    public User register(@RequestBody User registrationRequest) {
        // Extract the registration details from the request object
        String username = registrationRequest.getUserName();
        String password = registrationRequest.getPassword();
        String email = registrationRequest.getEmail();
        System.out.println("Username: " + username);
        System.out.println("This user is now registered");
        User createdUser = userService.create(registrationRequest);
        // Implement the logic to handle user registration
        // Retrieve the user details from the request and process it
        // Return an appropriate response, such as a success message or error message
        return createdUser;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User existingUser = userService.read(id);

        if (existingUser != null) {
            // Update the user fields
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            // Save the updated user
            existingUser = userService.update(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            // User not found
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        boolean deleted = userService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


































/*@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    *//* @Autowired
     private UserServiceImpl userService;*//*
    @Autowired
    private UserService userService;

    @GetMapping("/list/all")
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>(userService.getAll());
        return users;
    }

    @RequestMapping("/list/{argument}")
    public ArrayList<User> getAllByArgument(@PathVariable String argument) {
        ArrayList<User> users = new ArrayList<>(userService.getAll());
        users.removeIf(user -> !user.toString().toLowerCase().contains(argument.toLowerCase()));
        return users;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        User createdUser = userService.create(user);
        return createdUser;
    }
    @GetMapping("/read/{userId}")
    public User readUser(@PathVariable Integer userId) {
        System.out.println("/api/admin/users/read was triggered");
        System.out.println("UserService was created...attempting to read user...");
        User readUser = userService.read(userId);
        return readUser;
    }
    @PutMapping("/update/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User updatedUser) {
        System.out.println("/api/admin/users/update was triggered");
        System.out.println("UserService was created...attempting to update user...");
        User userToUpdate = userService.update(updatedUser);
        return userToUpdate;
    }
    @DeleteMapping("/delete/{userId}")
    public boolean deleteUser(@PathVariable Integer userId) {
        System.out.println("/api/admin/users/delete was triggered");
        System.out.println("UserService was created...attempting to delete user...");
        return userService.delete(userId);
    }
}*/
