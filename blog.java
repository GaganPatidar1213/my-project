package com.example.newsy;

// Blog.java
public class blog {
    private String title;
    private String content;
    private String author;

    // Constructor
    public blog(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}

