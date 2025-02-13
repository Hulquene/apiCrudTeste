package com.kenny.apiCrud.controller;

import com.kenny.apiCrud.domain.user.User;
import com.kenny.apiCrud.exception.UserNullException;
import com.kenny.apiCrud.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsersController {

    @Autowired
    private UsersService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User body) {
        if(body.getName()==null){
            throw new UserNullException();
        }
        User newUser = userService.createUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(user -> {
//                    userService.deleteUser(id);
//                    return ResponseEntity.noContent().build(); // Retorna HTTP 204 No Content
//                })
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(Map.of("message", "Usuário não encontrado"))); // Retorna erro 404
//    }


}
