package com.bookstore.bookstore.service;


import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.Bookshelf;
import com.bookstore.bookstore.entity.Shelf;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.BookshelfRepository;
import com.bookstore.bookstore.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookshelfService {

    @Autowired
    BookshelfRepository bookshelfRepository;

    public Bookshelf createBoookshelf(Shelf shelf){
        Bookshelf newBookshelf=new Bookshelf();
        newBookshelf.getShelves().add(shelf);
        newBookshelf.setAvailableShelfcapacity(newBookshelf.getAvailableShelfcapacity()+1);
        shelf.setBookshelf(newBookshelf);
        bookshelfRepository.save(newBookshelf);
        return newBookshelf;
    }

    public Bookshelf shelfControl(Bookshelf bookshelf,Shelf shelf){

        if(bookshelf.getShelves().size()<4){
            bookshelf.getShelves().add(shelf);
            shelf.setBookshelf(bookshelf);
            bookshelf.setAvailableShelfcapacity(bookshelf.getAvailableShelfcapacity()+1);
            bookshelfRepository.save(bookshelf);
            return bookshelf;
        }
        else{
              return null;

        }
    }


    public void deleteBookshelf(Bookshelf bookshelf){
        bookshelfRepository.delete(bookshelf);
    }


    }



