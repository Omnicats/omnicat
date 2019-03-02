package org.usfirst.frc.team1452.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import mechanism.climber.Climber;
import mechanism.drive.Drive;
import mechanism.kicker.Kicker;
import mechanism.lift.Lift;
import mechanism.scoop.Scoop;
import mechanism.sensor.CameraStream;
import mechanism.sensor.VisionProcessing;
import util.Constants;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drive drive;
  public static Scoop scoop;
  public static Kicker kicker;
  public static Climber climber;
  public static Lift lift;
  public static OI oi;

  public static Joystick throttleJ = new Joystick(0);
  public static Joystick turnJ = new Joystick(1);
  public static Joystick j2 = new Joystick(2);

  CameraStream stream = new CameraStream();
  VisionProcessing processing = new VisionProcessing(stream);

  NetworkTableEntry distanceErrorEntry;

  Relay ledRelay = new Relay(0, Relay.Direction.kForward);
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() { 
    drive = new Drive(new WPI_TalonSRX(11), new WPI_TalonSRX(12), new WPI_TalonSRX(0), new WPI_TalonSRX(1));
    scoop = new Scoop(new WPI_TalonSRX(6));
    kicker = new Kicker(new WPI_TalonSRX(8)); //7
    climber = new Climber(new WPI_TalonSRX(4), new WPI_TalonSRX(5)); //4, 5
    lift = new Lift(new WPI_TalonSRX(10), new WPI_TalonSRX(2));
    //oi = new OI();

    distanceErrorEntry = Shuffleboard.getTab("Vision").add("Distance Error", 0).getEntry();	
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {    
  }

  @Override
  public void teleopPeriodic() {
	  //Scheduler.getInstance().run();
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
  
    if(j2.getRawButton(1)){ //pressed
      //lift.goTo(Constants.liftDown);
      //climber.goTo(Constants.climberForward);
      drive.driveToHeading(1452);
    }
    else{
      double forward = -1.0 * Robot.throttleJ.getY() * Math.abs(Robot.throttleJ.getY());  // Sign this so forward is positive
      double turn = 1.0 * Robot.turnJ.getX(); 
      
      if (Math.abs(forward) < 0.025) {
        forward = 0;
      }
      if (Math.abs(turn) < 0.025) {
        turn = 0;
      }
      drive.curvatureDrive(forward, turn, turnJ.getRawButton(1));
    }
    if(j2.getRawButtonPressed(4)){
      //lift.goTo(Constants.liftRocketLow_Cargo);
      climber.goTo(Constants.climberDown);
    }
    if(j2.getRawButtonPressed(3)){
      //lift.goTo(Constants.liftRocketMedium_Cargo);
      climber.goTo(Constants.climberBack);
    }
    if(j2.getRawButtonPressed(9)){

    }
    if(j2.getRawButtonPressed(8)){
      //kicker.goTo(Constants.kickerHoldHatch);
      climber.goTo(Constants.climberTest);
    }
    if(j2.getRawButton(2)){
      //lift.setPower(j2.getY());
      //climber.shutoff();
      //climber.setPower(j2.getY());
    }
    if(j2.getRawButtonPressed(6)){
      //lift.zero();
      if(ledRelay.get().equals(Relay.Value.kOff)){
        ledRelay.set(Value.kForward);
      }
      else{
        ledRelay.set(Value.kOff);
      }
    }

    /*double forward = -1.0 * Robot.throttleJ.getY() * Math.abs(Robot.throttleJ.getY());  // Sign this so forward is positive
    double turn = 1.0 * Robot.turnJ.getX(); 
    
    if (Math.abs(forward) < 0.025) {
      forward = 0;
    }
    if (Math.abs(turn) < 0.025) {
      turn = 0;
    }
    drive.curvatureDrive(forward, turn, turnJ.getRawButton(1));*/

    lift.updateEntries();
    distanceErrorEntry.setDouble(processing.getDistanceErrorInches());

    //kicker.set(j2.getRawButton(5) ? 0 : kickerVal);
    //scoop.set(j2.getRawButton(5) ? 0 : scoopVal);
    //climber.set(j2.getRawButton(5) ? climberVal : 0);
    //lift.set(j2.getRawButton(5) ? liftVal : 0);
    //liftFollower.set(j2.getRawButton(7) ? liftVal : 0);
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}