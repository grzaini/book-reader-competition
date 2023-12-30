package net.brc.service.impl;

import net.brc.model.PasswordResetToken;
import net.brc.model.Reader;
import net.brc.repo.PasswordResetTokenRepo;
import net.brc.repo.ReaderRepo;
import net.brc.service.ReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {

    private static final Logger LOG = LoggerFactory.getLogger(ReaderService.class);

    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;
    @Autowired
    private ReaderRepo readerRepo;
    public Optional<Reader> findReaderById(Long id) {
        return readerRepo.findById(id);
    }

    @Override
    public Reader findReaderByEmail(String email) {
        Reader foundReader = new Reader();
        List<Reader> readerList = readerRepo.findAll();
        for (Reader r : readerList){
            if (r.getMail().equals(email)){
                foundReader = r;
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
}
