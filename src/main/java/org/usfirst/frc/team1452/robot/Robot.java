package org.usfirst.frc.team1452.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import mechanism.Climber;
import mechanism.Kicker;
import mechanism.Lift;
import mechanism.Scoop;
import mechanism.sensor.CameraStream;
import mechanism.sensor.VisionProcessing;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  WPI_TalonSRX frontLeft = new WPI_TalonSRX(11);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(1);

  WPI_TalonSRX leftSlave = new WPI_TalonSRX(12);
  WPI_TalonSRX rightSlave = new WPI_TalonSRX(0);  

  Scoop scoop;
  Kicker kicker;
  Climber climber;
  Lift lift;

  DifferentialDrive drive = new DifferentialDrive(frontLeft, frontRight);
  Joystick j = new Joystick(0);
  Joystick j1 = new Joystick(1);
  Joystick j2 = new Joystick(2);

  CameraStream stream = new CameraStream();
  VisionProcessing processing = new VisionProcessing(stream, j2);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    scoop = new Scoop(new WPI_TalonSRX(6));
    kicker = new Kicker(new WPI_TalonSRX(7));
    climber = new Climber(new WPI_TalonSRX(4), new WPI_TalonSRX(5));
    lift = new Lift(new WPI_TalonSRX(2), new WPI_TalonSRX(10));
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {    
    leftSlave.follow(frontLeft);
	  rightSlave.follow(frontRight);
    
    frontLeft.setInverted(false);
    frontRight.setInverted(true);
    leftSlave.setInverted(InvertType.FollowMaster);
	  rightSlave.setInverted(InvertType.FollowMaster);
    
    drive.setRightSideInverted(false); 
  }

  @Override
  public void teleopPeriodic() {
    double forward = -1.0 * j.getY() * Math.abs(j.getY());  // Sign this so forward is positive
    double turn = 1.0 * j1.getX(); 
    
    if (Math.abs(forward) < 0.025) {
      forward = 0;
    }
    if (Math.abs(turn) < 0.025) {
      turn = 0;
    }
    drive.curvatureDrive(forward, turn, j.getRawButton(1));

    ////////////////////////////////////////////////////

    double kickerVal = j2.getY();
    double scoopVal = j2.getThrottle();
    double climberVal = j2.getY() * Math.abs(j2.getY());
    double liftVal = -j2.getThrottle() * Math.abs(j2.getThrottle());

    if (Math.abs(kickerVal) < 0.015) {
      kickerVal = 0;
    }
    if (Math.abs(scoopVal) < 0.015) {
      scoopVal = 0;
    }
    if (Math.abs(climberVal) < 0.015) {
      climberVal = 0;
    }
  
    if(j2.getRawButtonPressed(1)){
      //lift.set(ControlMode.Position, 500);
      scoop.set(ControlMode.Position, 0);
    }
    if(j2.getRawButtonPressed(4)){
      //lift.set(ControlMode.Position, 1500);
      scoop.set(ControlMode.Position, 1025);
    }
    

    
    if(j2.getRawButtonPressed(3)){
      //lift.set(ControlMode.Position, 2500);
      scoop.set(ControlMode.Position, 1300);
    }
    if(j2.getRawButtonPressed(10)){
      //lift.setSelectedSensorPosition(0, 0, 30);
      scoop.setSelectedSensorPosition(0, 0, 30);
    }
    if(j2.getRawButton(2)){
      //lift.set(0);
      scoop.set(0);
    }

    kicker.set(j2.getRawButton(5) ? 0 : kickerVal);
    //scoop.set(j2.getRawButton(5) ? 0 : scoopVal);
    climber.set(j2.getRawButton(5) ? climberVal : 0);
    lift.set(j2.getRawButton(5) ? liftVal : 0);
    //liftFollower.set(j2.getRawButton(7) ? liftVal : 0);
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}