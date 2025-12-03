package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void initData() {
        if (authorRepository.count() == 0 && bookRepository.count() == 0) {
            List<Author> authors = new ArrayList<>();
            authors.add(new Author("J.K", "Rowling", "England", "J.K Rowling biography"));
            authors.add(new Author("J.R.R", "Tolkien", "England", "J.R.R Tolkien biography"));
            authors.add(new Author("George", "Orwell", "England", "George Orwell biography"));
            authors.add(new Author("Andy", "Weir", "United States", "Andy Weir biography"));
            authors.add(new Author("Douglas", "Adams", "England", "Douglas Adams biography"));
            authorRepository.saveAll(authors);

            List<Book> books = new ArrayList<>();
            books.add(new Book("Harry Potter", "Fantasy", 5.0, authors.get(0)));
            books.add(new Book("Lord of the Rings", "Fantasy", 4.8, authors.get(1)));
            books.add(new Book("1984", "Dystopian", 4.5, authors.get(2)));
            books.add(new Book("Project Hail Mary", "Science Fiction", 4.9, authors.get(3)));
            books.add(new Book("The Hitchhiker's Guide To The Galaxy", "Sci-Fi", 4.2, authors.get(4)));
            bookRepository.saveAll(books);
        }
    }
}