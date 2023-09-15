package com.bookstore.bookstore.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String category;
    public Category() {
        this.authors = new ArrayList<>();
        this.languages = new ArrayList<>();
    }


    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
   @ManyToMany
   private List<Author>authors;

   @ManyToMany
   private List<Language>languages;


    public List<Author> getAuthors() {
        return authors;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
