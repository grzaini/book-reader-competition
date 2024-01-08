package net.brc.repo;

import net.brc.model.ReaderExam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderExamRepo extends ListCrudRepository<ReaderExam, Long> {
}
