package e2.isa.grupa5.security;

import java.io.Serializable;

/**
 * 
 * @author Jelena Jankovic RA139-2013
 *
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;
    
    /**
     * Value of JWT token generated for logged in Restaurant user
     */
    private final String token;
    
    /**
     * User ID, to be used for REST calls for front-end
     * @param token
     */
    private final long userId;

    public JwtAuthenticationResponse(String token, long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

	public long getUserId() {
		return userId;
	}
    
    
}
