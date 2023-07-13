package afpa.mra.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import afpa.mra.entities.UtilisateurDetail;
import afpa.mra.repositories.UtilisateurRepository;

@Service
public class UtilisateurDetailService implements UserDetailsService {


    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailService(UtilisateurRepository utilisateurRepository) {
           this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateurRepository
                .findByEmail(username)
                .map(UtilisateurDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + username));
    }
    
}
