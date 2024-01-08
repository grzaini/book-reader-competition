package net.brc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.brc.model.Book;
import net.brc.model.Exam;
import net.brc.model.Reader;
import net.brc.repo.ExamRepo;
import net.brc.service.ExamService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ExamController {

    @Autowired
    private ExamService examService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert-exam")
    public void insert(@Valid @RequestBody Exam exam){
        examService.save(exam);
    }

    @GetMapping("/current-exam")
    public Exam getCurrentExam(){
        Exam currentExam = examService.findCurrentExam();
        return currentExam;
    }
}
