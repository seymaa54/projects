package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.Category;
import com.bookstore.bookstore.entity.Language;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.CategoryRepository;
import com.bookstore.bookstore.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LanguageRepository languageRepository;

    public List<String> showBooks(String firstName,String lastName) {

        Author author = authorRepository.findByFirstNameAndLastNameIgnoreCase(firstName, lastName) ;
        if (author == null) {
            List newLİst=new ArrayList<>();
            newLİst.add("The author was not found!");
            return newLİst;

        } else {
            List<Book> booksByAuthor = bookRepository.findByAuthor(author);
            List<String> titles=new ArrayList<>();
            for(Book book:booksByAuthor){
                titles.add(book.getTitle());

            }
            return titles;
        }


    }

    public Map<String, List<String>> showCategories(String firstName, String lastName) {
        Author author = authorRepository.findByFirstNameAndLastNameIgnoreCase(firstName, lastName);
        if (author == null) {
            Map<String, List<String>> result = new HashMap<>();
            List<String> error = new ArrayList<>();
            error.add("The author was not found!");
            result.put("Result:", error);
            return result;
        }

        List<Book> books = bookRepository.findByAuthor(author);
        Map<String, List<String>> categorizedBooks = new HashMap<>();

        for (Book book: books) {
            Category category = book.getCategory();
            String categoryName =category.getCategory();
            String bookTitle =book.getTitle();

            categorizedBooks.computeIfAbsent(categoryName, k -> new ArrayList<>()).add(bookTitle);
        }

        return categorizedBooks;
    }


public  String  delete(String firstName, String lastName){
    Author author = authorRepository.findByFirstNameAndLastNameIgnoreCase(firstName, lastName);
    if(author!=null){
        List<Book> books=bookRepository.findByAuthor(author);
        List<Long> bookIds = books.stream()
                .map(Book::getId) // Her kitabın ID'sini alıyoruz
                .collect(Collectors.toList()); // Long türündeki ID'leri List<Long> olarak topluyoruz

        for (Long id : bookIds) {
            bookService.deleteStock(id);
        }
        List<Category> categories=categoryRepository.findAll();
        List<Language> languages = languageRepository.findAll();

        for (Category category : categories) {
            category.getAuthors().remove(author);
            categoryRepository.save(category);
        }

        for (Language language : languages) {
           language.getAuthors().remove(author);
            languageRepository.save(language);
        }
        authorRepository.delete(author);
        return ("The author was deleted!");
    }
    else{
       return ("The author is not available!");
    }
}


}

