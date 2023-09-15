package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Category;
import com.bookstore.bookstore.entity.Language;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    AuthorRepository authorRepository;



    public Language createLanguagee(String language, Author existingAuthor, Category category){
        Language newLangauge=new Language();
        newLangauge.getAuthors().add(existingAuthor);
        newLangauge.setLanguage(language);
        newLangauge.getCategories().add(category);
        authorRepository.save(existingAuthor);
        languageRepository.save(newLangauge);

        return newLangauge;
    }

}
