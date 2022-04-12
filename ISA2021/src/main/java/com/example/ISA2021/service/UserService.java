package com.example.ISA2021.service;

import com.example.ISA2021.dto.UserDTO;
import com.example.ISA2021.dto.UserRegistrationDTO;
import com.example.ISA2021.model.CancelRequest;
import com.example.ISA2021.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
	User save(User userRequest);
    User addUser(UserRegistrationDTO userInfo);
    void delete(Long id);
    void activateUser(Long id);
    User editUser(UserDTO user);
  //  User findOne(Long dermatologistId);
    User findOne(Long id);

}
