package e2.isa.grupa5.service;

/*import e2.isa.grupa5.model.FriendshipRequest;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.repository.FriendshipRequestRepository;
import e2.isa.grupa5.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.stereotype.Service;

import java.util.List;

/*
@Service
public class FriendshipRequestService {

	@Autowired 
	private FriendshipRequestRepository friendshipRepository;
	
	@Autowired 
	private GuestRepository guestRepository; 

		public List<FriendshipRequest> findSentFriendshipRequests(Long id) {
			Guest guest = guestRepository.findOne(id);
			return friendshipRepository.findByFrom(guest);
		}

		public FriendshipRequest sendFriendshipRequest(Long fromId, Long toId) {
			Guest guest1 = guestRepository.findOne(fromId); 
			Guest guest2 = guestRepository.findOne(toId); 
			FriendshipRequest friendshipRequest = new FriendshipRequest(); 
			friendshipRequest.setFrom(guest1);
			friendshipRequest.setTo(guest2);
			friendshipRepository.save(friendshipRequest); 
			return friendshipRequest;
		}

		public boolean  deleteFriendshipRequest(Long id) {
			try{
				friendshipRepository.delete(id);
				return true; 
			}catch(Exception e) {
				e.printStackTrace();
				return false; 
			}	
		}

		public boolean confirmFriendshipRequest(Long id) {
			try{
				FriendshipRequest friendshipRequest = friendshipRepository.findOne(id); 
				Guest guestTo = friendshipRequest.getTo(); 
				Guest guestFrom = friendshipRequest.getFrom(); 
				guestTo.getFriends().add(guestFrom);
				guestFrom.getFriends().add(guestTo); 
				guestRepository.save(guestFrom); 
				guestRepository.save(guestTo); 
				friendshipRepository.delete(friendshipRequest);
				return true; 
			}catch(Exception e) {
				e.printStackTrace();
				return false; 
			}
		}

}*/
