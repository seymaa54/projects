package com.bookstore.bookstore.repository;


import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.Category;
import com.bookstore.bookstore.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByCategoryAndLanguageAndTitleIgnoreCase(Category category, Language language, String title
    );
    Book findByTitleIgnoreCase(String title);

    List<Book> findAll();
    Optional<Book> findById(Long id);

    List<Book> findByAuthor(Author author);



}
