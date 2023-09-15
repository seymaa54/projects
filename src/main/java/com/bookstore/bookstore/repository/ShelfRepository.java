package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf,Long> {
    @Query("SELECT s FROM Shelf s JOIN s.books b WHERE b = :book")
    List<Shelf> findShelvesByBook(@Param("book") Book book);

    List<Shelf> findByCategoryAndLanguage(Category category,Language language);


}
