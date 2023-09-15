package com.bookstore.bookstore.repository;


import com.bookstore.bookstore.entity.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCommentRepository extends JpaRepository<BookComment,Long> {
}
