/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package mechanism.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team1452.robot.OI;
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

  private static double turnPreviousError = 0;
	private static double turnI = 0;
	private static double turnD = 0;
	private static double turnPreviousTime = 0;
	private static double turnDT = 0;

  private static double throttlePreviousError = 0;
	private static double throttleI = 0;
	private static double throttleD = 0;
	private static double throttlePreviousTime = 0;
	private static double throttleDT = 0;


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

    /*frontLeft.setSensorPhase(true);
    frontRight.setSensorPhase(true);

    frontRight.configRemoteFeedbackFilter(frontLeft.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, 30); // 

    frontRight.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, 30);
    frontRight.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.QuadEncoder, 30);

    frontRight.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, 1, 30);

    frontRight.configSelectedFeedbackCoefficient(Constants.driveTurnUnitsPerRotation / Constants.driveEncoderUnitsPerRotation,  scale to 1/10 of a degree per unit, 30);

    frontRight.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, 30);
		frontRight.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, 30);
		frontLeft.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 30);		//Used remotely by right Talon, speed up

    frontRight.config_kP(1, Constants.driveTurnKP, 30);
		frontRight.config_kI(1, Constants.driveThrottleKI, 30);
		frontRight.config_kD(1, Constants.driveTurnKD, 30);
		frontRight.config_kF(1, Constants.driveTurnKF, 30);
		frontRight.config_IntegralZone(1, Constants.driveTurnIZone, 30);
		frontRight.configClosedLoopPeakOutput(1, Constants.driveTurnMaxPower, 30);
    
    frontRight.configAuxPIDPolarity(false, 30);


    frontLeft.configRemoteFeedbackFilter(frontRight.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, 30); // 

    frontLeft.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, 30);
    frontLeft.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.QuadEncoder, 30);

    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, 1, 30);

    frontLeft.configSelectedFeedbackCoefficient(Constants.driveTurnUnitsPerRotation / Constants.driveEncoderUnitsPerRotation,  scale to 1/10 of a degree per unit, 30);

    frontLeft.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, 30);
		frontLeft.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, 30);
		frontRight.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 30);		//Used remotely by right Talon, speed up

    frontLeft.config_kP(1, Constants.driveTurnKP, 30);
		frontLeft.config_kI(1, Constants.driveThrottleKI, 30);
		frontLeft.config_kD(1, Constants.driveTurnKD, 30);
		frontLeft.config_kF(1, Constants.driveTurnKF, 30);
		frontLeft.config_IntegralZone(1, Constants.driveTurnIZone, 30);
		frontLeft.configClosedLoopPeakOutput(1, Constants.driveTurnMaxPower, 30);
    
    frontLeft.configAuxPIDPolarity(false, 30);


    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);*/
  }

  public void driveDistanceToHeading(double throttleError, double turnError){
    //frontRight.set(ControlMode.MotionMagic, inches, DemandType.AuxPID, desiredHeadingDegrees);
    //frontLeft.follow(frontRight, FollowerType.AuxOutput1); 

    turnDT = (System.currentTimeMillis() - turnPreviousTime)/1000;
    turnI += turnError/turnDT;
    turnD = -(turnError - turnPreviousError)/turnDT;
    turnPreviousError = turnError;

    throttleDT = (System.currentTimeMillis() - throttlePreviousTime)/1000;
    throttleI += throttleError/throttleDT;
    throttleD = -(throttleError - throttlePreviousError)/throttleDT;
    throttlePreviousError = throttleError;

    drive.arcadeDrive(Constants.driveThrottleKP*throttleError + Constants.driveTurnKI*throttleI + Constants.driveThrottleKD*throttleD, Constants.driveTurnKP*turnError + Constants.driveTurnKI*turnI + Constants.driveTurnKD*turnD);
  }

  public void driveToHeading(double turnError){
    //frontLeft.set(ControlMode.PercentOutput, -Robot.throttleJ.getY() * Math.abs(Robot.throttleJ.getY()), DemandType.AuxPID, desiredHeadingDegrees);
    //frontRight.set(ControlMode.PercentOutput, -Robot.throttleJ.getY() * Math.abs(Robot.throttleJ.getY()), DemandType.AuxPID, desiredHeadingDegrees);
    //frontRight.set(ControlMode.Position, desiredHeadingDegrees);

    turnDT = (System.currentTimeMillis() - turnPreviousTime)/1000;
    turnI += turnError/turnDT;
    turnD = -(turnError - turnPreviousError)/turnDT;
    turnPreviousError = turnError;

    drive.arcadeDrive(OI.throttleOutput(), Constants.driveTurnKP*turnError + Constants.driveTurnKI*turnI + Constants.driveTurnKD*turnD);
  }

  public void turnDegrees(double degrees){
    //frontRight.set(ControlMode.PercentOutput, 0, DemandType.AuxPID, degrees * 10);
    //frontLeft.follow(frontRight, FollowerType.AuxOutput1);

    /*dt = (System.currentTimeMillis() - previousTime)/1000;
    i += error/dt;
    d = -(error - previousError)/dt;
    arcadeUpdate((leftSpeed + rightSpeed)/2, kp*error + ki*i + kd*d);
    previousError = error;*/

  }

  public static void resetPIDs(){
    resetTurnPID();
    resetThrottlePID();
  }

  public static void resetTurnPID(){
    turnPreviousError = 0;
    turnI = 0;
    turnD = 0;
  }

  public static void resetThrottlePID(){
    throttlePreviousError = 0;
    throttleI = 0;
    throttleD = 0;
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
