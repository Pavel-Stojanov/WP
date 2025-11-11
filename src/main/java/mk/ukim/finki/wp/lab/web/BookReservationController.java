package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookReservationController {
    private final BookService bookService;
    private final BookReservationService bookReservationService;

    public BookReservationController(BookService bookService, BookReservationService bookReservationService) {
        this.bookService = bookService;
        this.bookReservationService = bookReservationService;
    }

    @PostMapping("/bookReservation")
    public String reserveBook(
            @RequestParam String readerName,
            @RequestParam String readerAddress,
            @RequestParam int numCopies,
            @RequestParam Long bookId,
            HttpServletRequest request,
            Model model) {

        Book selectedBook = bookService.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        String clientIp = request.getRemoteAddr();

        BookReservation reservation = bookReservationService.placeReservation(selectedBook.getTitle(), readerName, readerAddress, numCopies, clientIp);

        model.addAttribute("reservation", reservation);

        return "reservationConfirmation";
    }
}
