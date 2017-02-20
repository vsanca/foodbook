package e2.isa.grupa5.rest.sysmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.SystemManager;
import e2.isa.grupa5.model.users.SystemManagerDTO;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;
import e2.isa.grupa5.service.UserService;
import e2.isa.grupa5.service.systemmanager.SystemManagerService;


/**
 * {@link SystemManager} REST services.
 * 
 * Funkcionalnost 2.9:
 * - postoji menadzer sistema koji moze da dodaje i druge menadzere sistema
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/sysmanager")
public class SystemManagerController {
	
	@Autowired
	SystemManagerRepository systemManagerRepository;
	
	@Autowired
	SystemManagerService systemManagerService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody SystemManagerDTO smDTO) {
		
		SystemManager sm = new SystemManager();
		userService.copyData(sm, smDTO);
		
		sm = systemManagerRepository.save(sm);
		
		return new ResponseEntity<>(sm, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity update(@RequestBody SystemManagerDTO smDTO) {
		
		SystemManager sm = systemManagerService.updateData(smDTO);
		
		if(sm != null) {
			return new ResponseEntity<>(sm, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity getAll() {
		
		return new ResponseEntity<>(systemManagerRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity get(@PathVariable long id) {
		
		SystemManager sm = systemManagerRepository.findById(id);
		
		if(sm != null) {
			return new ResponseEntity<>(sm, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
