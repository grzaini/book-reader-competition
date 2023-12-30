package net.brc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.brc.model.Reader;
import net.brc.repo.ReaderRepo;
import net.brc.service.ReaderService;
import net.brc.utility.EmailService;
import net.brc.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ReaderRepo readerRepo;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert-reader")
    public void insert(@Valid @RequestBody Reader reader, HttpServletRequest request){
        List<Reader> readerList = readerRepo.findAll();
        for (Reader r : readerList){
            String newUserMail = reader.getMail();
            if (r.getMail().equals(reader.getMail())){
                throw new ResponseStatusException(HttpStatus.FOUND, "Such a user already exists!");
            }
        }
        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        reader.setPassword(encryptedPassword);
        readerRepo.save(reader);

        String token = UUID.randomUUID().toString();
        readerService.createPasswordResetTokenForUser(reader, token);

        //Email sending
        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        emailService.constructResetTokenEmail(appUrl,request.getLocale(),token, reader, password);

    }

}
