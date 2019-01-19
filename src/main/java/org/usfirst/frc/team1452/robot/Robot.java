package org.usfirst.frc.team1452.robot;

import edu.wpi.first.wpilibj.*;
import mechanism.sensor.CameraStream;

public class Robot extends TimedRobot {
    CameraStream stream;
	
	public void robotInit() {
        stream = new CameraStream();
	}

	public void autonomousInit() {
		
	}

	public void autonomousPeriodic() {
		
	}

	public void teleopInit() {

	}
	
	public void teleopPeriodic() {
	}

	public void testPeriodic() {
		
	}
	
	public void disabledInit() {

	}
	
	public void disabledPeriodic() {

	}
}