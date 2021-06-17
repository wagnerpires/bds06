package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
	private final AuthService authService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final ModelMapper mapper;
	private final UserRepository repository;

	public UserService(AuthService authService, BCryptPasswordEncoder passwordEncoder, ModelMapper mapper, UserRepository repository) {
		this.authService = authService;
		this.passwordEncoder = passwordEncoder;
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);

		Optional<User> optional = repository.findById(id);

		User entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		UserDTO dto = mapper.map(entity, UserDTO.class);

		return dto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return user;
	}

	public UserDTO getProfile() {
		User entity = authService.authenticated();
		UserDTO dto = mapper.map(entity, UserDTO.class);
		return dto;
	}
}
