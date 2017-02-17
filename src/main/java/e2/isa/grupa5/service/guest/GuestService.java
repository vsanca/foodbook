package e2.isa.grupa5.service.guest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.repository.guest.InvitedToReservationRepository;
import e2.isa.grupa5.repository.guest.ReservationRepository;
import e2.isa.grupa5.rest.dto.guest.ProfilePageDTO;


@Service
@Transactional
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private InvitedToReservationRepository invitedToReservationRepository;
  
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    
    
    public Guest findOne(Long id) {
        return guestRepository.findOne(id);
    }

    
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    
    public Guest delete(Long id) {
        Guest guest = guestRepository.findOne(id);

        if(guest != null){
            guestRepository.delete(guest);
        }

        return guest;
    }


	public ProfilePageDTO getProfilePageInfo(Long id) {
		ProfilePageDTO dto = new ProfilePageDTO();
		
		Guest guest = guestRepository.findOne(id);
		if(guest == null) {
			return null;
		}
		dto.setName(guest.getName());
		dto.setSurname(guest.getSurname());
		dto.setAddress(guest.getAddress());
		long numberOfVisits = 0;
		numberOfVisits += reservationRepository.countByGuest(guest);
		numberOfVisits += invitedToReservationRepository.countByGuest(guest);
		dto.setNumberOfVisits(numberOfVisits);
		List<Guest> friends = guest.getFriends();
		List<ProfilePageDTO> friendsDTO = new ArrayList<>();
		for(Guest friend : friends) {
			ProfilePageDTO friendDTO = new ProfilePageDTO();
			friendDTO.setName(friend.getName());
			friendDTO.setSurname(friend.getSurname());
			friendsDTO.add(friendDTO);
			
		}
		dto.setFriends(friendsDTO);
		return dto;
	}
}
