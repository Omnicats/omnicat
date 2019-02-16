/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package mechanism.kicker;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import util.Subsystem;
import util.Constants;

public class Kicker extends Subsystem{
  TalonSRX kicker;

  public Kicker(TalonSRX kicker){
    this.kicker = kicker;

    kicker.setSensorPhase(false);

    /* Set relevant frame periods to be at least as fast as periodic rate */
    kicker.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
    kicker.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);

    /* Set the peak and nominal outputs */
    kicker.configNominalOutputForward(0, 30);
    kicker.configNominalOutputReverse(0, 30);
    kicker.configPeakOutputForward(1, 30);
    kicker.configPeakOutputReverse(-1, 30);

    /* Set Motion Magic gains in slot0 - see documentation */
    kicker.selectProfileSlot(0, 0);
    kicker.config_kF(0, Constants.kickerKF, 30); 
    kicker.config_kP(0, Constants.kickerKP, 30);
    kicker.config_kI(0, Constants.kickerKI, 30);
    kicker.config_kD(0, Constants.kickerKD, 30);

    /* Zero the sensor */
    kicker.setSelectedSensorPosition(0, 0, 30);
  }

  public void goTo(double pos){
    kicker.set(ControlMode.Position, pos); //Change to motion magic
  }

  @Override
  protected void initDefaultCommand() {

  }
}
