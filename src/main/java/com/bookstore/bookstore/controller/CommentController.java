package com.bookstore.bookstore.controller;


import com.bookstore.bookstore.dto.BookCommentDto;
import com.bookstore.bookstore.entity.BookComment;
import com.bookstore.bookstore.service.BookCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    BookCommentService bookCommentService;

    @PostMapping(value ="/add")
    public void addComment(@RequestBody BookCommentDto bookCommentDto){
        bookCommentService.addComment(bookCommentDto);
    }

    @GetMapping(value ="/showComments/{title}")
    public List<String> showComments(@PathVariable String title){
       return bookCommentService.showComments(title);
    }

    @DeleteMapping(value ="/delete/{title}/{id}")
    public void deleteComment(@PathVariable String title,@PathVariable Long id){
        bookCommentService.deleteCommentById(title,id);
    }

    @DeleteMapping(value ="/delete/{title}")
    public void deleteAllComments(@PathVariable String title){
        bookCommentService.deleteCommentsByBook(title);
    }



}
