package e2.isa.grupa5.repository.interfaces;

import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.model.users.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findByAddress(String address);
    User findByEmail(String email);
    User findById(Long id);
    List<User> findByName(String name);
    List<User> findByPassword(String password);
    List<User> findByRole(UserRoles role);
    List<User> findBySurname(String surname);

}
