package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Optional<Book> findById(Long id);
    Book save(String title, String genre, double rating, Author author);
    Book update(Long id, String title, String genre, double rating, Author author);
    void deleteById(Long id);
}
