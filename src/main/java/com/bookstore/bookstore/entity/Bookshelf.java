package com.bookstore.bookstore.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Entity
public class Bookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private final  int maxShelfCount=4;

    private int availableShelfcapacity=0;

    @ManyToMany
    private List<Category>categories;

    @ManyToMany
    private List<Language>languages;

    @ManyToMany
    private List<Shelf> shelves = new ArrayList<>();

    public Bookshelf(){
        this.categories=new ArrayList<>();
        this.languages=new ArrayList<>();
        this.shelves=new ArrayList<>();
    }


    public int getAvailableShelfcapacity() {
        return availableShelfcapacity;
    }

    public void setAvailableShelfcapacity(int availableShelfcapacity) {
        this.availableShelfcapacity = availableShelfcapacity;
    }

    public int getMaxShelfCount() {
        return maxShelfCount;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Bookshlef ID: " + getId();
    }
}
