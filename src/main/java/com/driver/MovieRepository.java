package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {
    private HashMap<String,Movie> movieData=new HashMap<>();
    private HashMap<String ,Director> directorData=new HashMap<>();
    private HashMap<String, List<String>> movieDirectorPair=new HashMap<>();

    public void addMovie(Movie movie)
    {
        movieData.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {

        directorData.put(director.getName(), director);
    }

    public Optional<Movie> findMovie(String movie) {
        if(movieData.containsKey(movie)){
            return Optional.of(movieData.get(movie));
        }
        return Optional.empty();
    }

    public Optional<Director> findDirector(String director) {
        if(directorData.containsKey(director)){
            return Optional.of(directorData.get(director));
        }
        return  Optional.empty();
    }

    public void addMovieDirectorPair(String movie, String director) {
        List<String> movieList=movieDirectorPair.getOrDefault(director,new ArrayList<>());
        movieList.add(movie);
        movieDirectorPair.put(director,movieList);
    }

    public Optional<Movie> getMovieByName(String name) {
        if(movieData.containsKey(name)){
            Optional.of(movieData.get(name));
        }
        return Optional.empty();
    }

    public Optional<Director> getDirectorByName(String name) {
        if(directorData.containsKey(name)){
            Optional.of(directorData.get(name));
        }
        return Optional.empty();
    }

    public List<String> getMovieByDirector(String name) {
        return movieDirectorPair.getOrDefault(name,new ArrayList<>());
    }

    public List<String> getAllMovies() {
        List<String> movies=new ArrayList<>();
        for(var entry : movieData.entrySet()){
            movies.add(entry.getKey());
        }
        return movies;
    }

    public void deleteDirectorByName(String name) {
        if(movieDirectorPair.containsKey(name)){
            List<String> movies=movieDirectorPair.getOrDefault(name,new ArrayList<>());
            for(String movie:movies){
                movieData.remove(movie);
            }
            directorData.remove(name);
            movieDirectorPair.remove(name);

        }

        throw new RuntimeException("Director not found");
    }

    public void deleteAllDirectors() {
        for(var entry:directorData.entrySet()){
            deleteDirectorByName(entry.getKey());
        }
    }
}
