/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package mechanism.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team1452.robot.Robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import util.Subsystem;
import util.Constants;

public class Drive extends Subsystem{
  WPI_TalonSRX frontLeft;
  WPI_TalonSRX frontRight;

  WPI_TalonSRX leftFollower;
  WPI_TalonSRX rightFollower;

  DifferentialDrive drive;
  
  public Drive(WPI_TalonSRX frontLeft, WPI_TalonSRX leftFollower, WPI_TalonSRX frontRight, WPI_TalonSRX rightFollower){
    this.frontLeft = frontLeft;
    this.leftFollower = leftFollower;
    this.frontRight = frontRight;
    this.rightFollower = rightFollower;
  
    drive = new DifferentialDrive(frontLeft, frontRight);
    leftFollower.follow(frontLeft);
	  rightFollower.follow(frontRight);
    
    frontLeft.setInverted(false);
    frontRight.setInverted(true);
    leftFollower.setInverted(InvertType.FollowMaster);
	  rightFollower.setInverted(InvertType.FollowMaster);
    
    drive.setRightSideInverted(false); 

    frontLeft.setSensorPhase(true);
    frontRight.setSensorPhase(true);
  }

  public void arcadeDrive(double throttle, double turn){
    drive.arcadeDrive(throttle, turn, false);
  }

  public void curvatureDrive(double throttle, double turn, boolean quickTurn){
    drive.curvatureDrive(throttle, turn, quickTurn);
  }

  public void freeze(){
    drive.arcadeDrive(0, 0, false);
  }

  public void shutoff(){
    drive.arcadeDrive(0, 0, false);
  }

  public boolean withinThreshold(double pos){
	  return false;
  }
  public void setPower(double power){
  }

  @Override
  protected void initDefaultCommand() {
    //new DriverControl();
  }
}
