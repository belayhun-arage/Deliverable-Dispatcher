package p2.nm8742;
import edu.metrostate.ics372.p2.Event;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class AmbulanceDispatcher {
	
//	Constructor
	public AmbulanceDispatcher() {}

	public static void main(String[] args) {

		AmbulanceDispatcher ambulanceDispatcher = new AmbulanceDispatcher();
		// Create a file chooser dialog
		JFileChooser fileChooser = new JFileChooser();

		// Show the dialog and wait for the user's selection
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			
			//accept the number of ambulances from the user
			Scanner scanner = new Scanner(System.in);
	        int userInput;

	        do {
	            System.out.print("Enter number of ambulances greater than 1: ");
	            userInput = scanner.nextInt();

	            if (userInput < 1) {
	                System.out.println("Invalid input. Please try again.");
	            }
	        } while (userInput <1);

	        // user input accepted
	        System.out.println("NUmber of ambulances: " + userInput);
			// Get the selected file
			

			java.io.File selectedFile = fileChooser.getSelectedFile();
	
			
//			Make sure to pass number of ambulances to be grater or equsl to 1
			Event[] events=ambulanceDispatcher.processEvents(userInput, selectedFile.toString());
			
//			Display the output for process event
			for (int i=0;i<events.length;i++) {
				System.out.println("Event with caller ID : " +events[i].getCallID() + " Ambulance ID :" + events[i].getAssignedAmbulanceID());
			}
		}
	}

	public static void loadAmbulances(List<Ambulance> ambulances) {
		ambulances = ambulances;
	}


//	Read call recording history from the scenario file
	public static List<CallRecord> readItemsFromFile(String fileName) {

		List<CallRecord> records = new ArrayList<>();
		String pattern = "yyyy-MM-dd'T'HH:mm";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] details = line.split("\\|");
				String time = details[0];
				String callID = details[1];
				String priority = details[2];
				int duration = Integer.parseInt(details[3]);

				LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
				Date date = java.sql.Timestamp.valueOf(dateTime);

				CallRecord item = new CallRecord(date, callID, Priority.valueOf(priority), duration);
				records.add(item);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}

//	check for ambulance availability
	private static Ambulance getAvailableAmbulance(List<Ambulance> ambulances) {
		for (Ambulance ambulance : ambulances) {
			if (ambulance.getIsAvailable()) {
				return ambulance;
			}
		}
		return null;
	}

//	Processing Events
	public static Event[] processEvents(int numAmbulances, String scenarioFilename) {
		List<Ambulance> ambulances = new ArrayList<>();
		List<CallRecord> records   = readItemsFromFile(scenarioFilename);
		
		//Size equal with number of loaded call records
		Event[] events=new Event[records.size()];
		
		//check for the preconditions
		if(numAmbulances<1) {
			throw new IllegalArgumentException("Number of ambulances must be greate or equal to one");
		}
		File scenarioFile = new File(scenarioFilename);
		if(!scenarioFile.exists()||!scenarioFile.canRead()) {
			throw new IllegalArgumentException("File must be readable");
		}
		for (int i = 1; i <= numAmbulances; i++) {
			Ambulance ambulance = new Ambulance(i);
			ambulances.add(ambulance);
		}
		
		PriorityQueue<CallRecord> priorityQueue = new PriorityQueue<>((c1, c2) -> {
			if (c1.getPriority() != c2.getPriority()) {
				return c2.getPriority().compareTo(c1.getPriority());
			} else if (!c1.getCallRecievedTime().equals(c2.getCallRecievedTime())) {
				return c1.getCallRecievedTime().compareTo(c2.getCallRecievedTime());
			} else {
				return c1.getCallID().compareTo(c2.getCallID());
			}
		});

		priorityQueue.addAll(records);
		
		while (!priorityQueue.isEmpty()) {
			CallRecord call = priorityQueue.poll();
			Ambulance availableAmbulance = getAvailableAmbulance(ambulances);
			
//          Check for ambulance availability
			if (availableAmbulance != null) {
				availableAmbulance.assignAmbulance(call.getDuration());
				
//				Create a new event object add add to the events array
				events[availableAmbulance.getAmbulanceID()]=new MyEvent(call.getCallID(), availableAmbulance.getAmbulanceID(), new Date());
				System.out.println("Event with caller ID : " +call.getCallID() + " Ambulance ID :" + availableAmbulance.getAmbulanceID());
			} else {
				priorityQueue.offer(call);
			}
		}
		return events;
	}
}

