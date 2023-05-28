package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie) {

        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }

    public void movieDirectorPair(String movie, String director) {
        Optional<Movie> moviefnd=movieRepository.findMovie(movie);
        Optional<Director> findDirector=movieRepository.findDirector(director);
        if(moviefnd.isEmpty() || findDirector.isEmpty()){
            throw new RuntimeException("Entity not found");
        }
        movieRepository.addMovieDirectorPair(movie,director);
    }

    public Movie getMovieByName(String name) {
        Optional<Movie> movieFnd=movieRepository.getMovieByName(name);
        if(movieFnd.isEmpty()){
            throw new RuntimeException("Movie by given name not found");
        }
        return movieFnd.get();
    }

    public Director getDiectorByName(String name) {
        Optional<Director> directorFnd=movieRepository.getDirectorByName(name);
        if(directorFnd.isEmpty()){
            throw new RuntimeException("Director by given name not found");
        }
        return directorFnd.get();
    }

    public List<String> getMoviesByDirector(String name) {
        List<String> movies=movieRepository.getMovieByDirector(name);
        if(movies.isEmpty()){
            throw new RuntimeException("Not present");
        }
        return movies;
    }

    public List<String> getAllMovies() {
        List<String> movies=movieRepository.getAllMovies();
        return movies;
    }

    public void deleteDirectorByName(String name) {
        movieRepository.deleteDirectorByName(name);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}
