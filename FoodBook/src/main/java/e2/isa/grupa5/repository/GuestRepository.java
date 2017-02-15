package e2.isa.grupa5.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.UserRoles;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

	
	List<Guest> findByAddress(String address);
	List<Guest> findByEmail(String email);

	List<Guest> findById(Long id);
	List<Guest> findByName(String name);
	Guest findByEmailAndPassword(String username, String password);
	List<Guest> findByPassword(String password);

	List<Guest> findByRole(UserRoles role);
	List<Guest> findBySurname(String surname);
	
	
}
