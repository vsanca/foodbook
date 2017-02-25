package e2.isa.grupa5.rest.restaurant;

import java.util.ArrayList;
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

import e2.isa.grupa5.model.grade.GradeDTO;
import e2.isa.grupa5.model.restaurant.MenuDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.service.grade.GradeService;
import e2.isa.grupa5.service.restaurant.MenuService;
import e2.isa.grupa5.service.restaurant.RestaurantService;

/**
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	GradeService gradeService;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody RestaurantDTO rDTO) {
		
		Restaurant r = restaurantService.addRestaurant(rDTO);
		
		if(r != null) {
			
			MenuDTO mDTO = new MenuDTO();
			mDTO.setRestaurantId(r.getId());
			menuService.create(mDTO);
			
			return new ResponseEntity<>(r, HttpStatus.OK);
			
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity update(@RequestBody RestaurantDTO rDTO) {
		
		Restaurant r = restaurantService.editRestaurant(rDTO);
		
		if(r != null) {
			return new ResponseEntity<>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getById(@PathVariable long id) {
		
		Restaurant r = restaurantRepository.findById(id);
		
		if(r != null) {
			return new ResponseEntity<>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getAll() {
		
		List<Restaurant> all = restaurantRepository.findAll();
		List<RestaurantDTO> rDTOs = new ArrayList<>();
		
		for(Restaurant r : all) {
			RestaurantDTO rDTO = new RestaurantDTO(r);
			
			List<GradeDTO> restaurantGrades = gradeService.getGradesForRestaurant(r);
			
			rDTO.setGrades(restaurantGrades);
			rDTO.setGradeAtmosphere((int) restaurantService.calculateAtmosphereGrade(restaurantGrades));
			rDTO.setGradeMeal((int) restaurantService.calculateMealGrade(restaurantGrades));
			rDTO.setGradeWaiter((int) restaurantService.calculateWaiterGrade(restaurantGrades));
			
			rDTOs.add(rDTO);
		}
		
		return new ResponseEntity<>(rDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/manager/{mId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getForManager(@PathVariable long mId) {
		
		try {
			long rId = restaurantManagerRepository.findById(mId).getRestaurant().getId();
			Restaurant r = restaurantRepository.findById(rId);
			
			if(r != null) {
				return new ResponseEntity<>(r, HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
}
