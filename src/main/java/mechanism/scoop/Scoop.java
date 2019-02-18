/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package mechanism.scoop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import util.Subsystem;
import util.Constants;

public class Scoop extends Subsystem{
  TalonSRX scoop;

  public Scoop(TalonSRX scoop){
    this.scoop = scoop;

    scoop.setSensorPhase(false);

    /* Set relevant frame periods to be at least as fast as periodic rate */
    scoop.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
    scoop.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);

    /* Set the peak and nominal outputs */
    scoop.configNominalOutputForward(0, 30);
    scoop.configNominalOutputReverse(0, 30);
    scoop.configPeakOutputForward(1, 30);
    scoop.configPeakOutputReverse(-1, 30);

    /* Set Motion Magic gains in slot0 - see documentation */
    scoop.selectProfileSlot(0, 0);
    scoop.config_kF(0, Constants.scoopKF, 30); 
    scoop.config_kP(0, Constants.scoopKP, 30);
    scoop.config_kI(0, Constants.scoopKI, 30);
    scoop.config_kD(0, Constants.scoopKD, 30);

    /* Zero the sensor */
    scoop.setSelectedSensorPosition(0, 0, 30);
  }

  public void goTo(double pos){
    scoop.set(ControlMode.Position, pos); //Change to motion magic
  }

  public void freeze(){
    scoop.set(ControlMode.Position, scoop.getSelectedSensorPosition());
  }

  public void shutoff(){
    scoop.set(ControlMode.PercentOutput, 0);
  }
  
  public boolean withinThreshold(double pos){
	return Math.abs(scoop.getSelectedSensorPosition() - pos) < Constants.scoopPThreshold && Math.abs(scoop.getSelectedSensorVelocity) < Constants.scoopVThreshold;
  }

  @Override
  protected void initDefaultCommand() {

  }
}
