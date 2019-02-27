/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package commands.global;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class WaitForTrigger extends Command {
  JoystickButton b;
  public static boolean intakingBallLow = false;
	
  public WaitForTrigger(JoystickButton b) {
	  this.b = b;
  }

  public WaitForTrigger(JoystickButton b, boolean intakingBallLow) {
    this.b = b;
    this.intakingBallLow = intakingBallLow;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
	return b.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    intakingBallLow = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    intakingBallLow = false;
  }
}
