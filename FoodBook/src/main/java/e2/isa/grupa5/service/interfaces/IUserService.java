package e2.isa.grupa5.service.interfaces;

import e2.isa.grupa5.model.users.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findOne(Long id);

    User save(User user);

    User delete(Long id);

    User findByEmail(String email);
}
