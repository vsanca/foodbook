package e2.isa.grupa5.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import e2.isa.grupa5.model.users.UserRoles;

/**
 * 
 * @author Jelena Jankovic RA139-2013
 *
 */
public class JwtUser implements UserDetails {

	private static final long serialVersionUID = -5225841727660999691L;
	private final Long id;
    private final String firstname;
    private final String surname;
    private final String password;
    private final String email;
    private final boolean active;
    private UserRoles userRoles;
    
    
    
	public JwtUser(Long id, String name, String surname, String email, String password, boolean active,
			UserRoles role) {
		this.id = id;
		this.firstname = name;
		this.surname = surname;
		this.email = email;
		this.active = active;
		this.userRoles = role;
		this.password = password;
	}

	public UserRoles getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	@JsonIgnore
    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }


    public String getEmail() {
        return email;
    }

	public boolean isActive() {
		return active;
	}
    
	/** SPRING SECURITY fields **/
	
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
    	 return Collections.singletonList(new SimpleGrantedAuthority(userRoles.name()));
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public String getSurname() {
		return surname;
	}
	
	
}
