package e2.isa.grupa5.service.jpa;

import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.repository.interfaces.UserRepository;
import e2.isa.grupa5.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Viktor on 12/21/2016.
 */

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.findOne(id);
        if(user != null){
            userRepository.delete(user);
        }

        return user;
    }

    @Override
    public User findByEmail(String email) {
       return userRepository.findByEmail(email);
    }
}
