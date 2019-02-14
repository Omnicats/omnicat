package util.controlSystem.PID;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class PID {	
	private NetworkTableEntry kPEntry;
	private NetworkTableEntry kIEntry;
	private NetworkTableEntry kDEntry;
	private NetworkTableEntry pEntry;
	private NetworkTableEntry iEntry;
	private NetworkTableEntry dEntry;
	private NetworkTableEntry outputEntry;

	private double kp;
	public double getKp() {
		return kp;
	}
	public void setKp(double kp) {
		this.kp = kp;
		kPEntry.setDouble(kp);
	}
	
	private double ki;
	public double getKi() {
		return ki;
	}
	public void setKi(double ki) {
		this.ki = ki;
		kIEntry.setDouble(ki);
	}
	
	private double kd;
	public double getKd() {
		return kd;
	}
	public void setKd(double kd) {
		this.kd = kd;
		kDEntry.setDouble(kd);
	}
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void reset() {
		previousError = 0;
		i = 0;
		d = 0;
	}

	public PID(String name, double kp, double ki, double kd) {
		this.name = name;
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		kPEntry = Shuffleboard.getTab(name).addPersistent("kP", kp).getEntry();
		kIEntry = Shuffleboard.getTab(name).addPersistent("kI", ki).getEntry();
		kDEntry = Shuffleboard.getTab(name).addPersistent("kD", kd).getEntry();
		pEntry = Shuffleboard.getTab(name).add("P", 0).getEntry();
		iEntry = Shuffleboard.getTab(name).add("I", 0).getEntry();
		dEntry = Shuffleboard.getTab(name).add("D", 0).getEntry();
		outputEntry = Shuffleboard.getTab(name).add("Output", 0).getEntry();
	}
	
	private double previousError = 0;
	private double i = 0;
	private double d = 0;
	private double previousTime = 0;
	private double dt = 0;
	
	public double update(double error) {
		kp = kPEntry.getDouble(kp);
		ki = kIEntry.getDouble(ki);
		kd = kDEntry.getDouble(kd);
		dt = (System.currentTimeMillis() - previousTime)/1000;
		i += error/dt;
		d = -(error - previousError)/dt;
		previousError = error;
		pEntry.setDouble(kp*error);
		iEntry.setDouble(ki*i);
		dEntry.setDouble(kd*d);
		outputEntry.setDouble(kp*error + ki*i + kd*d);
		previousTime = System.currentTimeMillis()/1000;
		return kp*error + ki*i + kd*d;
	}
}
