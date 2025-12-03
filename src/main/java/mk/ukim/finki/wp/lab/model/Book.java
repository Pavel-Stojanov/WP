package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private double averageRating;

    @ManyToOne
    private Author author;

    public Book(String title, String genre, double rating, Author author) {
        this.title = title;
        this.genre = genre;
        this.averageRating = rating;
        this.author = author;
    }
}
