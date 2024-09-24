package dev.myorg.backbone.service;

import dev.myorg.backbone.entity.Movie;
import dev.myorg.backbone.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepo;

    public List<Movie> allMovies()
    {
        return movieRepo.findAll();
    }

    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return movieRepo.findMovieByImdbId(imdbId);
    }
}
