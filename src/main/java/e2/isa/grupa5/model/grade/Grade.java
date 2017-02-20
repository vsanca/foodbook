package e2.isa.grupa5.model.grade;

import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.users.User;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="grade")
public class Grade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grade_id")
	private Long id;

	@ManyToOne
	private User user;

	@Column(name="grade_date")
	private Date date;

	@Column(name="grade_value")
	private int value;

	@ManyToOne
	private Reservation reservation;

	public Grade() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
