package net.brc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import net.brc.model.Exam;
import net.brc.model.PasswordResetToken;
import net.brc.model.Reader;
import net.brc.service.ExamService;
import net.brc.service.PasswordResetTokenService;
import net.brc.service.ReaderExamService;
import net.brc.service.ReaderService;
import net.brc.utility.EmailService;
import net.brc.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderExamService readerExamService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ExamService examService;
    //@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert-reader")
    public String insertReader(@ModelAttribute("reader") Reader reader,
                               HttpServletRequest request, Model model, RedirectAttributes ra){
        Exam currentExam = examService.findCurrentExam();
        List<Reader> readerList = readerService.findAllReader();
        for (Reader r : readerList){
            String newUserMail = reader.getMail();
            if (r.getMail().equals(newUserMail)){
                //throw new ResponseStatusException(HttpStatus.FOUND, "Such a user already exists!");
                //model.addAttribute("emailExist", true);
                ra.addFlashAttribute("emailExist", "This email exist!");
                return "redirect:/";
            }
        }

        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        reader.setPassword(encryptedPassword);

        //reader.getExam().add(currentExam);
        readerService.save(reader);

        readerExamService.registerReaderInExam(reader,currentExam);

        String token = UUID.randomUUID().toString();
        readerService.createPasswordResetTokenForUser(reader, token);

        //Email sending
        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        emailService.constructResetTokenEmail(appUrl,request.getLocale(),token, reader, password);
        ra.addFlashAttribute("created", true);
        return "redirect:/";
    }

    @GetMapping("/newReader")
    public String getUpdateReader(@PathParam("token") String token, Model model){

        PasswordResetToken isUserToken = passwordResetTokenService.findByToken(token);
        List<Reader> readerList = readerService.findAllReader();

        for(Reader r : readerList){
            if(r.getId() == isUserToken.getReader().getId()){
                model.addAttribute("reader", r);
                break;
            }
        }
        return "update-reader";
    }

    @PostMapping("/info-updated")
    public String postUpdateReader(@ModelAttribute("reader") Reader reader,
                                   @ModelAttribute("new-password") String givenPassword, Model model,
                                   @ModelAttribute("reader-id") String id) throws Exception {

        String newPassword = givenPassword;
        Long currentId = Long.parseLong(id);
        Optional<Reader> optionalReader = readerService.findById(currentId);
        Reader currentReader = optionalReader.orElse(new Reader());
        if(currentReader == null) {
            throw new Exception ("User not found");
        }

        //update password
        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = (currentReader.getPassword() != null) ? currentReader.getPassword() : "000";
            currentReader.setPassword(passwordEncoder.encode(newPassword));
        }

        currentReader.setFullName_en(reader.getFullName_en());
        currentReader.setFullName_fa(reader.getFullName_fa());
        currentReader.setMail(reader.getMail());
        currentReader.setMobile(reader.getMobile());
        //currentReader.setDob(reader.getDob());

        readerService.save(currentReader);

        model.addAttribute("reader", currentReader);

        Exam readerExam = readerService.findReaderExam(currentReader.getMail());
        model.addAttribute("readerExam", readerExam);

        return "my-profile";
    }




}
