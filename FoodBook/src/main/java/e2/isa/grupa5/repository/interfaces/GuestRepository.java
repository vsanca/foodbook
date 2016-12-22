package e2.isa.grupa5.repository.interfaces;


import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {


	/*List<Guest> findByAddress(String address);
	List<Guest> findByEmail(String email);
	List<Guest> findByFriends(List friends);*/
	List<Guest> findById(Long id);
	/*List<Guest> findByName(String name);
	List<Guest> findByPassword(String password);
	List<Guest> findByReservations(List reservations);
	List<Guest> findByRole(UserRoles role);
	List<Guest> findBySurname(String surname);*/
}
