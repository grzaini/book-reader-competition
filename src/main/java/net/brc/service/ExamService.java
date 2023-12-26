package net.brc.service;

import lombok.AllArgsConstructor;
import net.brc.model.Exam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExamService {

    Exam findCurrentExam();

    Exam save(Exam exam);

    Optional<Exam> findExamById(Long id);
}
