package e2.isa.grupa5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.repository.UserRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;

@Service
@Transactional
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
