package e2.isa.grupa5.service.systemmanager;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import e2.isa.grupa5.model.users.SystemManager;
import e2.isa.grupa5.model.users.SystemManagerDTO;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;
import e2.isa.grupa5.service.UserService;

/**
 * 
 * @author Juri
 *
 */
@Service
public class SystemManagerService {
	
	@Autowired
	SystemManagerRepository systemManagerRepository;
	
	@Autowired
	UserService userService;
	
	
	public SystemManager updateData(SystemManagerDTO sysManDTO) {
		SystemManager sysMan = systemManagerRepository.findById(sysManDTO.getId());
		
		userService.setVariableAttributes(sysMan, sysManDTO);
		
		
		try {
			sysMan = systemManagerRepository.save(sysMan);
			return sysMan;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
		
	
}