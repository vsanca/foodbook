package e2.isa.grupa5.rest.grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.service.grade.GradeService;

/**
 * 
 * @author Boris
 * 
 *
 */
@RestController
@RequestMapping("/grade")
public class GradeController {
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	GradeService gradeService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getById(@PathVariable long id) {
		
		Grade g = gradeRepository.findById(id);
		
		if(g != null) {
			return new ResponseEntity<>(g, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/allGradesRestaurant/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getGradesByRestaurantId(@PathVariable long id) {
		
		Restaurant r = restaurantRepository.findById(id);
		
		List<Grade> allGrades = gradeService.getGradesForRestaurant(r);
		
		if(allGrades != null) {
			return new ResponseEntity<>(allGrades, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
