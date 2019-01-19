package mechanism.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDDrive {
	/*private Drive drive;
	public void setInvertedMotor(int ...port) {
		drive.setInverted(port);
	}
	public Drive getDrive() {
		return drive;
	}
	
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
	
	public PIDDrive(double kp, double ki, double kd, Drive drive) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		this.drive = drive;
	}
	
	private double previousError = 0;
	private double i = 0;
	private double d = 0;
	private double previousTime = 0;
	private double dt = 0;
	
	public void arcadeUpdate(double targetSpeed, double targetTurn) {
		drive.update(targetSpeed + targetTurn, targetSpeed - targetTurn);
	}
	
	public void update(double leftSpeed, double rightSpeed, double error, boolean usePID) {
		dt = (System.currentTimeMillis() - previousTime)/1000;
		if(usePID) {
			i += error/dt;
			d = -(error - previousError)/dt;
			arcadeUpdate((leftSpeed + rightSpeed)/2, kp*error + ki*i + kd*d);
			previousError = error;
		}
		else {
			previousError = 0;
			i = 0;
			d = 0;
			drive.update(leftSpeed*Math.abs(leftSpeed), rightSpeed*Math.abs(rightSpeed));
			
		}
		SmartDashboard.putNumber("P", kp*error);
		SmartDashboard.putNumber("I", ki*i);
		SmartDashboard.putNumber("D", kd*d);
		SmartDashboard.putNumber("KP", kp);
		SmartDashboard.putNumber("KI", ki);
		SmartDashboard.putNumber("KD", kd);
		SmartDashboard.putNumber("Output", kp*error + ki*i + kd*d);
		previousTime = System.currentTimeMillis()/1000;
	}*/
}
