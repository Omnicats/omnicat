/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package util;

/**
 * Add your docs here.
 */
public abstract class Subsystem extends edu.wpi.first.wpilibj.command.Subsystem{
	public boolean withinThreshold(double pos){return false;};
	public void goTo(double pos){};
    public void shutoff(){};
    public void freeze(){};
}
