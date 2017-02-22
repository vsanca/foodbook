package e2.isa.grupa5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.model.users.UserDTO;
import e2.isa.grupa5.repository.UserRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;

/**
 * 
 * General operations that can be performed on any {@link User}.
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
	
	/**
	 * Sets all variable attributes that are possible to update.
	 * 
	 * @param u
	 * @param uDTO
	 */
	public void setVariableAttributes(User u, UserDTO uDTO) {
		u.setName(uDTO.getName());
		u.setSurname(uDTO.getSurname());
		u.setAddress(uDTO.getAddress());
	}
	
	/**
	 * Copies all information from DTO to {@link User} object.
	 * 
	 * @param u
	 * @param uDTO
	 */
	public void copyData(User u, UserDTO uDTO) {
		u.setEmail(uDTO.getEmail());
		u.setPassword(uDTO.getPassword());
		setVariableAttributes(u, uDTO);
	}
	
}
