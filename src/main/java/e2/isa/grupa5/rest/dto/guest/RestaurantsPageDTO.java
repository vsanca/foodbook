package e2.isa.grupa5.rest.dto.guest;

public class RestaurantsPageDTO {
	private String name;
	private DistanceDTO distance;
	private int rating;
	private int friendsRating;
	private long id;
	
	public RestaurantsPageDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DistanceDTO getDistance() {
		return distance;
	}

	public void setDistance(DistanceDTO distance) {
		this.distance = distance;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getFriendsRating() {
		return friendsRating;
	}

	public void setFriendsRating(int friendsRating) {
		this.friendsRating = friendsRating;
	}

	public void setRestaurantId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
