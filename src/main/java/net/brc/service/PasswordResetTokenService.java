package net.brc.service;

import net.brc.model.PasswordResetToken;
import org.springframework.stereotype.Service;

@Service
public interface PasswordResetTokenService {

    PasswordResetToken save(PasswordResetToken passwordResetToken);
}
