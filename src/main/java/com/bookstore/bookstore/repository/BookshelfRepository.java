package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.entity.Bookshelf;
import com.bookstore.bookstore.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf,Long> {

    Bookshelf findByShelvesContains(Shelf shelf);

    List<Bookshelf> findAll();
}
