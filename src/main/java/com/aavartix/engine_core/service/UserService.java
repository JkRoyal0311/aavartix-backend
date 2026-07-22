package com.aavartix.engine_core.service;

import com.aavartix.engine_core.dto.UserRegistrationRequest;
import com.aavartix.engine_core.model.User;
import com.aavartix.engine_core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUser(UserRegistrationRequest request) {
        // 1. Check if user already exists
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already registered!");
        }

        // 2. Create the new user entity
        User newUser = new User();
        newUser.setFullName(request.getFullName());
        newUser.setPhoneNumber(request.getPhoneNumber());

        // Note: For MVP, we are storing password as plain text.
        // In V2, we will add BCrypt hashing here for security.
        newUser.setPasswordHash(request.getPassword());

        // 3. Save to PostgreSQL
        return userRepository.save(newUser);
    }
}