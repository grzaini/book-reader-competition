package net.brc.service;

import net.brc.model.Exam;
import net.brc.model.Reader;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReaderService {
//public interface ReaderService {
    Optional<Reader> findById(Long id);

    Reader findReaderByEmail(String email);

    List<Reader> findAllReader();

    Reader save(Reader reader);

    void createPasswordResetTokenForUser(final Reader reader, final String token);

    Exam findReaderExam(String email);

}
