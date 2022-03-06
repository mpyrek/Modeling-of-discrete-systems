import java.util.ArrayList;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
		//TODO: insert logic which updates according to currentState and 
		//number of active neighbors
		int counter = countActiveNeighbors();
		if (this.getState()==0 && counter==3) this.nextState=1;
		else if (this.getState()==1 && !(counter==2 || counter==3)) this.nextState=0;
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
	public int countActiveNeighbors() {
		int counter = 0;
		for (Point neighbor : this.neighbors) {
			if (neighbor.getState()==1) counter+=1;
		}
		return counter;
	}
}
