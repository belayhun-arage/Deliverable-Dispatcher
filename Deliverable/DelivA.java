import java.io.*;
import java.util.Collections;
import java.util.Comparator;

public class DelivA {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	//Constructor - DO NOT MODIFY
	public DelivA(File in, Graph gr) {
		inputFile = in;
		graph = gr;
		
		// Set up for writing to a file
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 ); // Strip off ".txt"
		String outputFileName = baseFileName.concat( "_out.txt" );
		outputFile = new File( outputFileName );
		if ( outputFile.exists() ) {    // For retests
			outputFile.delete();
		}
				
		try {
			output = new PrintWriter(outputFile);			
		}
		catch (Exception x ) { 
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		
		// Calls the method that will do the work of deliverable A
		runDelivA();
		output.println("New File Generated" + outputFile);
		output.flush();
	}

	//*********************************************************************************
	//               This is where your work starts
	
	private void runDelivA() {
		displayIndegrees();
		displayOutdegrees();
	}
	
	//display all incoming edges 
	public void displayIndegrees() {
		Collections.sort(graph.getNodeList(),new IndegreesComparator());
		System.out.println("Indegrees : \n");
		for(Node node:graph.getNodeList()) {
			System.out.println("Node  " + node.getAbbrev() +" has "  + node.getIncomingEdges().size() + "\n");
		}
	}
	
	//display all outgoing edges 
	public void displayOutdegrees() {
		Collections.sort(graph.getNodeList(),new OutdegreesComparator());
		System.out.println("Outdegrees : \n");
		for(Node node:graph.getNodeList()) {
			System.out.println("Node "+node.getAbbrev() + " has " + node.getOutgoingEdges().size() + "\n");
		}
	}
	
	public class IndegreesComparator implements Comparator<Node>{

		@Override
		public int compare(Node node1,Node node2) {
			int indegreeComparator = Integer.compare(node2.getIncomingEdges().size(), node1.getIncomingEdges().size());
			if(indegreeComparator!=0) {
				return indegreeComparator;
			}
			else {
				return node1.getAbbrev().compareTo(node2.getAbbrev());
			}
		}
		
	}
	
	public class OutdegreesComparator implements Comparator<Node>{

		@Override
		public int compare(Node node1,Node node2) {
			int outdegreeComparator = Integer.compare(node2.getOutgoingEdges().size(), node1.getOutgoingEdges().size());
			if(outdegreeComparator!=0) {
				return outdegreeComparator;
			}
			else {
				return node1.getAbbrev().compareTo(node2.getAbbrev());
			}
		}
		
	}
}

