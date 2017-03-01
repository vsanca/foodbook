package e2.isa.grupa5.rest.dto.guest;

public class GuestOrderDTO {

	private String menuItemName;
	private long menuItemId;
	private boolean bePrepared;


	public void setMenuItemName(String name) {
		this.menuItemName = name;;
		
	}

	public String getMenuItemName() {
		return menuItemName;
	}


	public boolean isBePrepared() {
		return bePrepared;
	}

	public void setBePrepared(boolean bePrepared) {
		this.bePrepared = bePrepared;
	}

	public long getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(long menuItemId) {
		this.menuItemId = menuItemId;
	}
	
	

}
