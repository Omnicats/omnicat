package util.sensor.analog;

import edu.wpi.first.wpilibj.AnalogInput;

public class Rangefinder {
	private AnalogInput rangefinder;
	public boolean inInches = true;
	
	public enum Unit{
		Inches, Millimeters;
	}
	
	public Rangefinder(int port) {
		rangefinder = new AnalogInput(port);
	}
	
	public double getDistanceInches() {
		return getVoltage()*41.7;
	}
	
	public void setUnits(Unit unit) {
		if(unit.equals(Unit.Inches)) {
			inInches = true;
		}
		else {
			inInches = false;
		}
	}
	public Unit getUnits() {
		return inInches ? Unit.Inches : Unit.Millimeters;
	}
	
	public double getDistanceMM() {
		return getDistanceInches()/25.4;
	}
	
	public double getVoltage() {
		return rangefinder.getVoltage();
	}
	
	public double getDistance() {
		return inInches ? getDistanceInches() : getDistanceMM();
	}

}
