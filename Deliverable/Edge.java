//import java.util.*;

// Edge between two nodes
public class Edge {
	
	private int distance;
	private Node tail;
	private Node head;
	private EdgeType edgeType;
	
	public Edge(Node tailNode, Node headNode, int dist) {
		distance = dist;
		tail = tailNode;
		head = headNode;
	}
	
	public Node getTail() {
		return tail;
	}
	
	public Node getHead() {
		return head;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setTail(Node newTail) {
		tail = newTail;
	}
	
	public void setHead(Node newHead) {
		head = newHead;
	}
	
	public void setDistance(int dist) {
		distance = dist;
	}
	
	public EdgeType getEdgeType() {
		return edgeType;
	}
	
	public void setEdgeType(EdgeType edgeType) {
		this.edgeType = edgeType;
	}
}
