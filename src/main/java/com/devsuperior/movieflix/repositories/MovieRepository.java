package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m INNER JOIN FETCH m.genre WHERE m.id = :id")
    Optional<Movie> findById(Long id);

    @Query("SELECT m FROM Movie m ORDER BY m.title")
    Page<Movie> findAllOrderByTitle(Pageable pageable);

    Page<Movie> findByGenreOrderByTitle(Genre genre, Pageable pageable);
}
