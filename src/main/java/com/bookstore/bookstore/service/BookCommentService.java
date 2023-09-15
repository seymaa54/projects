package com.bookstore.bookstore.service;


import com.bookstore.bookstore.dto.BookCommentDto;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.BookComment;
import com.bookstore.bookstore.repository.BookCommentRepository;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCommentService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCommentRepository bookCommentRepository;

    public void addComment(BookCommentDto bookCommentDto){
        String title=bookCommentDto.getTitle();
        String text=bookCommentDto.getCommentText();

        Book book=bookRepository.findByTitleIgnoreCase(title);
        if(book!=null){
            BookComment bookComment=new BookComment();
            bookComment.setCommentText(text);
            book.getComments().add(bookComment);
            bookCommentRepository.save(bookComment);
        }
        else{
            System.out.println("The book was not found");
        }
    }

    public List<String> showComments(String title) {
        List<String> list = new ArrayList<>();
        Book book = bookRepository.findByTitleIgnoreCase(title);
        if (book != null) {
            if (book.getComments().isEmpty()) {
                list.add("There are no comments for this book.");
                return list;
            } else {
                List<String> comments = new ArrayList<>();
                for(BookComment bookComment:book.getComments()){
                    comments.add(bookComment.getCommentDate().toString()+" "+bookComment.getCommentText());
                }
                return comments;
            }
        }
        else{
            list.add("The book was not found!");
            return list;

        }
        }
        public void deleteCommentById(String title,Long id){
        Book book=bookRepository.findByTitleIgnoreCase(title);
        Optional<BookComment> bookComment=bookCommentRepository.findById(id);
        if(bookComment.isPresent()){
            List<BookComment>bookComments=book.getComments();
            BookComment commentToDelete = bookComment.get();
            bookComments.remove( commentToDelete);
            bookRepository.save(book);
            bookCommentRepository.deleteById(id);
        }
        else{
            System.out.println("The comment was not found!");
        }

        }


    public void deleteCommentsByBook(String title) {
        Book book = bookRepository.findByTitleIgnoreCase(title);

        if (book != null) {
            List<BookComment> bookComments = book.getComments();
            List<Long> bookCommentsId=new ArrayList<>();


            if (!bookComments.isEmpty()) {
                for(BookComment bookComment:bookComments){
                    bookCommentsId.add(bookComment.getId());
                }
                bookComments.clear();
                bookRepository.save(book);

                for (Long id : bookCommentsId) {
                    bookCommentRepository.deleteById(id);
                }

                System.out.println("Comments have been removed.");
            } else {
                System.out.println("No comments found for the book.");
            }
        } else {
            System.out.println("The book was not found!");
        }
    }


}