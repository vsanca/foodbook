package e2.isa.grupa5.rest.dto.guest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Jelena Jankovic
 *
 */
public class ReservationDetailsDTO {

	private boolean confirmed;
	private List<GuestOrderDTO> guestOrders = new ArrayList<>();
	private List<GuestOrderDTO> allOrders = new ArrayList<>();
	private boolean success;
	private Date terminOd;
	
	private long guestId;
	
	

	public long getGuestId() {
		return guestId;
	}

	public void setGuestId(long guestId) {
		this.guestId = guestId;
	}

	public Date getTerminOd() {
		return terminOd;
	}

	public void setTerminOd(Date terminOd) {
		this.terminOd = terminOd;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
		
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setGuestOrders(List<GuestOrderDTO> guestOrdersDto) {
		this.guestOrders = guestOrdersDto;
		
	}

	public List<GuestOrderDTO> getGuestOrders() {
		return guestOrders;
	}

	public List<GuestOrderDTO> getAllOrders() {
		return allOrders;
	}

	public void setAllOrders(List<GuestOrderDTO> allOrders) {
		this.allOrders = allOrders;
	}

	public void setSuccess(boolean b) {
		success = b;
		
	}

	public boolean isSuccess() {
		return success;
	}
	
	
	
	

}
