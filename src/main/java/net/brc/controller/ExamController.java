package net.brc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.brc.model.Exam;
import net.brc.repo.ExamRepo;
import net.brc.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ExamController {

    @Autowired
    private ExamRepo examRepo;

    @Autowired
    private ExamService examService;

    @Autowired
    public ExamController(ExamService examService, ExamRepo examRepo) {
        this.examService = examService;
        this.examRepo = examRepo;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert-exam")
    public void insert(@Valid @RequestBody Exam exam){
        examRepo.save(exam);
    }

    @GetMapping
    public Exam getCurrentExam(){
        Exam currentExam = examService.findCurrentExam();
        return currentExam;
    }
}
