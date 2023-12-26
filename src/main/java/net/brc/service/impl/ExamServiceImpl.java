package net.brc.service.impl;

import net.brc.model.Exam;
import net.brc.repo.ExamRepo;
import net.brc.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Calendar;


@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepo examRepo;

    @Override
    public Exam findCurrentExam() {
        Calendar examCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();

        List<Exam> examList = examRepo.findAll();
        Exam currentExam = new Exam();

        for (Exam e : examList){
            examCalendar.setTime(e.getExamDate());
            int examMonth = examCalendar.get(Calendar.MONTH)+1;
            if(examMonth == calendar.get(Calendar.MONTH)+1){
                currentExam = e;
                break;
            }
            else currentExam = null;
        }
        return currentExam;
    }

    @Override
    public Exam save(Exam exam) {
        return examRepo.save(exam);
    }

    @Override
    public Optional<Exam> findExamById(Long id) {
        return examRepo.findById(id);
    }
}
