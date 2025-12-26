package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class BookReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private String bookTitle;
    private String readerName;
    private String readerAddress;
    private int numberOfCopies;
    private String clientIp;

    public BookReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies, String clientIp) {
        this.timestamp = LocalDateTime.now();
        this.bookTitle = bookTitle;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numberOfCopies = numberOfCopies;
        this.clientIp = clientIp;
    }
}
