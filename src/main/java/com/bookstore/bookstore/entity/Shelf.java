package com.bookstore.bookstore.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Transient
    private final int  maxBookCount=3;

    @ManyToMany
    private List<Book>books;
    @ManyToOne
    private Category category;

    @ManyToOne
    private Language language;


    public Shelf(){
        this.books=new ArrayList<>();
    }
    @ManyToOne
    private Bookshelf bookshelf;

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    public int getMaxBookCount() {
        return maxBookCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


    @Override
    public String toString() {
        return "Shelf ID: " + getId();
    }

}
