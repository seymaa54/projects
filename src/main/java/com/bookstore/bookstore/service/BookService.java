package com.bookstore.bookstore.service;

import com.bookstore.bookstore.dto.BookAddDto;
import com.bookstore.bookstore.entity.*;
import com.bookstore.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    ShelfService shelfService;

    @Autowired
    BookshelfService bookshelfService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    LanguageService languageService;

    @Autowired
    BookshelfRepository bookshelfRepository;


    public void addBook(BookAddDto bookAddDto) {
        String title = bookAddDto.getdTitle();
        String descripiton = bookAddDto.getDdescription();

        String firstName = bookAddDto.getDfirstName();
        String lastName = bookAddDto.getdLastName();
        int pageCount = bookAddDto.getDpageCount();
        String categoryy = bookAddDto.getdCategory();
        String languagee = bookAddDto.getdLanguage();


        Category category = categoryRepository.findByCategoryIgnoreCase(categoryy);
        Language language = languageRepository.findByLanguageIgnoreCase(languagee);


        Author existingAuthor = authorRepository.findByFirstNameAndLastNameIgnoreCase(firstName, lastName);
        if (existingAuthor == null) {
            existingAuthor = new Author();
            existingAuthor.setFirstName(firstName);
            existingAuthor.setLastName(lastName);
            authorRepository.save(existingAuthor);
        }

        try {

            //kitap sorgusu
            List<Book> existingBooks = bookRepository.findByCategoryAndLanguageAndTitleIgnoreCase(
                    category, language, title);

            //Kitap mevcut değilse yapılacak controller
            if (existingBooks.isEmpty()) {

                //dil ve kategori kontrolü

                if (category == null && language != null) {
                    category = categoryService.createCategoryy(categoryy, existingAuthor, language);
                } else if (category != null && language == null) {
                    language = languageService.createLanguagee(languagee, existingAuthor, category);
                } else if (category == null && language == null) {
                    category = categoryService.createCategoryy(categoryy, existingAuthor, language);
                    language = languageService.createLanguagee(languagee, existingAuthor, category);
                }
                if(category!=null&&language !=null){
                    if(!category.getAuthors().contains(existingAuthor)){
                        category.getAuthors().add(existingAuthor);
                        authorRepository.save(existingAuthor);
                    }
                    if (!language.getAuthors().contains(existingAuthor)){
                        language.getAuthors().add(existingAuthor);
                        authorRepository.save(existingAuthor);
                    }
                }
                Book newBook = new Book();
                newBook.setTitle(title);
                newBook.setCategory(category);
                newBook.setLanguage(language);
                newBook.setAuthor(existingAuthor);
                newBook.setDescription(descripiton);
                newBook.setPageCount(pageCount);
                newBook.setStockQuantity(newBook.getStockQuantity() + 1);
                bookRepository.save(newBook);

                //bir kitabın tutuldugu raflar liste halinde
                Shelf shelf;

                List<Shelf> shelves = shelfRepository.findByCategoryAndLanguage(category, language);

                if (shelves.isEmpty()) {
                    shelf = shelfService.createShelf(category, language);
                    shelf.getBooks().add(newBook);
                } else {
                    boolean shelfIsFound = false;
                    Shelf selectedShelf = null; // Seçilen raf
                    for (Shelf innerShelf : shelves) {
                        Shelf foundShelf = shelfService.shelfControl(innerShelf, category, language);

                        if (foundShelf != null) {
                            shelfIsFound = true;
                            selectedShelf = foundShelf;
                            break;
                        }
                    }

                    if (shelfIsFound == true) {
                        selectedShelf.getBooks().add(newBook);
                    } else {
                        shelf = shelfService.createShelf(category, language);
                        shelf.getBooks().add(newBook);
                    }
                }

                bookRepository.save(newBook);

                List<Shelf> shelf1 = shelfRepository.findShelvesByBook(newBook);
                shelf = shelf1.get(0);

                Bookshelf bookshelf = bookshelfRepository.findByShelvesContains(shelf);
                if (bookshelf != null) {
                    newBook.getBookshelves().add(bookshelf);

                } else {
                    List<Bookshelf> bookshelves = bookshelfRepository.findAll();
                    if (bookshelves.isEmpty()) {
                        Bookshelf bookshelf1 = bookshelfService.createBoookshelf(shelf);
                        newBook.getBookshelves().add(bookshelf1);
                    } else {
                        Bookshelf selectedBookshelf = null;
                        for (Bookshelf bookshelff : bookshelves) {
                            bookshelff = bookshelfService.shelfControl(bookshelff, shelf);
                            if (bookshelff != null) {
                                selectedBookshelf = bookshelff;
                                break;
                            }
                        }
                        if (selectedBookshelf == null) {
                            bookshelf = bookshelfService.createBoookshelf(shelf);
                            newBook.getBookshelves().add(bookshelf);
                        } else {
                            newBook.getBookshelves().add(selectedBookshelf);
                        }
                    }
                }
                bookRepository.save(newBook);
            }
            else {
                Book existingBook = existingBooks.get(0);

                if(!category.getAuthors().contains(existingAuthor))
                    category.getAuthors().add(existingAuthor);
                    authorRepository.save(existingAuthor);

                if(!language.getAuthors().contains(existingAuthor))
                    language.getAuthors().add(existingAuthor);
                authorRepository.save(existingAuthor);

                List<Shelf> shelves = shelfRepository.findShelvesByBook(existingBook);

                Shelf selectedShelf = null;
                for (Shelf shelf : shelves) {
                    shelf = shelfService.shelfControl(shelf, category, language);
                    if (shelf != null) {
                        selectedShelf = shelf;
                        break;
                    }
                }
                if (selectedShelf == null) {
                    Shelf newShelf = shelfService.createShelf(category, language);
                    newShelf.getBooks().add(existingBook);
                    existingBook.setStockQuantity(existingBook.getStockQuantity() + 1);
                    bookRepository.save(existingBook);


                    List<Bookshelf> bookshelves = bookshelfRepository.findAll();
                    Bookshelf selectedBookshelf = null;
                    for (Bookshelf bookshelf : bookshelves) {
                        bookshelf = bookshelfService.shelfControl(bookshelf, newShelf);
                        if (bookshelf != null) {
                            selectedBookshelf = bookshelf;
                            break;

                        }
                    }

                    if (selectedBookshelf == null) {
                        Bookshelf bookshelf = bookshelfService.createBoookshelf(newShelf);
                        existingBook.getBookshelves().add(bookshelf);
                    } else {
                        existingBook.getBookshelves().add(selectedBookshelf);

                    }

                    bookRepository.save(existingBook);


                } else {
                    //selectedShelf=!null
                    selectedShelf.getBooks().add(existingBook);
                    existingBook.setStockQuantity(existingBook.getStockQuantity() + 1);
                    existingBook.getBookshelves().add(selectedShelf.getBookshelf());
                    bookRepository.save(existingBook);

                }

            }


        } catch (Exception ex) {
            System.out.println("hata");
            ex.printStackTrace();
        }
    }

    public void decreaseStockQuantity(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Category category = book.getCategory();
            Language language = book.getLanguage();

            List<Shelf> shelfList = shelfRepository.findShelvesByBook(book);
            Shelf foundedShelf = null;
            for (Shelf shelf : shelfList) {
                shelf = shelfService.shelfControl(shelf, category, language);
                if (shelf != null) {
                    foundedShelf = shelf;
                    break;
                }
            }

            if (foundedShelf == null) {
                for (Shelf shelf : shelfList) {
                    shelf = shelfService.shelfControl2(shelf, category, language);
                    if (shelf != null) {
                        foundedShelf = shelf;
                        break;
                    }

                }


            }

            if (foundedShelf != null) {
                foundedShelf.getBooks().remove(book);
                shelfRepository.save(foundedShelf);
            }
            book.setStockQuantity(book.getStockQuantity() - 1);
            bookRepository.save(book);
            if (book.getStockQuantity() <= 0) {
                bookRepository.delete(book);
            }

            if (foundedShelf.getBooks().size() <= 0) {
                Bookshelf bookshelf = foundedShelf.getBookshelf();
                bookshelf.getShelves().remove(foundedShelf);
                bookshelf.setAvailableShelfcapacity(bookshelf.getAvailableShelfcapacity()-1);
                shelfRepository.delete(foundedShelf);
                bookshelfRepository.save(bookshelf);
                if (bookshelf.getShelves().isEmpty()) {
                    bookshelfService.deleteBookshelf(bookshelf);
                    bookshelfRepository.save(bookshelf);
                }

            }

        } else {
            System.out.println("The book was not found!");
        }

    }
    public void deleteStock(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            int s = book.getStockQuantity();
            for (int i = 0; i < s; i++) {
                decreaseStockQuantity(id);
            }

        }
    }
    public void deleteAll() {
        List<Book> allBooks = bookRepository.findAll();
        List<Long> bookIds = allBooks.stream()
                .map(Book::getId) // Her kitabın ID'sini alıyoruz
                .collect(Collectors.toList()); // Long türündeki ID'leri List<Long> olarak topluyoruz


        for (Long id : bookIds) {
            deleteStock(id);
            bookRepository.deleteById(id);

        }
        shelfRepository.deleteAll();
        bookshelfRepository.deleteAll();
    }
    public List<String> findBook(String title) {
        Book book = bookRepository.findByTitleIgnoreCase(title);
        List<Shelf> shelves = shelfRepository.findAll();
        List<String> strings = new ArrayList<>();
        if (book != null) {
            for (Shelf shelf : shelves) {
                if (shelf.getBooks().contains(book)) {
                    Bookshelf bookshelf = shelf.getBookshelf();
                    strings.add(bookshelf.toString() + " " + shelf.toString());
                }
            }
        } else {
            strings.add("The book was not found!");
            return strings;
        }
        return  strings;

    }


}