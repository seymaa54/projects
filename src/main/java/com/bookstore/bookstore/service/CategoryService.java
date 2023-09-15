package com.bookstore.bookstore.service;


import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Category;
import com.bookstore.bookstore.entity.Language;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;



    @Autowired
    AuthorRepository authorRepository;
    public Category createCategoryy(String category, Author author, Language language){
        Category newCategory=new Category();
        newCategory.setCategory(category);
        newCategory.getAuthors().add(author);
        newCategory.getLanguages().add(language);
        authorRepository.save(author);
        categoryRepository.save(newCategory);
        return  newCategory;

    }

}
