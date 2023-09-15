package com.bookstore.bookstore.service;


import com.bookstore.bookstore.entity.*;
import com.bookstore.bookstore.repository.BookshelfRepository;
import com.bookstore.bookstore.repository.ShelfRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelfService {
    @Autowired
    ShelfRepository shelfRepository;


    public Shelf shelfControl(Shelf shelf,Category category,Language language) {
        if (shelf.getBooks().size() < 3) {
            return shelf;
        }
        else{
            return null;
        }
    }


    public Shelf createShelf(Category category, Language language){
        Shelf newShelf=new Shelf();
        newShelf.setCategory(category);
        newShelf.setLanguage(language);
        shelfRepository.save(newShelf);
        return newShelf;

    }

    public Shelf shelfControl2(Shelf shelf,Category category,Language language) {
        if (shelf.getBooks().size() <= 3) {
            return shelf;
        }
        else{
            return null;
        }
    }





}
