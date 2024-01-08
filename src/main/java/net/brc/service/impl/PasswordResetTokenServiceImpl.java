package net.brc.service.impl;

import net.brc.model.PasswordResetToken;
import net.brc.repo.PasswordResetTokenRepo;
import net.brc.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;

    @Override
    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        return passwordResetTokenRepo.save(passwordResetToken);
    }

    @Override
    public PasswordResetToken findByToken(String token) {

        List<PasswordResetToken> tokenList = passwordResetTokenRepo.findAll();
        PasswordResetToken isTheToken = new PasswordResetToken();

        for(PasswordResetToken tok : tokenList){
            if(tok.getToken().equals(token)){
                isTheToken = tok;
            }
            else {
                isTheToken = null;
            }
        }
        return isTheToken;
    }
}
