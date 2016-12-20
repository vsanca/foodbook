package e2.isa.grupa5.model;

import e2.isa.grupa5.model.users.Guest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Restaurant")
public class FrendshipRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="frendship_request_id")
	private Long id; 
	
	@Column(name="frendship_request_confirmed")
	private boolean confirmed; 
	
	@OneToMany
	private Guest from;
	
	@OneToMany
	private Guest to; 
	
	public FrendshipRequest() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Guest getFrom() {
		return from;
	}

	public void setFrom(Guest from) {
		this.from = from;
	}

	public Guest getTo() {
		return to;
	}

	public void setTo(Guest to) {
		this.to = to;
	}
	////////////////////////////////////////
	///// create, delete, update, read    CRUD
}
