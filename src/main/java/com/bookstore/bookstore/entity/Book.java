package com.bookstore.bookstore.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int stockQuantity=0;
    private int pageCount;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Category category;
    @ManyToOne
    private Language language;
    @ManyToMany
    private List<Bookshelf>bookshelves;

    public Book(){
        this.bookshelves=new ArrayList<>();
    }

    @OneToMany
    private List<BookComment> comments =new ArrayList<>();

    public List<BookComment> getComments() {
        return comments;
    }

    public void setComments(List<BookComment> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<Bookshelf> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


}
