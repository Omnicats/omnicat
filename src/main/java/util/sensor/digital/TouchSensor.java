package util.sensor.digital;

import edu.wpi.first.wpilibj.DigitalInput;

public class TouchSensor {
	private DigitalInput sensor;
	public DigitalInput getSensor() {
		return sensor;
	}
	
	private boolean state;
	public boolean getState() {
		return state;
	}
	
	private boolean prevState;
	public boolean getPrevState() {
		return prevState;
	}
	
	public TouchSensor(int port) {
		sensor = new DigitalInput(port);
	}
	
	public void update() {
		prevState = state;
		state = sensor.get();
	}
	
	public boolean pressed() {
		return (state && !prevState);
	}
	
	public boolean released() {
		return (!state && prevState);
	}
	
	public boolean changedState() {
		return (state != prevState);
	}
}
