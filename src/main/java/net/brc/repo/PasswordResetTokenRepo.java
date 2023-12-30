package net.brc.repo;

import net.brc.model.PasswordResetToken;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepo extends ListCrudRepository<PasswordResetToken, Long> {
}
