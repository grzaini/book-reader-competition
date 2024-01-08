package net.brc.controller;

import lombok.AllArgsConstructor;
import net.brc.model.Book;
import net.brc.model.Exam;
import net.brc.model.Reader;
import net.brc.service.ExamService;
import net.brc.service.ReaderService;
import net.brc.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ReaderService readerService;
    //get index page
    @GetMapping
    public String home(Model model){

        Reader reader = new Reader();
        List<Exam> examList = examService.findAllExams();
        Exam currentExam = examService.findCurrentExam();
        Book currentBook = new Book();
        if (currentExam != null) {
            currentBook = currentExam.getBook();

        } else {
            model.addAttribute("noExam", "There is no exam yet!");
        }
        model.addAttribute("currentExam", currentExam);
        model.addAttribute("currentBook", currentBook);
        model.addAttribute("examList", examList);
        //model.addAttribute("reader", reader);

        return "index";
    }

    //get user page
    @PostMapping("/my-profile")
    public String login(@ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            Model model, RedirectAttributes ra){

        Reader reader = readerService.findReaderByEmail(username);
        String dbPassword = reader.getPassword();
        if(!SecurityUtility.passwordEncoder().matches(password,dbPassword)){
            ra.addFlashAttribute("passNotCorrect", true);
            return "redirect:/";
        }
        Exam readerExam = readerService.findReaderExam(username);

        //String username = principal.getName();
        model.addAttribute("reader", reader);
        model.addAttribute("readerExam", readerExam);
        return "my-profile";

    }

    @GetMapping("/bad-request")
    public String getErrorPage(){

        return "bad-request";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/";
    }
}
