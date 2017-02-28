package e2.isa.grupa5.rest.dto.guest;

import java.util.List;

/**
 * 
 * @author Jelena Jankovic
 *
 */
public class ReservationDetailsDTO {

	private boolean confirmed;
	private List<GuestOrderDTO> guestOrders;
	private List<GuestOrderDTO> allOrders;
	private boolean success;

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
