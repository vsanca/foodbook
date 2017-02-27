package e2.isa.grupa5.rest.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.restaurant.ReportDTO;

/**
 * Mock data generation and retrieval for generating the report.
 * 
 * Funkcionalnost 2.3:
 * - prikaz izveštaja o poslovanju restorana
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/data")
public class MockDataService {
	
	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity generateData() {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity getRestaurantGrade(@PathVariable long id) {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/meal/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity getMealData(@PathVariable long id) {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/waiter/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity getWaiterData(@PathVariable long id) {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/revenue/{id}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity revenue(@PathVariable long id, @RequestBody ReportDTO rDTO) {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/revenueByWaiter/{id}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity revenueByWaiter(@PathVariable long id, @RequestBody ReportDTO rDTO) {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	

}
