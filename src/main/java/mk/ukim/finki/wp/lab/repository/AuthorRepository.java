package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@DependsOn("dataHolder")
public class AuthorRepository {
    public static List<Author> authors = null;

    @PostConstruct
    public void init() {
        authors = new ArrayList<>();
        authors.add(new Author("J.K", "Rowling", "England", "J.K Rowling biography"));
        authors.add(new Author("J.R.R", "Tolkien", "England", "J.R.R Tolkien biography"));
        authors.add(new Author("George", "Orwell", "England", "George Orwell biography"));
        authors.add(new Author("Andy", "Weir", "United States", "Andy Weir biography"));
        authors.add(new Author("Douglas", "Adams", "England", "Douglas Adams biography"));
        authors.add(new Author("Aldous", "Huxley", "England", "Aldous Huxley biography"));
        authors.add(new Author("Mary", "Shelley", "England", "Mary Shelley biography"));
        authors.add(new Author("Frank", "Herbert", "United States", "Frank Herbert biography"));
        authors.add(new Author("Suzanne", "Collins", "United States", "Suzanne Collins biography"));
        authors.add(new Author("Arthur C.", "Clarke", "England", "Arthur C. Clarke biography"));
        for (int i = 0; i < authors.size(); i++) {
            authors.get(i).setId((long) i);
            DataHolder.books.get(i).setAuthor(authors.get(i));
        }
    }

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(Long id) {
        return authors.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
