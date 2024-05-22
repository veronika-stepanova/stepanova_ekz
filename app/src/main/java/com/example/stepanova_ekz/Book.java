package com.example.stepanova_ekz;

public class Book {
    private String Title;
    private String Author;
    private int Year;

    public Book(String title, String author, int year) {
        Title = title;
        Author = author;
        Year = year;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.Year = year;
    }
}
