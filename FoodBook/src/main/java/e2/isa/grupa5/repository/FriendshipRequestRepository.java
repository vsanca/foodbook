package e2.isa.grupa5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.friends.FriendshipRequest;
import e2.isa.grupa5.model.users.Guest;

public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Long> {

	List<FriendshipRequest> findByFrom(Guest from);
	FriendshipRequest findById(Long id);
	List<FriendshipRequest> findByTo(Guest to);

}
