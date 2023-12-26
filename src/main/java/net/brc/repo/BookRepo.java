package net.brc.repo;

import net.brc.model.Book;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends ListCrudRepository<Book, Long> {
}
