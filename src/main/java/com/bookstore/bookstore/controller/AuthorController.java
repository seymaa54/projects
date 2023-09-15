package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/showBooks/{firstName}/{lastName}/titles")
    public List<String> showBooks(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return  authorService.showBooks(firstName,lastName);
    }

    @GetMapping("/showCategories/{firstName}/{lastName}/categories")
    public Map<String, List<String>> showCategories(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return  authorService.showCategories(firstName,lastName);
    }



    @DeleteMapping("/delete/{firstName}/{lastName}")
    public String deleteAuthor(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        return authorService.delete(firstName,lastName);

    }

}


