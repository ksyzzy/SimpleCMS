package com.example.modelsservice.services;

import com.example.modelsservice.dto.UserDTO;
import com.example.modelsservice.models.User;
import com.example.modelsservice.repositories.UserRepository;
import org.bouncycastle.openssl.PasswordException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserDTO getUserDTOById(long id) {
        User user = userRepository.findById(id).orElseThrow();
        return convertToUserDTO(user);
    }

    public User addUser(User user) throws PasswordException {
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new PasswordException("Password cannot be blank");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            return userRepository.save(user);
        } else {
            throw new NoSuchElementException();
        }
    }

    public User setAdminPermissions(Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        user.setAdmin(!user.isAdmin());
        return userRepository.save(user);
    }

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
