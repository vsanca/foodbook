package e2.isa.grupa5.service;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Viktor on 12/21/2016.
 */

@Service
@Transactional
public class GuestService implements IGuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Guest findOne(Long id) {
        return guestRepository.findOne(id);
    }

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest delete(Long id) {
        Guest guest = guestRepository.findOne(id);

        if(guest != null){
            guestRepository.delete(guest);
        }

        return guest;
    }
}
