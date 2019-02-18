/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package mechanism.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import util.Subsystem;
import util.Constants;

public class Climber extends Subsystem{
  TalonSRX climber;
  TalonSRX climberFollower;

  public Climber(TalonSRX climber, TalonSRX climberFollower){
    this.climber = climber;
    this.climberFollower = climberFollower;
    climberFollower.follow(climber);

    climber.setSensorPhase(true);

    /* Set relevant frame periods to be at least as fast as periodic rate */
    climber.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
    climber.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);

    /* Set the peak and nominal outputs */
    climber.configNominalOutputForward(0, 30);
    climber.configNominalOutputReverse(0, 30);
    climber.configPeakOutputForward(1, 30);
    climber.configPeakOutputReverse(-1, 30);

    /* Set Motion Magic gains in slot0 - see documentation */
    climber.selectProfileSlot(0, 0);
    climber.config_kF(0, Constants.climberKF, 30); 
    climber.config_kP(0, Constants.climberKP, 30);
    climber.config_kI(0, Constants.climberKI, 30);
    climber.config_kD(0, Constants.climberKD, 30);

    /* Zero the sensor */
    climber.setSelectedSensorPosition(0, 0, 30);
  }

  public void goTo(double pos){
    climber.set(ControlMode.Position, pos); //Change to motion magic
  }

  public void freeze(){
    climber.set(ControlMode.Position, climber.getSelectedSensorPosition());
  }

  public void shutoff(){
    climber.set(ControlMode.PercentOutput, 0);
  }

  public boolean withinThreshold(double pos){
	return Math.abs(climber.getSelectedSensorPosition() - pos) < Constants.climberPThreshold && Math.abs(climber.getSelectedSensorVelocity) < Constants.climberVThreshold;
  }
  
  @Override
  protected void initDefaultCommand() {

  }
}
