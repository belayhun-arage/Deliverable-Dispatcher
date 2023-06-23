package p2.nm8742;

import java.time.LocalDateTime;
import java.util.Date;
import edu.metrostate.ics372.p2.Event;

public class MyEvent implements Event{
	private String callerID;
	private int ambulanceID;
	private Date eventTime;
	private Priority priority;
	
	public MyEvent(String callerID, int ambulanceID, Date eventTime) {
		super();
		this.callerID = callerID;
		this.ambulanceID = ambulanceID;
		this.eventTime = eventTime;
	}

	@Override
	public LocalDateTime getAvailTime() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getCallID() {
		// TODO Auto-generated method stub
		return callerID;
	}
	@Override
	public LocalDateTime getCallTime() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDispatchDelayInMinutes() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public LocalDateTime getDispatchTime() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Priority getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}
	@Override
	public int getAssignedAmbulanceID() {
		// TODO Auto-generated method stub
		return ambulanceID;
	}
	

}
