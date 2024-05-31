
package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    // inject via application.properties


    private List<Movie> movies = new ArrayList<>();
    private String message;

    public WelcomeController() {
        movies.add(new Movie("Dune: Part Two", 155, "2023-11-17"));
        movies.add(new Movie("Monkey Man", 120, "2024-05-03"));
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("movies", movies);
        return "welcome"; //view
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "User")
            String name, Model model) {

        model.addAttribute("message", name);

        return "welcome"; //view
    }

    @GetMapping("/movie")
    public String addMovie(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "runtime") int runtime,
            @RequestParam(name = "releaseDate") String releaseDate,
            Model model) {
        Movie newMovie = new Movie(title, runtime, releaseDate);
            if(!movies.contains(newMovie)){
                movies.add(newMovie);
                model.addAttribute("message", " Movie " + title + " has been added successfully");
                model.addAttribute("movies", movies);
                return "thankYou";
            } else {
                model.addAttribute("message", " Movie already exists: " + title);
                return "sorry";
            }
    }

    @GetMapping("/delete-movie")
    public String deleteMovie(
            @RequestParam(name = "number") int movieNumber, Model model) {
            if(movieNumber >=0 && movieNumber < movies.size()){
                Movie deletedMovie = movies.remove(movieNumber - 1);
                model.addAttribute("movies", movies);
                model.addAttribute("deletedMovie", deletedMovie);
                model.addAttribute("message"," Movie " + deletedMovie + " has been deleted from the list");
                return "thankYou";
            } else {
                model.addAttribute("message", " Invalid movie number");
                return "sorry";
            }
    }

    @GetMapping("/update-movie")
    public String updateMovie(
            @RequestParam(name = "number") int movieNumber,
            @RequestParam(name = "newTitle") String newTitle,
            @RequestParam(name = "newRuntime") int newRuntime,
            @RequestParam(name = "newReleaseDate") String newReleaseDate,
            Model model) {
        if(movieNumber >= 0 && movieNumber < movies.size()){
            Movie movie = movies.get(movieNumber);
            String oldTitle = movie.getTitle();
            movie.setTitle(newTitle);
            movie.setRuntime(newRuntime);
            movie.setReleaseDate(newReleaseDate);
            model.addAttribute("oldTitle", oldTitle);
            model.addAttribute("newTitle", newTitle);
            model.addAttribute("movies", movies);
            model.addAttribute("message", " Movie " + oldTitle + " has been updated to " + newTitle);
            return "thankYou";
        } else {
            model.addAttribute("message", "Invalid movie number");
            return "sorry";
        }
    }
}
