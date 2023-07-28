package afpa.mra.services;

import afpa.mra.entities.RazMdp;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.RazMdpRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private RazMdpRepository resetPasswordRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;



    public void saveResetCode(Utilisateur utilisateur, Integer code) {
        RazMdp resetPassword = new RazMdp();
        resetPassword.setUtilisateur(utilisateur);
        resetPassword.setCode(code);
        Instant now = Instant.now();
        Instant expireDateTime = now.plus(3, ChronoUnit.HOURS);
        resetPassword.setDateExpiration(Date.from(expireDateTime));
        resetPasswordRepository.save(resetPassword);
    }

    public String isCodeValid(Integer Code, String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        if(!utilisateur.isPresent()){
            return "Utilisateur non trouvé";
        }
        Optional<RazMdp> resetPassword = resetPasswordRepository.findByCodeAndUtilisateur(Code, utilisateur.get());
        if(!resetPassword.isPresent()){
            return "Code non trouvé";
        }
        Date expireDate = resetPassword.get().getDateExpiration();
        Date currentDate = new Date();
        if (expireDate != null && expireDate.before(currentDate)){
            return "Code expired";
        }
        return "Code OK";
    }
}
