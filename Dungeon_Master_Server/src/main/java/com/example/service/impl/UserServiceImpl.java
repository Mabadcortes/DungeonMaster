package com.example.service.impl;

import com.example.model.CharacterEntity;
import com.example.model.UserEntity;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public List<CharacterEntity> getCharactersOfUser(Long id) {
        Optional<UserEntity> userEntity = this.repository.findById(id);
        UserEntity user = null;
        List<CharacterEntity> characterEntityList = null;
        if (userEntity.isPresent()) {
            user = userEntity.get();
            characterEntityList = user.getCharacterList();
        }
        return characterEntityList;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        System.out.println(userEntity.toString());
        this.repository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity, Long id) {
        Optional<UserEntity> newUser = this.repository.findById(id);
        UserEntity user = null;
        if (newUser.isPresent()) {
            user = newUser.get();
            user.setUsername(userEntity.getUsername());
            user.setEmail(userEntity.getEmail());
            user.setCharacterList(userEntity.getCharacterList());
            this.repository.save(user);
        }
        return userEntity;
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
