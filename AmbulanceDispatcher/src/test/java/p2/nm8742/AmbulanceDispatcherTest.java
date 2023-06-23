package p2.nm8742;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import edu.metrostate.ics372.p2.Event;
import java.util.Date;
public class AmbulanceDispatcherTest {
	
	private AmbulanceDispatcher ambulanceDispatcher;
	
    @Before
    public void setUp() {
    	ambulanceDispatcher = new AmbulanceDispatcher();
    }
    
   //test for assigning free ambulances    
    @Test
    public void testForAssignAmbulances() {
        // Create sample data for testing
    	//Call Records
    	CallRecord callRecord1 = new CallRecord(new Date(), "Call 1",Priority.T1,  1);
    	CallRecord callRecord2 = new CallRecord(new Date(), "Call 2",Priority.T2,  2);
    	CallRecord callRecord3 = new CallRecord(new Date(), "Call 3",Priority.T3,  3);
    	CallRecord callRecord4 = new CallRecord(new Date(), "Call 4",Priority.T1,  4);
    	
        //Create two ambulances
    	Ambulance ambulance1 = new Ambulance(1);
    	Ambulance ambulance2 = new Ambulance(2);
    	
       //Events
        Event[] events = {
        		new MyEvent(callRecord1.getCallID(), ambulance1.getAmbulanceID(), new Date()),
        		new MyEvent(callRecord2.getCallID(), ambulance2.getAmbulanceID(), new Date()),
        		new MyEvent(callRecord3.getCallID(), ambulance1.getAmbulanceID(), new Date()),
        		new MyEvent(callRecord4.getCallID(), ambulance2.getAmbulanceID(), new Date())
        };

        // Verify the expected assignments
        assertEquals("Ambulance 1", events[0].getAssignedAmbulanceID());
        assertEquals("Ambulance 2", events[1].getAssignedAmbulanceID());
        assertEquals("Ambulance 1", events[2].getAssignedAmbulanceID());
        assertEquals("Ambulance 2", events[3].getAssignedAmbulanceID());
    }
    
    
}
