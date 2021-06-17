package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dtos.UserDTO;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {
    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(){
        UserDTO dto = service.getProfile();
        return ResponseEntity.ok().body(dto);
    }

}
