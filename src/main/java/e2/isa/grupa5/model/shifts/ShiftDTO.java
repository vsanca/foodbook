package e2.isa.grupa5.model.shifts;

import java.io.Serializable;
import java.util.Date;

/**
 * {@link Shift} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - raspored smena
 * 
 * @author Viktor
 *
 */
public class ShiftDTO implements Serializable{

	private long workerId;
	private String workerType;
	private Date startDate;
	private Date endDate;
	private String startTime;
	private String endTime;
	private long areaId;
	private long restaurantId;
	
	public ShiftDTO() {
		
	}

	public ShiftDTO(long workerId, String workerType, Date startDate, Date endDate, String startTime, String endTime,
			long areaId) {
		this.workerId = workerId;
		this.workerType = workerType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.areaId = areaId;
	}

	public long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}

	public String getWorkerType() {
		return workerType;
	}

	public void setWorkerType(String workerType) {
		this.workerType = workerType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public long getAreaId() {
		return areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	
}
