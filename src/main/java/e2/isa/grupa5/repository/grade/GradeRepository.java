package e2.isa.grupa5.repository.grade;



import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.users.UserRoles;


/**
 * 
 * @author Boris
 *
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {
	
	public Grade findById(long id);
	
	
}
