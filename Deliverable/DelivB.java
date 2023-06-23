import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class DelivB {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;
	int time;
	

	// Constructor - DO NOT MODIFY
	public DelivB(File in, Graph gr) {
		inputFile = in;
		graph = gr;

		// Set up for writing to a file
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 );
		String outputFileName = baseFileName.concat( "_out.txt" );
		outputFile = new File( outputFileName );
		if ( outputFile.exists() ) {    // For retests
					outputFile.delete();
				}
		try {
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}

		// Calls the method that will do the work of deliverable B
		runDelivB();
		output.println("New File Generated" + outputFile);
		output.flush();
	}

	// *********************************************************************************
	// This is where your work starts

	private void runDelivB() {
		System.out.println("Dfs of graph \n");// Prints to console
		dfs(graph.getNodeList());
		discoveryFinishTime();
		System.out.println("Edge Classification \n");// Prints to console
		edgeType();

	}

//	Start DFS
	public void dfs(ArrayList<Node> nodes) {
		Node startingNode=null;
		
//		set all nodes unvisited by giving white color
		for (Node node : nodes) {
			node.setColor(Color.WHITE);
			if (node.getValue().contains("S")) {
				startingNode=node;
			}
		}
		int time = 0;
		
//		Start from the starting node
		DFS_visite(startingNode);
		for (Node node : nodes) {
			if (node.getColor() == Color.WHITE) {
				DFS_visite(node);
			}
		}
		
	}

//Visit unvisited nodes and set their discovery and finish time
	public void DFS_visite(Node node) {
		time = time + 1;
		node.setDiscoveryTime(time);
		node.setColor(Color.GRAY);
		
		ArrayList<Edge> edges = node.getOutgoingEdges();
		Collections.sort(edges,new EdgeComparator());
		for (Edge edge : edges) {
			if (edge.getHead().getColor() == Color.WHITE) {
				edge.setEdgeType(EdgeType.TREE);
				DFS_visite(edge.getHead());
			}else {
				edge.setEdgeType(EdgeType.FORWARD);
			}
			
		}
		
//		Set the node as visited
		node.setColor(Color.BLACK);
		time = time + 1;
		node.setFinishTime(time);
	}
	
//	Compare nodes using abbrevation
	public class NodeComparator implements Comparator<Node>{

		@Override
		public int compare(Node node1,Node node2) {
			return node1.getAbbrev().compareToIgnoreCase(node2.getAbbrev());
		}
		
	}

//	Compare Edges with their distance and if they have the same distance compare using their abrev
	public class EdgeComparator implements Comparator<Edge>{
		
		@Override
		public int compare(Edge edge1,Edge edge2) {
			int edgeComparator = Integer.compare(edge1.getDistance(), edge2.getDistance());
			if(edgeComparator!=0) {
				return edgeComparator;
			}
			else {
				return edge2.getHead().getAbbrev().compareTo(edge1.getHead().getAbbrev());
			}
		}
		
	}
	
//Identify the discovery and finish time for each node
	public void discoveryFinishTime() {
		ArrayList<Node> nodes =new ArrayList<Node>(graph.getNodeList());
		Collections.sort(nodes,new NodeComparator());
		System.out.println("Node   " + "Disc   " + "Finish   ");
		for (Node node : nodes) {
			System.out.println(
					node.getAbbrev() + "   " + "   " + node.getDiscoveryTIme() + "   " + "   " + node.getFinishTime());
		}

	}

//	Identify the type of edges for edges according to the rule
	public void edgeType() { 
//		EDGE(U, V)
		for (Node u : graph.getNodeList()) {
			ArrayList<Edge> edges = u.getOutgoingEdges();
			edges.sort(Comparator.comparing((e->e.getHead().getAbbrev())));
			for (Edge v : edges) {

				if (u.getDiscoveryTIme() > v.getHead().getDiscoveryTIme() && u.getFinishTime() < v.getHead().getFinishTime()) {
					v.setEdgeType(EdgeType.BACK);
				}
				if (v.getHead().getDiscoveryTIme() < v.getHead().getFinishTime() && v.getHead().getFinishTime() < u.getDiscoveryTIme() && u.getDiscoveryTIme() < u.getFinishTime()) {
					v.setEdgeType(EdgeType.CROSS);
				}
			}
		}
		System.out.println("Edge   " + "  Type");
		for (Node node : graph.getNodeList()) {
			for (Edge edge : node.getOutgoingEdges()) {
				System.out.println(node.getAbbrev() + "=>" + edge.getHead().getAbbrev() + "   " + edge.getEdgeType());
			}
		}

	}

}
