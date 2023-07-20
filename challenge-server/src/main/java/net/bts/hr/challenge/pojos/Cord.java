package net.bts.hr.challenge.pojos;

public class Cord {
	private Double x;
	private Double y;
	
	public Cord() {
		this.x = 0.0;
		this.y = 0.0;
	}
	
	public Cord(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
	
	public Cord add(Cord cord) {
		this.x += cord.getX();
		this.y += cord.getY();
		return this;
	}

	@Override
	public String toString() {
		return "Cord [x=" + x + ", y=" + y + "]";
	}
	
	
	
	
}
