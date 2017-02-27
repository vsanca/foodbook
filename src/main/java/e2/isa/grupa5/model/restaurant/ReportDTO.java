package e2.isa.grupa5.model.restaurant;

import java.util.Date;

/**
 * DTO for passing data for report REST services.
 * 
 * @author Viktor
 *
 */
public class ReportDTO {
	
	private Date start_interval;
	private Date end_interval;
	
	public ReportDTO() {
		
	}
	
	public Date getStart_interval() {
		return start_interval;
	}
	
	public void setStart_interval(Date start_interval) {
		this.start_interval = start_interval;
	}
	
	public Date getEnd_interval() {
		return end_interval;
	}
	
	public void setEnd_interval(Date end_interval) {
		this.end_interval = end_interval;
	}
	
	
}
