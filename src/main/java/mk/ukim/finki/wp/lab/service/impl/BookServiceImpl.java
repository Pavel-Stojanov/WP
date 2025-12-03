package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll(String text, Double rating, Long authorId) {

        Specification<Book> spec = Specification.where(null);

        // filter title
        if (text != null && !text.isEmpty()) {
            spec = spec.and((root1, query1, cb) ->
                    cb.like(cb.lower(root1.get("title")), "%" + text.toLowerCase() + "%"));
        }

        // filter rating
        if (rating != null) {
            spec = spec.and((root1, query1, cb) ->
                    cb.greaterThanOrEqualTo(root1.get("averageRating"), rating));
        }

        // filter author
        if (authorId != null) {
            spec = spec.and((root1, query1, cb) ->
                    cb.equal(root1.get("author").get("id"), authorId));
        }

        return this.bookRepository.findAll(spec);
    }

    @Override
    public Optional<Book> findBookById(long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Book addBook(String title, String genre, Double averageRating, Long authorId) {
        if(title == null || title.isEmpty() ||
                genre == null || genre.isEmpty() ||
                averageRating == null || averageRating <= 0.0 ||
                authorId == null
        ){
            throw new IllegalArgumentException("Invalid parameters");
        }
        Author author = this.authorRepository.findById(authorId).get();
        return this.bookRepository.save(new Book(title, genre, averageRating, author));
    }

    @Override
    public Book editBook(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        if(bookId == null ||
                title == null || title.isEmpty() ||
                genre == null || genre.isEmpty() ||
                averageRating == null || averageRating <= 0.0 ||
                authorId == null){

            throw new IllegalArgumentException("Invalid parameters");
        }

        Book book = this.bookRepository.findById(bookId).get();
        Author author = this.authorRepository.findById(authorId).get();

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(long id) {

        bookRepository.deleteById(id);
    }
}
