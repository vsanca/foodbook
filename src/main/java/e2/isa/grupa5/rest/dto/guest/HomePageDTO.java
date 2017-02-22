package e2.isa.grupa5.rest.dto.guest;

public class HomePageDTO {
	private String date; 
	private int rating; 
	private String friends;
	private String restaurantName; 
	
	public HomePageDTO() {
		rating = 3; 
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}



	public void setRestaurantName(String restauranName) {
		this.restaurantName = restauranName;
		
	}



	public String getRestaurantName() {
		return restaurantName;
	}	
}
