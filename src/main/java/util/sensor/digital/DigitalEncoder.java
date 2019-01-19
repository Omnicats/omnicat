package util.sensor.digital;

import edu.wpi.first.wpilibj.Encoder;

public class DigitalEncoder extends Encoder{	
	private double rotationsPerInch;
	public double getRotationsPerInch() {
		return rotationsPerInch;
	}
	public void setRotationsPerInch(double rotationsPerInch) {
		this.rotationsPerInch = rotationsPerInch;
	}

	private double rotationsPerDegree;
	public double getRotationsPerDegree() {
		return rotationsPerDegree;
	}
	public void setRotationsPerDegree(double rotationsPerDegree) {
		this.rotationsPerDegree = rotationsPerDegree;
	}
	
	public DigitalEncoder(int port0, int port1) {
		super(port0, port1);
	}
	
	public double getDegrees() {
		return getDistance()/rotationsPerDegree;
	}

	public double getInches() {
		return getDistance()/rotationsPerInch;
	}
	
	public double getRate() {
		return getRate()/rotationsPerInch;
	}
	
}
