package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class MovieService {
    private final ModelMapper mapper;
    private final GenreRepository genreRepository;
    private final MovieRepository repository;

    public MovieService(ModelMapper mapper, GenreRepository genreRepository, MovieRepository repository) {
        this.mapper = mapper;
        this.genreRepository = genreRepository;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Optional<Movie> optional = repository.findById(id);
        Movie entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return mapper.map(entity, MovieDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<MovieDTO> findByGenre(@RequestParam(defaultValue = "0") Long genreId, Pageable pageable) {
        Page<Movie> page = null;

        if (genreId == 0) {
            page = repository.findAllOrderByTitle(pageable);
        } else {
            Genre genre = genreRepository.getOne(genreId);
            page = repository.findByGenreOrderByTitle(genre, pageable);
        }

        return page.map(entity -> mapper.map(entity, MovieDTO.class));
    }
}
