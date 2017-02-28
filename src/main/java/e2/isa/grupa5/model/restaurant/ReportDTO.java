package e2.isa.grupa5.model.restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO for passing data for report REST services.
 * 
 * @author Viktor
 *
 */
public class ReportDTO {
	
	private Date start_interval;
	private Date end_interval;
	private String type;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
