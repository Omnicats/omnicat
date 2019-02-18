package org.usfirst.frc.team1452.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import mechanism.climber.Climber;
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
  WPI_TalonSRX frontLeft = new WPI_TalonSRX(11);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(1);

  WPI_TalonSRX leftSlave = new WPI_TalonSRX(12);
  WPI_TalonSRX rightSlave = new WPI_TalonSRX(0);

  public static Scoop scoop;
  public static Kicker kicker;
  public static Climber climber;
  public static Lift lift;
  public static OI oi;

  DifferentialDrive drive = new DifferentialDrive(frontLeft, frontRight);
  public static Joystick throttleJ = new Joystick(0);
  public static Joystick turnJ = new Joystick(1);
  public static Joystick j2 = new Joystick(2);

  CameraStream stream = new CameraStream();
  VisionProcessing processing = new VisionProcessing(stream, mechBoard);

  boolean intaking = false;
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    leftSlave.follow(frontLeft);
	  rightSlave.follow(frontRight);
    
    frontLeft.setInverted(false);
    frontRight.setInverted(true);
    leftSlave.setInverted(InvertType.FollowMaster);
	  rightSlave.setInverted(InvertType.FollowMaster);
    
    drive.setRightSideInverted(false); 
    

    scoop = new Scoop(new WPI_TalonSRX(6));
    kicker = new Kicker(new WPI_TalonSRX(7));
    climber = new Climber(new WPI_TalonSRX(4), new WPI_TalonSRX(5));
    lift = new Lift(new WPI_TalonSRX(10), new WPI_TalonSRX(2));
    //oi = new OI();
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
	Scheduler.getInstance().run();
    double forward = -1.0 * throttleJ.getY() * Math.abs(throttleJ.getY());  // Sign this so forward is positive
    double turn = 1.0 * turnJ.getX(); 
    
    if (Math.abs(forward) < 0.025) {
      forward = 0;
    }
    if (Math.abs(turn) < 0.025) {
      turn = 0;
    }
    drive.curvatureDrive(forward, turn, throttleJ.getRawButton(1));

    ////////////////////////////////////////////////////

    /*double kickerVal = j2.getY();
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
    }*/
  
    if(j2.getRawButtonPressed(1)){
      //lift.set(ControlMode.Position, 500);
      //climber.goTo(Constants.climberForward);
      lift.goTo(Constants.liftHatchLow);
    }
    if(j2.getRawButtonPressed(4)){
      //lift.set(ControlMode.Position, 1500);
      //climber.goTo(Constants.climberDown);
      lift.goTo(Constants.liftRocketMedium_Hatch);
    }
    if(j2.getRawButtonPressed(3)){
      //lift.set(ControlMode.Position, 2500);
      //climber.goTo(Constants.climberBack);
      lift.goTo(Constants.liftRocketHigh_Hatch);
    }
    if(j2.getRawButton(2)){
      //lift.set(0);
      //climber.shutoff();
      lift.setPower(j2.getY());
    }
    /*if(j2.getRawButton(6)){
      lift.zero();
    }*/

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