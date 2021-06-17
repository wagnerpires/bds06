package com.devsuperior.movieflix.dtos;

import java.io.Serializable;

public class GenreDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public GenreDTO() {

    }

    public GenreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreDTO genreDTO = (GenreDTO) o;

        return id != null ? id.equals(genreDTO.id) : genreDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
