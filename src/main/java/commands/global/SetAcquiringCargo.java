/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package commands.global;

import org.usfirst.frc.team1452.robot.OI;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class SetAcquiringCargo extends InstantCommand {
  /**
   * Add your docs here.
   */
  boolean acquiringCargo;

  public SetAcquiringCargo(boolean acquiringCargo) {
    super();
    this.acquiringCargo = acquiringCargo;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    OI.acquiringCargo = this.acquiringCargo;
  }

}
