package e2.isa.grupa5.security;



import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.repository.interfaces.UserRepository;


/**
 * 
 * @author Jelena Jankovic RA139-2013
 *
 */
@Component
public class UserDetailsServiceImpl  implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.trace(".loadUserByUsername({})", username);

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }

        final List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        JwtUser jwtUser = JwtUserFactory.create(user, grantedAuthorities);
        return jwtUser;
    }

    
   
}