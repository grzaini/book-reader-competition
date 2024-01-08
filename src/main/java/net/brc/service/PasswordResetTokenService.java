package net.brc.service;

import net.brc.model.PasswordResetToken;
import net.brc.repo.PasswordResetTokenRepo;
import org.springframework.stereotype.Service;

@Service
public interface PasswordResetTokenService {

    PasswordResetToken save(PasswordResetToken passwordResetToken);

    PasswordResetToken findByToken(String token);
}
