package com.example.ISA2021.controller;

import com.example.ISA2021.dto.BoatDTO;
import com.example.ISA2021.dto.CancelRequestDTO;
import com.example.ISA2021.dto.UserDTO;
import com.example.ISA2021.dto.UserRegistrationDTO;
import com.example.ISA2021.mappers.UserMapper;
import com.example.ISA2021.model.Boat;
import com.example.ISA2021.model.CancelRequest;
import com.example.ISA2021.model.User;
import com.example.ISA2021.repository.CancelRequestRepository;
import com.example.ISA2021.service.CancelRequestService;
import com.example.ISA2021.service.UserService;
import com.example.ISA2021.service.impl.CancelRequestServiceImpl;
import com.example.ISA2021.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CancelRequestService cancelRequestService;

	private UserServiceImpl userImpl;

	@Autowired
	private CancelRequestServiceImpl cancelRequestImpl;

	@Autowired
	private CancelRequestRepository cancelRequestRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @PostMapping("/public/register")
    public ResponseEntity add(@Valid @RequestBody UserRegistrationDTO user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("activate/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity activate(@PathVariable Long id) {
		userService.activateUser(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/logged/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findById(id);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "/edit")
	public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO userDTO) {

		User userInfo = userService.findById(userDTO.getId());
		userInfo.setName(userDTO.getName());
		userInfo.setSurname(userDTO.getSurname());
		userInfo.setUsername(userDTO.getUsername());
		userInfo.setCountry(userDTO.getCountry());
		userInfo.setCity(userDTO.getCity());
		userInfo.setAddress(userDTO.getAddress());
		userInfo.setNumber(userDTO.getNumber());

		userInfo = userService.save(userInfo);

		return new ResponseEntity<>(UserMapper.toDto(userInfo), HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "{userId}/cancelRequest")
	public ResponseEntity cancelRequest(@PathVariable long userId, @RequestBody CancelRequestDTO crd) {
		CancelRequest cr = new CancelRequest();
		cr.setUserId(userId);
		cr.setText(crd.getText());
		cr.setAccepted(crd.isAccepted());

		cr = cancelRequestService.save(cr);
		return ResponseEntity.ok().build();
	}


	@GetMapping(value = "/allCancelRequests")
	public ResponseEntity<List<CancelRequestDTO>> getAllCancelRequests() {

		List<CancelRequest> cr = cancelRequestService.findAll();

		List<CancelRequestDTO> crDTO = new ArrayList<>();
		for (CancelRequest crs: cr) {
			crDTO.add(new CancelRequestDTO(crs));
		}

		return new ResponseEntity<>(crDTO, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "/answerRequest")
	public ResponseEntity answerRequest(@RequestBody CancelRequestDTO crd) {
		cancelRequestImpl.answerRequest(crd);
		return ResponseEntity.ok().build();
	}
}
