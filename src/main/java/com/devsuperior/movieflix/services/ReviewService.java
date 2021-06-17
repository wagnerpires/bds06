package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private final AuthService authService;
    private final ModelMapper mapper;
    private final ReviewRepository repository;

    public ReviewService(AuthService authService, ModelMapper mapper, ReviewRepository repository) {
        this.authService = authService;
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {

        User user = authService.authenticated();

        Review entity = mapper.map(dto, Review.class);
        entity.setUser(user);

        entity = repository.save(entity);
        return mapper.map(entity, ReviewDTO.class);
    }
}
