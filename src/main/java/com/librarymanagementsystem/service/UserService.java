package com.librarymanagementsystem.service;

import com.librarymanagementsystem.Entity.UserEntity;
import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInt{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    public User saveUser(User user) {
        UserEntity userEntity = userRepository.save(modelMapper.map(user, UserEntity.class));
        user = modelMapper.map(userEntity, User.class);
        return user;
    }
}
