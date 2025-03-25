package com.example.controller;

import com.example.model.CharacterEntity;
import com.example.model.UserEntity;
import com.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> userEntityList = this.service.findAll();
        if (userEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userEntityList);
    }

    @GetMapping("/user/{id}")
    public Optional<UserEntity> findById(@PathVariable Long id) {
        return this.service.findById(id);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<UserEntity> findByUsername(@PathVariable String username) {
        return this.service.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable String email) {
        return this.service.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/character_list/{id}")
    public ResponseEntity<List<CharacterEntity>> getCharactersOfUser(@PathVariable Long id) {
        List<CharacterEntity> characterEntityList = this.service.getCharactersOfUser(id);
        if (characterEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(characterEntityList);
    }

    @PostMapping("/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        this.service.createUser(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity, @PathVariable Long id) {
        this.service.updateUser(userEntity, id);
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserEntity> deleteById(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
