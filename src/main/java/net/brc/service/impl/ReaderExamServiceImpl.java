package net.brc.service.impl;

import net.brc.model.Exam;
import net.brc.model.Reader;
import net.brc.model.ReaderExam;
import net.brc.repo.ReaderExamRepo;
import net.brc.service.ReaderExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderExamServiceImpl implements ReaderExamService {

    @Autowired
    private ReaderExamRepo readerExamRepo;

    @Autowired
    public ReaderExamServiceImpl(ReaderExamRepo readerExamRepo){
        this.readerExamRepo = readerExamRepo;
    }
    @Override
    public List<ReaderExam> findAllReaderExam() {
        return readerExamRepo.findAll();
    }

    @Override
    public void registerReaderInExam(Reader reader, Exam exam) {
        ReaderExam readerExam = new ReaderExam();
        readerExam.setReader(reader);
        readerExam.setExam(exam);
        readerExamRepo.save(readerExam);
    }
}
