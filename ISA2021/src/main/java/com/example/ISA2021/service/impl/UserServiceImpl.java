package com.example.ISA2021.service.impl;

import com.example.ISA2021.common.TimeProvider;
import com.example.ISA2021.config.consts.UserRoles;
import com.example.ISA2021.dto.UserDTO;
import com.example.ISA2021.dto.UserRegistrationDTO;
import com.example.ISA2021.exception.ApiRequestException;
import com.example.ISA2021.exception.ResourceNotFoundException;
import com.example.ISA2021.mappers.UserMapper;
import com.example.ISA2021.model.CancelRequest;
import com.example.ISA2021.model.ConfirmationToken;
import com.example.ISA2021.model.User;
import com.example.ISA2021.repository.AuthorityRepository;
import com.example.ISA2021.repository.ConfirmationTokenRepository;
import com.example.ISA2021.repository.UserRepository;
import com.example.ISA2021.service.AuthorityService;
import com.example.ISA2021.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityService authService;

	@Autowired
	private ConfirmationTokenRepository tokenRepository;

	@Autowired
	private TimeProvider timeProvider;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username);
		return u;
	}

	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}
	@Override
	public User addUser(UserRegistrationDTO userInfo) {
		if (userRepository.findByUsername(userInfo.getUsername()) != null) {
			throw new ApiRequestException("Username '" + userInfo.getUsername() + "' already exists.");
		}

		if (userRepository.findByEmail(userInfo.getEmail()) != null) {
			throw new ApiRequestException("Email '" + userInfo.getEmail() + "' is taken.");
		}

		User user = createNewUserObject(userInfo);
		userRepository.save(user);

		ConfirmationToken token = new ConfirmationToken(user);
		tokenRepository.save(token);

		return user;
	}

	@Override
	public User save(User u) {
		return userRepository.save(u);
	}

	private User createNewUserObject(UserRegistrationDTO userInfo) {
		User user = UserMapper.toUserEntity(userInfo);
		user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		user.setLastPasswordResetDate(timeProvider.nowTimestamp());
		user.getUserAuthorities().add(authorityRepository.findByName(UserRoles.ROLE_CLIENT));
		user.setName(userInfo.getName());
		user.setSurname(userInfo.getSurname());
		user.setEmail(userInfo.getEmail());
		user.setAddress(userInfo.getAddress());
		user.setCity(userInfo.getCity());
		user.setCountry(userInfo.getCountry());
		user.setRoleType(UserRoles.ROLE_CLIENT);
		user.setNumber(userInfo.getPhone());

		//aktivacija naloga
		user.setEnabled(true);

		return user;
	}

	@Override
	public void delete(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " doesn't exist"));

		userRepository.delete(user);
	}

	@Override
	public void activateUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " doesn't exist"));

		if(user.isEnabled()) {
			user.setEnabled(false);
		} else {
			user.setEnabled(true);
		}
		userRepository.save(user);
	}

	@Override
	public User editUser(UserDTO user) {
		User userInfo = findById(user.getId());
		userInfo.setName(user.getName());
		userInfo.setSurname(user.getSurname());
		userInfo.setUsername(user.getUsername());
		userInfo.setCountry(user.getCountry());
		userInfo.setCity(user.getCity());
		userInfo.setAddress(user.getAddress());
		userInfo.setNumber(user.getNumber());
		userInfo = save(userInfo);
		return userInfo;
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findById(id).orElseGet(null);
	}

}
