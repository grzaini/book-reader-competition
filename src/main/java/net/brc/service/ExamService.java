package net.brc.service;

import lombok.AllArgsConstructor;
import net.brc.model.Exam;
import net.brc.model.Reader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExamService {

    Exam findCurrentExam();

    Exam save(Exam exam);

    Optional<Exam> findExamById(Long id);

    List<Exam> findAllExams();

    List<Reader> findWinners(Exam exam);
}
