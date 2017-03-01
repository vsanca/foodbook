package e2.isa.grupa5.rest.dto.guest;

public class SendFriendshipRequestDTO {
	private long friendId; 
	private long guestId;
	
	public long getFriendId() {
		return friendId;
	}
	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}
	public long getGuestId() {
		return guestId;
	}
	public void setGuestId(long guestId) {
		this.guestId = guestId;
	} 
	
}
