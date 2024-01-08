package net.brc.service;

import net.brc.model.Exam;
import net.brc.model.Reader;
import net.brc.model.ReaderExam;
import net.brc.repo.ReaderExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReaderExamService {


    List<ReaderExam> findAllReaderExam();

    void registerReaderInExam(Reader reader, Exam exam);
}
