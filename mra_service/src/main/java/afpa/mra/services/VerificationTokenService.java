package afpa.mra.services;

import afpa.mra.entities.Utilisateur;
import afpa.mra.entities.VerificationToken;
import afpa.mra.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationToken getByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    public VerificationToken getByUtilisateur(Utilisateur utilisateur) {
        return verificationTokenRepository.findByUtilisateur(utilisateur);
    }

    public void saveVerificationToken(Utilisateur utilisateur, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUtilisateur(utilisateur);
        verificationToken.setToken(token);
        Instant now = Instant.now();
        Instant expireDateTime = now.plus(24, ChronoUnit.HOURS);
        verificationToken.setExpireDate(Date.from(expireDateTime));
        verificationTokenRepository.save(verificationToken);
    }
}
