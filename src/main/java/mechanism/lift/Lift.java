/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package mechanism.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import util.Subsystem;
import util.Constants;

public class Lift extends Subsystem{
  WPI_TalonSRX lift;
  WPI_TalonSRX liftFollower;
  private NetworkTableEntry encoderEntry;
  private NetworkTableEntry motorEntry;
  private NetworkTableEntry integralEntry;
  private NetworkTableEntry maxEncoderEntry;



  public Lift(WPI_TalonSRX lift, WPI_TalonSRX liftFollower){
    this.lift = lift;
    this.liftFollower = liftFollower;
    lift.configFactoryDefault();
    liftFollower.configFactoryDefault();
    liftFollower.follow(lift);

    lift.setInverted(false);
    liftFollower.setInverted(InvertType.OpposeMaster);

    lift.setSensorPhase(true);

    /* Set relevant frame periods to be at least as fast as periodic rate */
    lift.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
    lift.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);

    /* Set the peak and nominal outputs */
    lift.configNominalOutputForward(0, 30);
    lift.configNominalOutputReverse(0, 30);
    lift.configPeakOutputForward(1, 30);
    lift.configPeakOutputReverse(-1, 30);

    /* Set Motion Magic gains in slot0 - see documentation */
    lift.selectProfileSlot(0, 0);
    lift.config_kF(0, Constants.liftKF, 30); 
    lift.config_kP(0, Constants.liftKP, 30);
    lift.config_kI(0, Constants.liftKI, 30);
    lift.config_kD(0, Constants.liftKD, 30);

    lift.config_IntegralZone(0, Constants.liftIZone);
    //lift.configMaxIntegralAccumulator(0, 30000, 30);

    /* Set acceleration and vcruise velocity - see documentation */
    lift.configMotionCruiseVelocity(Constants.liftMaxV, 30);
    lift.configMotionAcceleration(Constants.liftMaxA, 30);

    /* Zero the sensor */
    lift.setSelectedSensorPosition(0, 0, 30);

    encoderEntry = Shuffleboard.getTab("Lift").add("Lift Encoder", 0).getEntry();	
    motorEntry = Shuffleboard.getTab("Lift").add("Lift Output Power", 0).getEntry();	
    integralEntry = Shuffleboard.getTab("Lift").add("Lift Integral Accumulator", 0).getEntry();	
    maxEncoderEntry = Shuffleboard.getTab("Lift").add("Max Encoder Value", 0).getEntry();	

  }

  public void goTo(double pos){
    lift.set(ControlMode.MotionMagic, pos); //Change to motion magic
  }
  
  public void freeze(){
    lift.set(ControlMode.Position, lift.getSelectedSensorPosition());
  }

  public void shutoff(){
    lift.set(ControlMode.PercentOutput, 0);
  }
  
  public boolean withinThreshold(double pos){
	  return Math.abs(lift.getSelectedSensorPosition() - pos) < Constants.liftPThreshold && Math.abs(lift.getSelectedSensorVelocity()) < Constants.liftVThreshold;
  }

  public void setPower(double power){
    lift.set(ControlMode.PercentOutput, power);
  }

  @Override
  protected void initDefaultCommand() {

  }

  public void updateEntries(){
    encoderEntry.setNumber(lift.getSelectedSensorPosition());
    motorEntry.setNumber(lift.getMotorOutputPercent());
    integralEntry.setNumber(lift.getIntegralAccumulator());
    maxEncoderEntry.setNumber(Math.max(lift.getSelectedSensorPosition(), maxEncoderEntry.getDouble(0)));
  }

  /*public void zero(){
    //lift.setSelectedSensorPosition(0);
  }*/
}
