package afpa.mra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import afpa.mra.services.UtilisateurDetailService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UtilisateurDetailService utilisateurDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DecryptService decryptService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String passwordEncrypted = (String) authentication.getCredentials();
        String passwordDecrypted = this.decryptService.decrypt(passwordEncrypted);
        System.out.println("Username: " + username);
        System.out.println("Password: " + passwordEncrypted);
        System.out.println("PasswordDecrypted: " + passwordDecrypted);

        UserDetails userDetails = this.utilisateurDetailService.loadUserByUsername(username);
      

        if (userDetails == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(passwordDecrypted, userDetails.getPassword())) {
            System.out.println("Wrong password");
            throw new BadCredentialsException("Wrong password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // return authentication.equals(UsernamePasswordAuthenticationToken.class);
        return true;
    }

}
