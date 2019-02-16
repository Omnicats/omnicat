/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package commands.global;

import edu.wpi.first.wpilibj.command.InstantCommand;
import util.Subsystem;


/**
 * Add your docs here.
 */
public class Shutoff extends InstantCommand {
  Subsystem s;

  public Shutoff(Subsystem s) {
    super();
    requires(s);
    this.s = s;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    s.shutoff();
  }

}
