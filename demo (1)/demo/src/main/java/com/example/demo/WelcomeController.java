
package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    // inject via application.properties


    private final List<Movie> movies = new ArrayList<>();
    private String message;

    public WelcomeController() {
        movies.add(new Movie("Dune: Part Two", 155, "2023-11-17", "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family."));
        movies.add(new Movie("A Quiet Place: Day One", 120, "2024-05-03", "A woman named Sam must survive an invasion in New York City by bloodthirsty alien creatures with ultrasonic sound hearing."));
        movies.add(new Movie("Furiosa: A Mad Max Saga", 158, "2024-11-17", "The origin story of renegade warrior Furiosa before her encounter and teamup with Mad Max."));
        movies.add(new Movie("Kingdom of the Planet of the Apes", 155, "2024-09-27", "Many years after the reign of Caesar, a young ape goes on a journey that will lead him to question everything he's been taught about the past and make choices that will define a future for apes and humans alike."));
        movies.add(new Movie("The Fall Guy", 126, "2024-08-13", "A down-and-out stuntman must find the missing star of his ex-girlfriend's blockbuster film."));
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("movies", movies);
        return "welcome"; //view
    }

    @GetMapping("/movie/{index}")
    public String movieDetail(@PathVariable int index, Model model){
        if(index >= 0 && index <movies.size()){
            model.addAttribute("movie", movies.get(index));
            model.addAttribute("index", index);
            return "movieDetail";
        } else {
            model.addAttribute("message", "Invalid movie index");
            return "sorry";
        }
    }

    @GetMapping("/add-movie")
    public String addMovieForm() {
        return "addMovie";
    }

    @PostMapping("/movie")
    public String addMovie(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "runtime") int runtime,
            @RequestParam(name = "releaseDate") String releaseDate,
            @RequestParam(name = "description") String description,
            Model model) {
        Movie newMovie = new Movie(title, runtime, releaseDate, description);
        movies.add(newMovie);
        model.addAttribute("movies", movies);
        model.addAttribute("message", "Movie " + title + " has been added succesfully");
        return "thankYou";
    }

    @GetMapping("/edit-movie/{index}")
    public String editMovieForm(@PathVariable int index, Model model) {
        if (index >= 0 && index < movies.size()) {
            model.addAttribute("movie", movies.get(index));
            model.addAttribute("index", index);
            return "editMovie";
        } else {
            model.addAttribute("message", "Invalid movie index");
            return "sorry";
        }
    }

    @PostMapping("/delete-movie/{index}")
    public String deleteMovie(@PathVariable int index, Model model) {
        if (index >= 0 && index < movies.size()) {
            Movie deletedMovie = movies.remove(index);
            model.addAttribute("movies", movies);
            model.addAttribute("message", "Movie " + deletedMovie.getTitle() + " has been deleted from the list");
            return "thankYou";
        } else {
            model.addAttribute("message", "Invalid movie index");
            return "sorry";
        }
    }

    @PostMapping ("/update-movie/{index}")
    public String updateMovie(
            @RequestParam(name = "index") int index,
            @RequestParam(name = "newTitle") String newTitle,
            @RequestParam(name = "newRuntime") int newRuntime,
            @RequestParam(name = "newReleaseDate") String newReleaseDate,
            @RequestParam(name = "newDescription") String newDescription,
            Model model) {
        if(index >= 0 && index < movies.size()){
            Movie movie = movies.get(index);
            movie.setTitle(newTitle);
            movie.setRuntime(newRuntime);
            movie.setReleaseDate(newReleaseDate);
            movie.setDescription(newDescription);
            model.addAttribute("movie", movie);
            model.addAttribute("newTitle", newTitle);
            model.addAttribute("newRuntime", newRuntime);
            model.addAttribute("newReleaseDate", newReleaseDate);
            model.addAttribute("newDescription", newDescription);
            model.addAttribute("message", "Movie " + movie.getTitle() + " has been updated successfully.");
            return "thankYou";
        } else {
            model.addAttribute("message", "Invalid movie index");
            return "sorry";
        }
    }
}
