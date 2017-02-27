package e2.isa.grupa5.rest.restaurant;

import java.text.SimpleDateFormat;
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
import e2.isa.grupa5.model.shifts.Shift;
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
	
	
	@RequestMapping(value = "/currentTables/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getTablesForNowByWaiterId(@PathVariable long id) {
		
		Waiter wt = waiterRepository.findById(id);
		List<ShiftWaiter> wtShifts = shiftWaiterRepository.findByWaiter_Id(id);
		List<RestaurantArea> areasForNow = new ArrayList<RestaurantArea>();
		
		for(ShiftWaiter shW: wtShifts){	
			Shift sh = shW.getShift();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date shiftDate = sh.getDay();
			String shDate = sdf.format(shiftDate);
			String[] parsedDate = shDate.split("-");
			String shMonth = parsedDate[1];
			String shDay = parsedDate[2];
			
			Date now = new Date();
			String nowDate = sdf.format(now);
			String[] nowParsedDate = nowDate.split("-");
			String nowMonth = nowParsedDate[1];
			String nowDay = nowParsedDate[2];
			
			if((shMonth.equals(nowMonth)) && (shDay.equals(nowDay))){
				areasForNow.addAll(shW.getAreas());
			}
		}
		
		List<RestaurantTable> allTables = restaurantTableRepository.findAll();
		if(allTables == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<RestaurantTable> myTables = new ArrayList<RestaurantTable>();
		for(RestaurantTable rt : allTables){
			for(RestaurantArea ra : areasForNow){
				if(rt.getArea().getId() == ra.getId()){
					myTables.add(rt);
				}
			}
		}
		
		if(myTables.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else{
			return new ResponseEntity<>(myTables, HttpStatus.OK);
		}
		
	}
	
}
