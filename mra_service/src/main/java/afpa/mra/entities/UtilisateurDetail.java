package afpa.mra.entities;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UtilisateurDetail implements UserDetails{

    private final Utilisateur user;

    public UtilisateurDetail(Utilisateur user){
        this.user = user;
    }

    public String getUserId() {
        return user.getId().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    String role = user.getRole().name();
        return Collections.singletonList(new SimpleGrantedAuthority(role));
}

    @Override
    public String getPassword() {
        return user.getMdp();
    }

    @Override
    public String getUsername() {
       return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }
}
