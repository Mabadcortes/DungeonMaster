package com.example.service;

import com.example.model.CharacterEntity;
import com.example.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String username);

    List<CharacterEntity> getCharactersOfUser(Long id);

    Optional<UserEntity> findByEmail(String email);

    UserEntity createUser(UserEntity userEntity);

    UserEntity updateUser(UserEntity userEntity, Long id);

    void deleteById(Long id);
}
