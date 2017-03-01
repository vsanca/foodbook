package e2.isa.grupa5.rest.dto.guest;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;

import e2.isa.grupa5.model.reservation.GuestReservationOrder;
import e2.isa.grupa5.repository.reservation.GuestReservationOrderRepository;

public class GuestReservationOrderDTO {

	@Autowired
	GuestReservationOrderRepository guestReservationOrderRepository;
	
	private Long myId;
	private String itemName;
	private String itemImgLink;
	private long itemId;
	private long waiterId;
	private long chefId;
	private long bartenderId;
	private Long reservationId;
	private String itemType;
	private boolean bePrepared;
	private boolean isAccepted;
	private boolean isCreated;
	private boolean isDelivered;
	
	public GuestReservationOrderDTO(GuestReservationOrder order){
		this.myId = order.getId();
		if(order.getItem() != null){
		this.itemName = order.getItem().getItem().getName();
		this.itemImgLink = order.getItem().getItem().getImage_link();
		this.itemId = order.getItem().getItem().getId();
		this.itemType = order.getItem().getItem().getItemType().getName();
		}
		else{
			this.itemName = "";
			this.itemImgLink = "";
			int randomNum = 0;
			this.itemId = randomNum;
			this.itemType = "";
		}
		if(order.getWaiter() != null){
			this.waiterId = order.getWaiter().getId();
		}
		else{
			this.waiterId = 0;
		}
		
		if(order.getChef() != null){
			this.chefId = order.getChef().getId();
		}
		else{
			this.chefId = 0;
		}
		
		
		if(order.getBartender() != null){
			this.bartenderId = order.getBartender().getId();
		}
		else{
			this.bartenderId = 0;
		}
		
		this.reservationId = order.getReservation().getId();
		
		this.bePrepared = order.isBePrepared();
		this.isAccepted = order.isAccepted();
		this.isCreated = order.isCreated();
		this.isDelivered = order.isDelivered();
	}

	
	
	public Long getMyId() {
		return myId;
	}

	public void setMyId(Long myId) {
		this.myId = myId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(long waiterId) {
		this.waiterId = waiterId;
	}

	public long getChefId() {
		return chefId;
	}

	public void setChefId(long chefId) {
		this.chefId = chefId;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public boolean isBePrepared() {
		return bePrepared;
	}

	public void setBePrepared(boolean bePrepared) {
		this.bePrepared = bePrepared;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public boolean isCreated() {
		return isCreated;
	}

	public void setCreated(boolean isCreated) {
		this.isCreated = isCreated;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}



	public GuestReservationOrderRepository getGuestReservationOrderRepository() {
		return guestReservationOrderRepository;
	}



	public void setGuestReservationOrderRepository(GuestReservationOrderRepository guestReservationOrderRepository) {
		this.guestReservationOrderRepository = guestReservationOrderRepository;
	}



	public String getItemImgLink() {
		return itemImgLink;
	}



	public void setItemImgLink(String itemImgLink) {
		this.itemImgLink = itemImgLink;
	}



	public long getBartenderId() {
		return bartenderId;
	}



	public void setBartenderId(long bartenderId) {
		this.bartenderId = bartenderId;
	}
	
	
	
}
