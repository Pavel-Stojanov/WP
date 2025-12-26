package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {
    List<Book> findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(String text, Double rating);
    List<Book> findAllByAuthor_Id(Long authorId);
}
