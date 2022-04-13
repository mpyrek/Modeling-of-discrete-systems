import java.util.ArrayList;

import static java.lang.Integer.min;

public class Point {

	public ArrayList<Point> neighbors;
	public ArrayList<Point> neighbors2;
	public static Integer []types ={0,1,2,3};
	public int type;
	public int staticField;
	public boolean isPedestrian;
	public int type_of_neighborhood;
	boolean blocked =false;

	public Point() {
		type=0;
		staticField = 100000;
		neighbors= new ArrayList<Point>();
		neighbors2 = new ArrayList<Point>();
	}
	
	public void clear() {
		staticField = 100000;
		
	}

	public boolean calcStaticField() {
		int minimum = 1000000000;
		if (this.type_of_neighborhood == 1) {
			for (Point neighbor : neighbors) {
				minimum = min(neighbor.staticField,minimum);
			}
			if (minimum + 1< this.staticField) {
				this.staticField = minimum + 1;
				return true;
			}
			return false;
		} else {
			for (Point neighbor : neighbors2) {
				minimum = min(neighbor.staticField,minimum);
			}
			if (minimum + 1 < this.staticField ) {
				this.staticField = minimum + 1;
				return true;
			}
			return false;
		}
	}
	
	public void move(){
		if (this.isPedestrian && !blocked) {
			int minimum = 10000000;
			Point position = null;
			if (this.type_of_neighborhood == 1) {
				for (Point neighbor : neighbors) {
					if ((neighbor.type == 0 || neighbor.type == 2) && minimum > neighbor.staticField && !neighbor.blocked) {
						minimum = neighbor.staticField;
						position = neighbor;
					}
				}
				if (position != null) {
					if (position.type == 0) {
						position.type = 3;
						position.isPedestrian = true;
					}
					position.blocked = true;
					this.type = 0;
					this.isPedestrian = false;
				}
				this.blocked = true;
			} else {
				for (Point neighbor : neighbors2) {
					if ((neighbor.type == 0 || neighbor.type == 2) && minimum > neighbor.staticField && !neighbor.blocked) {
						minimum = neighbor.staticField;
						position = neighbor;
					}
				}
				if (position != null) {
					if (position.type == 0) {
						position.type = 3;
						position.isPedestrian = true;
					}
					position.blocked = true;
					this.type = 0;
					this.isPedestrian = false;
				}
				this.blocked = true;
			}
		}
	}

	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
}