package net.brc.service.impl;

import net.brc.model.PasswordResetToken;
import net.brc.repo.PasswordResetTokenRepo;
import net.brc.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;

    @Override
    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        return passwordResetTokenRepo.save(passwordResetToken);
    }
}
