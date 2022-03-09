public class Point {

	public Point nNeighbor;
	public Point wNeighbor;
	public Point eNeighbor;
	public Point sNeighbor;
	public float nVel;
	public float eVel;
	public float wVel;
	public float sVel;
	public float pressure;
	public static Integer []types ={0,1,2};
	int type;
	int sinInput;



	public Point() {
		for(int i = 0;i<3;i++) types[i]=0;
		clear();
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		this.nVel = 0;
		this.eVel = 0;
		this.wVel = 0;
		this.sVel = 0;
		this.pressure = 0;
	}

	public void updateVelocity() {
		if(this.type==0) {
			this.nVel = this.nVel - (this.nNeighbor.pressure - this.pressure);
			this.wVel = this.wVel - (this.wNeighbor.pressure - this.pressure);
			this.eVel = this.eVel - (this.eNeighbor.pressure - this.pressure);
			this.sVel = this.sVel - (this.sNeighbor.pressure - this.pressure);
		}
	}

	public void updatePresure() {
		if(this.type==0) this.pressure = this.pressure - (float) 0.5*(this.nVel+this.wVel+this.eVel+this.sVel);
		if(this.type==2) {
			double radians = Math.toRadians(sinInput);
			this.pressure = (float) (Math.sin(radians));
		}

	}

	public float getPressure() {
		return pressure;
	}
}
