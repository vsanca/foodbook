package e2.isa.grupa5.service;

import e2.isa.grupa5.model.users.Guest;

import java.util.List;

/**
 * Created by Viktor on 12/21/2016.
 */

public interface IGuestService {
    List<Guest> findAll();

    Guest findOne(Long id);

    Guest save(Guest guest);

    Guest delete(Long id);
}
