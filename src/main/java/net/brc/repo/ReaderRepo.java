package net.brc.repo;

import net.brc.model.Reader;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepo extends ListCrudRepository<Reader, Long> {

}
