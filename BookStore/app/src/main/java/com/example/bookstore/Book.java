package com.example.bookstore;

public class Book {
    private String name;
    private String author;
    private String imgUrl;
    private String description;

    public Book(String name, String author, String imgUrl, String description) {
        this.name = name;
        this.author = author;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }
    public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
