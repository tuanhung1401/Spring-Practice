package com.example.demo;

public class Movie {
    private String title;
    private int runtime;
    private String releaseDate;
    private String description;

    public Movie(){}

    public Movie(String title, int runtime, String releaseDate, String description) {
        this.title = title;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() { return description;}

    public void setDescription(String description) { this.description = description; }
}
