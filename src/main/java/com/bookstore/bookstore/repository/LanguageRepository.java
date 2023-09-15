package com.bookstore.bookstore.repository;


import com.bookstore.bookstore.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository  extends JpaRepository<Language,Long> {
  Language findByLanguageIgnoreCase(String language);
}
