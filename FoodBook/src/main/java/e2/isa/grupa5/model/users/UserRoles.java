package e2.isa.grupa5.model.users;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRoles {
	GUEST(0), 
	CHEF(1), 
	WAITER(2), 
	BARTENDER(3), 
	MANAGER(4), 
	SYS_MANAGER(5), 
	BIDDER(6);
	
	private int value;
	
	private UserRoles(int value) {
		this.value = value;
	}
	
	 @JsonValue
	 public int getValue() {
	        return value;
	 }
	 
	 public void setValue(int value) {
		 this.value = value;
	 }
	 
}