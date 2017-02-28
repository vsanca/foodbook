package e2.isa.grupa5.security;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.service.guest.GuestService;

/**
 * Handles user authentication 
 * User authorization is role based using Security Aspects
 * @author Jelena Jankovic RA139-2013
 *
 */
@RequestMapping("/rest/auth")
@RestController
public class AuthenticationController {
	
	 	private final Log logger = LogFactory.getLog(this.getClass());
	
	    @Value("${jwt.header}")
	    private String tokenHeader;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    private GuestService guestService;
	
	    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
	    	logger.info("Performing login for user:" + authenticationRequest.getUsername());
	    	// UserServiceImpl sets passwordEncoder in order to PERFORM HASH on user input in order to compare with salted database value
	    	
	    	
	        // Perform the security
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        authenticationRequest.getUsername(),
	                        authenticationRequest.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Reload password post-security so we can generate token
	        final JwtUser userDetails = (JwtUser)userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	        final String token = jwtTokenUtil.generateToken(userDetails);
	        
	        // Return the token
	        return ResponseEntity.ok(new JwtAuthenticationResponse(token, userDetails.getId(), userDetails.getUserRoles()));
	    }
	    
	    
	    @RequestMapping(value = "/login-with-social-provider", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	    public ResponseEntity<?> loginWithSocialProvider(@RequestBody User user, Device device) throws AuthenticationException {
	    	logger.info("Performing login for user:" + user.getEmail());
	    	// UserServiceImpl sets passwordEncoder in order to PERFORM HASH on user input in order to compare with salted database value
	    	
	    	// if the user is not registered, register him and activate his account
	    	guestService.registerAndOrLoginGuestWithSocialProvider(user);
	    	JwtAuthenticationRequest authenticationRequest = new JwtAuthenticationRequest(user.getEmail(), user.getPassword());
	    	
	        // Perform the security
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        authenticationRequest.getUsername(),
	                        authenticationRequest.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Reload password post-security so we can generate token
	        final JwtUser userDetails = (JwtUser)userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	        final String token = jwtTokenUtil.generateToken(userDetails);
	        
	        // Return the token
	        return ResponseEntity.ok(new JwtAuthenticationResponse(token, userDetails.getId(), userDetails.getUserRoles()));
	    }

}
