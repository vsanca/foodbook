package e2.isa.grupa5.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import e2.isa.grupa5.model.users.User;

/**
 * 
 * @author Jelena Jankovic RA139-2013
 *
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user, List<GrantedAuthority> authorities) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                user.getRole(),
                authorities
        );
    }
}
