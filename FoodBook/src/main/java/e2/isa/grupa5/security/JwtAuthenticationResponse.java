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

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
