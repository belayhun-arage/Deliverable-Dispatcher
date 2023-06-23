package p2.nm8742;
import java.util.*;
public class Ambulance {
	private int ambulanceID;
	private boolean isAvailable;
	
	public Ambulance(int ambulanceID) {
		this.ambulanceID=ambulanceID;
		this.isAvailable = true;
	}


	public int getAmbulanceID() {
		return ambulanceID;
	}

	public void setAmbulanceID(int ambulanceID) {
		this.ambulanceID = ambulanceID;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
    public void assignAmbulance(int duration) {
        this.isAvailable = false;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	isAvailable = true;
                timer.cancel();
            }
        }, duration * 1000 * 60); 
    }
    
}
