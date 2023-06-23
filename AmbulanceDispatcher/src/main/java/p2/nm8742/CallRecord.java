package p2.nm8742;

import java.util.Date;

public class CallRecord {
	private Date callRecievedTime;
	private String callID;
	private Priority priority;
	private int duration;
	
	public CallRecord(Date callReceivedTime,String callID,Priority priority,int duration) {
		this.callRecievedTime=callReceivedTime;
		this.callID = callID;
		this.priority = priority;
		this.duration = duration;
	}

	public Date getCallRecievedTime() {
		return callRecievedTime;
	}

	public void setCallRecievedTime(Date callRecievedTime) {
		this.callRecievedTime = callRecievedTime;
	}

	public String getCallID() {
		return callID;
	}

	public void setCallID(String callID) {
		this.callID = callID;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}
