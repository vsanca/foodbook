package e2.isa.grupa5.rest.dto.guest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 */
public class CreateNewReservationDTO {

	private boolean success;
	private String errorInfo;

	private List<Long> invitedFriends = new ArrayList<>();
	private long guestId;
	private long restaurantId;
	private List<Long> tables = new ArrayList<>();
	private Date date;
	private Date arrival;
	private int duration;
	
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public List<Long> getInvitedFriends() {
		return invitedFriends;
	}
	public void setInvitedFriends(List<Long> invitedFriends) {
		this.invitedFriends = invitedFriends;
	}
	public long getGuestId() {
		return guestId;
	}
	public void setGuestId(long guestId) {
		this.guestId = guestId;
	}
	public long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public List<Long> getTables() {
		return tables;
	}
	public void setTables(List<Long> tables) {
		this.tables = tables;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
