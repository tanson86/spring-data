//package com.understanding.spring.data.spring_data.understanding.redis;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//
//@RestController
//@RequestMapping("/redis")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> createUser(@RequestBody User newUser) {
//        User user = userService.createUser(newUser);
//        return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: " + user.getId());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUser(@PathVariable Long id) {
//        return userService.getUser(id);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//        userService.updateUser(id, updatedUser);
//        return ResponseEntity.noContent().build(); // HTTP 204
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build(); // HTTP 204
//    }
//}