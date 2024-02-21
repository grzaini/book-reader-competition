package net.brc.service.impl;

import net.brc.model.Exam;
import net.brc.model.PasswordResetToken;
import net.brc.model.Reader;
import net.brc.model.ReaderExam;
import net.brc.repo.PasswordResetTokenRepo;
import net.brc.repo.ReaderRepo;
import net.brc.service.ReaderExamService;
import net.brc.service.ReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {

    private static final Logger LOG = LoggerFactory.getLogger(ReaderService.class);

    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;
    @Autowired
    private ReaderRepo readerRepo;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderExamService readerExamService;

    @Override
    public Optional<Reader> findById(Long id) {
        return readerRepo.findById(id);
    }

    @Override
    public Reader findReaderByEmail(String email) {
        Reader foundReader = new Reader();
        List<Reader> readerList = readerRepo.findAll();
        for (Reader r : readerList){
            if (r.getMail().equals(email)){
                foundReader = r;
                break;
            }
            else{
                foundReader = null;
            }
        }
        return foundReader;
    }

    @Override
    public List<Reader> findAllReader() {
        return readerRepo.findAll();
    }

    @Override
    public Reader save(Reader reader) {
        return readerRepo.save(reader);
    }

    @Override
    public void createPasswordResetTokenForUser(Reader reader, String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, reader);
        passwordResetTokenRepo.save(myToken);
    }

    @Override
    public Exam findReaderExam(String email) {
        Exam exam = new Exam();
        Reader reader = readerService.findReaderByEmail(email);
        List<ReaderExam> readerExamList = readerExamService.findAllReaderExam();
        for (ReaderExam re : readerExamList){
            if(Objects.equals(re.getReader().getId(), reader.getId())){
                exam = re.getExam();
            }else {
                exam = null;
            }
        }
        return exam;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Reader reader = readerService.findReaderByEmail(username);
//        if (reader == null){
//            throw new UsernameNotFoundException("User not found");
//        }
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(reader.getMail())
//                .password(reader.getPassword())
//                .build();
//    }
}
