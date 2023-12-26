package net.brc.repo;

import net.brc.model.Exam;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepo extends ListCrudRepository<Exam, Long> {
}
