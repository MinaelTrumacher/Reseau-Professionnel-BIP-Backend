package afpa.mra.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.util.ClassUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import afpa.mra.services.UtilisateurDetailService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UtilisateurDetailService utilisateurDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DecryptService decryptService;
    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // System.out.println(authentication);
        // System.out.println("username=>" + authentication.getPrincipal());
        // String username = authentication.getName();
        // System.out.println("=>" + authentication.getPrincipal());
        
        String username = (String) authentication.getPrincipal();

        if (username.length() > 100){
            Jwt claims = jwtDecoder.decode(username);
            String usernameJWT = claims.getClaims().get("sub").toString();
            Instant expirationDate = (Instant) claims.getClaims().get("exp");
            System.out.println("usernameJWT: " + usernameJWT);
            System.out.println("RoleJWT: " + claims.getClaims().get("role").toString());
            System.out.println("UserIdJWT: " + claims.getClaims().get("userId").toString());


            if (expirationDate.isBefore(Instant.now())) {
                System.out.println("Token expired");
                throw new BadCredentialsException("Token expired");
            }

            UserDetails userDetails = this.utilisateurDetailService.loadUserByUsername(usernameJWT);

            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        }else{
                
            String passwordEncrypted = (String) authentication.getCredentials();
            String passwordDecrypted = this.decryptService.decrypt(passwordEncrypted);
            System.out.println("username: " + username);
            System.out.println("Password: " + passwordEncrypted);
            System.out.println("PasswordDecrypted: " + passwordDecrypted);

            UserDetails userDetails = this.utilisateurDetailService.loadUserByUsername(username);

            System.out.println("UserDetail: " + userDetails.toString());
            
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
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // return authentication.equals(UsernamePasswordAuthenticationToken.class);
        return true;
    }

}
