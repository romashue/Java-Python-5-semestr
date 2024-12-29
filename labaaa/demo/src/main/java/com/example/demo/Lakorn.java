package com.example.demo;

public class Lakorn {
    private int id;
    private String name;
    private int year;
    private String genre;
    private String rating;

    public Lakorn(int id, String name, int year, String genre, String rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    public int getId() {return id;}
    public String getName() { return name; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }
    public String getRating() { return rating; }
}
