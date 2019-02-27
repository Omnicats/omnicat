package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class DepositHatchLow extends CommandGroup {
    public DepositHatchLow() {
    	addSequential(new SetPos(Robot.lift, Constants.liftHatchLow));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDepositHatch));
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDisengageHatch));
    }
}