package e2.isa.grupa5.rest.dto.guest;





import e2.isa.grupa5.model.reservation.GuestReservationOrder;


public class GuestReservationOrderDTO {

	
	
	private Long myId;
	private String itemName;
	private String itemImgLink;
	private long itemId;
	private long waiterId;
	private long chefId;
	private long bartenderId;
	private Long reservationId;
	private String itemType;
	private boolean bePrepared = false;
	private boolean accepted = false;
	private boolean created = false;
	private boolean delivered = false;
	
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
			this.itemId = 0;
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
		this.accepted = order.isAccepted();
		this.created = order.isCreated();
		this.delivered = order.isDelivered();
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

	public String getItemImgLink() {
		return itemImgLink;
	}

	public void setItemImgLink(String itemImgLink) {
		this.itemImgLink = itemImgLink;
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

	public long getBartenderId() {
		return bartenderId;
	}

	public void setBartenderId(long bartenderId) {
		this.bartenderId = bartenderId;
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
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	
	
		
}
