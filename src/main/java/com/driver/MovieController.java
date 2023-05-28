package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("Movie added Successfully", HttpStatus.CREATED);
    }
    @PostMapping("/addd-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("Director added Successfully",HttpStatus.CREATED);
    }
    @PutMapping("/movie-director-pair")
    public ResponseEntity<String> MovieDirectorPair(@RequestParam String movie ,@RequestParam String director){
        movieService.movieDirectorPair(movie,director);

        return new ResponseEntity<>("Movie Director pair created ",HttpStatus.CREATED);
    }
    @GetMapping("/get-by-movie-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie movie=movieService.getMovieByName(name);
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director=movieService.getDiectorByName(name);
        return new ResponseEntity<>(director,HttpStatus.OK);
    }
    @GetMapping("/get-movie-by-director/{director}")
    public ResponseEntity<List<String>> getMovieByDirectorName(@PathVariable String name){
        List<String> movies=movieService.getMoviesByDirector(name);
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> getAllMovies(){
        List<String> movies=movieService.getAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name){
        movieService.deleteDirectorByName(name);
        return new ResponseEntity<>("Director with given name is Deleted",HttpStatus.OK);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String > deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All Directors deleted", HttpStatus.OK);
    }
}
