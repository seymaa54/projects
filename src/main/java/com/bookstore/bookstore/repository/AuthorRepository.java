package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);

}
