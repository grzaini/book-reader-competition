package net.brc.service;

import net.brc.model.Reader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReaderService {

    Optional<Reader> findReaderById(Long id);

    Reader findReaderByEmail(String email);

    List<Reader> findAllReader();

    Reader save(Reader reader);

    void createPasswordResetTokenForUser(final Reader reader, final String token);

}
