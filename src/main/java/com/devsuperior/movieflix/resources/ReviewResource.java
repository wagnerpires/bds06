package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/reviews")
public class ReviewResource {
    private final ReviewService service;

    public ReviewResource(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
