package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.BookAddDto;
import com.bookstore.bookstore.dto.BookCommentDto;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.BookComment;
import com.bookstore.bookstore.service.BookCommentService;
import com.bookstore.bookstore.service.BookService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(value = "/add")
    public void addBook(@RequestBody BookAddDto bookAddDto) {
        bookService.addBook(bookAddDto);
    }

    @DeleteMapping(value ="/deleteBook/{id}")
    public void decreaseStockQuantity(@PathVariable Long id){
       bookService.decreaseStockQuantity(id);
    }


   @DeleteMapping(value = "/deleteStock/{id}")
   public void deleteStock(@PathVariable Long id){
        bookService.deleteStock(id);
   }


    @DeleteMapping(value = "/deleteAll")
    public void deleteAllBooks(){
        bookService.deleteAll();
    }

    @GetMapping(value = "/find/{title}")
    public List<String> findBook(@PathVariable String title){ return bookService.findBook(title);}






}