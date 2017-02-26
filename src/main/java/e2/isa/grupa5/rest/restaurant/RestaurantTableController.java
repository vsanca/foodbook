package e2.isa.grupa5.rest.restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.restaurant.RestaurantTableDTO;
import e2.isa.grupa5.model.shifts.ShiftBartender;
import e2.isa.grupa5.model.shifts.ShiftWaiter;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;
import e2.isa.grupa5.repository.shifts.ShiftWaiterRepository;
import e2.isa.grupa5.repository.waiter.WaiterRepository;
import e2.isa.grupa5.service.restaurant.RestaurantTableService;

/**
 * Funkcionalnost 2.3:
 * - upravljanje rasporedom sedenja
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/restaurants/tables")
public class RestaurantTableController {
	
	@Autowired
	RestaurantTableService restaurantTableService;
	
	@Autowired
	WaiterRepository waiterRepository;
	
	@Autowired
	ShiftWaiterRepository shiftWaiterRepository;
	
	@Autowired
	RestaurantTableRepository restaurantTableRepository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody RestaurantTableDTO rtDTO) {
		
		RestaurantTable rt = restaurantTableService.create(rtDTO);
		
		if(rt != null) {
			return new ResponseEntity<>(rt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getForRestaurant/{rId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getForRestaurant(@PathVariable long rId) {
		
		return new ResponseEntity<>(restaurantTableService.findAllByRestaurantId(rId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getForWaiter/{wId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getForWaiter(@PathVariable long wId) {
		Waiter w = waiterRepository.findById(wId);
		
		return new ResponseEntity<>(restaurantTableService.findAllByRestaurantId(w.getRestaurant().getId()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getForManager/{mId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getForManager(@PathVariable long mId) {
		
		return new ResponseEntity<>(restaurantTableService.findAllByManagerId(mId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/{mId}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity completeUpdateAndReturn(@PathVariable long mId, @RequestBody RestaurantTableDTO[] tDTOs){
		
		System.out.println("STOLOVI DTO:");
		System.out.println(tDTOs.length);
		for(int i=0; i<tDTOs.length; i++) {
			System.out.println("id: "+tDTOs[i].getId());
			System.out.println("name: "+tDTOs[i].getName());
			System.out.println("seats: "+tDTOs[i].getSeats());
			System.out.println("area: "+tDTOs[i].getArea());
			System.out.println("fabric: "+tDTOs[i].getFabricTable());
		}
		
		restaurantTableService.updateAllTables(Arrays.asList(tDTOs));
		
		return new ResponseEntity<>(restaurantTableService.findAllByManagerId(mId), HttpStatus.OK);
	}
	// VRACA Potrebne stolove
	@RequestMapping(value = "/getForWaiterIdAllTables/{wId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getForWaiterIdAllTables(@PathVariable long wId) {
		// pronalazi konobara na osnovu ID
		Waiter w = waiterRepository.findById(wId);
		ShiftWaiter myShift = null;
		// SVE SMENE ZA KONOBARA
		List<ShiftWaiter> myAllShiftsWt = shiftWaiterRepository.findByWaiter_Id(w.getId());
		//ako ne postoji ni 1 smena, cao
		if(myAllShiftsWt == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// ako postoje smene, proverava datuma, za sad samo je l taj datum, posle mogu da zamenim staru metodu
		else{
			Date now = new Date();
			for(ShiftWaiter shW1 : myAllShiftsWt ){
				if(shW1.getShift().getDay().getDay() == now.getDay()){
					myShift = shW1;
				}
			}
			// ako postoji ni 1 smena cao
			if(myShift == null){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			// ako postoji smena za danasnji datum
			else{
				// kupi sve oblasti iz smene
				List<RestaurantArea> allAreas = (List<RestaurantArea>) myShift.getAreas();
				if(allAreas == null){
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				// ako oblast/oblasti postoje traze se stolovi
				else{
					// kupi sve stolove, ako ne postoji ni 1 sto, cao
					List<RestaurantTable> allTables = restaurantTableRepository.findAll();
					if(allTables == null){
						
					}
					else{
						// od svih stolova, kupi samo one koji pripadaju oblastima smene
						List<RestaurantTable> myTables = new ArrayList<RestaurantTable>();
						for(RestaurantTable rt1 : allTables){
							for(RestaurantArea ra1 : allAreas){
								if(ra1.getId() == rt1.getArea().getId()){
									myTables.add(rt1);
								}
							}
						}
						// na kraju, ako nema ni 1 sto u smeni, cao
						if(myTables.isEmpty()){
							return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
						}
						// vraca odgovarajuce stolove
						else{
							return new ResponseEntity<>(myTables, HttpStatus.OK);
						}
					}
				}
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
