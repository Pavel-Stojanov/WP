package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation,Long> {
}
