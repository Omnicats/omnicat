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

import util.Subsystem;
import util.Constants;

public class Lift extends Subsystem{
  TalonSRX lift;
  TalonSRX liftFollower;

  public Lift(TalonSRX lift, TalonSRX liftFollower){
    this.lift = lift;
    this.liftFollower = liftFollower;
    liftFollower.follow(lift);

    lift.setInverted(false);
    liftFollower.follow(lift);
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

    /* Set acceleration and vcruise velocity - see documentation */
    lift.configMotionCruiseVelocity(Constants.liftMaxV, 30);
    lift.configMotionAcceleration(Constants.liftMaxA, 30);

    /* Zero the sensor */
    lift.setSelectedSensorPosition(0, 0, 30);
  }

  public void goTo(double pos){
    lift.set(ControlMode.Position, pos); //Change to motion magic
  }
  
  public void freeze(){
    lift.set(ControlMode.Position, lift.getSelectedSensorPosition());
  }

  public void shutoff(){
    lift.set(ControlMode.PercentOutput, 0);
  }
  
  public boolean withinThreshold(double pos){
	return Math.abs(lift.getSelectedSensorPosition() - pos) < Constants.liftPThreshold && Math.abs(lift.getSelectedSensorVelocity) < Constants.liftVThreshold;
  }


  public void setPower(double power){
    lift.set(ControlMode.PercentOutput, power);
  }

  @Override
  protected void initDefaultCommand() {

  }

  /*public void zero(){
    //lift.setSelectedSensorPosition(0);
  }*/
}
