package util.controlSystem.PID;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PID {	
	private double kp;
	public double getKp() {
		return kp;
	}
	public void setKp(double kp) {
		this.kp = kp;
	}
	
	private double ki;
	public double getKi() {
		return ki;
	}
	public void setKi(double ki) {
		this.ki = ki;
	}
	
	private double kd;
	public double getKd() {
		return kd;
	}
	public void setKd(double kd) {
		this.kd = kd;
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
		prefs = Preferences.getInstance();
	}
	
	private double previousError = 0;
	private double i = 0;
	private double d = 0;
	private double previousTime = 0;
	private double dt = 0;
	private Preferences prefs;
	
	public double update(double error) {
		kp = prefs.getDouble(name + " KP", 1);
		ki = prefs.getDouble(name + " KI", 1);
		kd = prefs.getDouble(name + " KD", 1);
		dt = (System.currentTimeMillis() - previousTime)/1000;
		i += error/dt;
		d = -(error - previousError)/dt;
		previousError = error;
		SmartDashboard.putNumber(name + " P", kp*error);
		SmartDashboard.putNumber(name + " I", ki*i);
		SmartDashboard.putNumber(name + " D", kd*d);
		SmartDashboard.putNumber(name + " Output", kp*error + ki*i + kd*d);
		previousTime = System.currentTimeMillis()/1000;
		return kp*error + ki*i + kd*d;
	}
}
