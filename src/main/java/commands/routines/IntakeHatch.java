package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class IntakeHatch extends CommandGroup {
    public IntakeHatch() {
    	addParallel(new SetPos(Robot.lift, Constants.liftHatchLow));
    	addParallel(new SetPos(Robot.kicker, Constants.kickerDisengageHatch));
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerHoldHatch));
    }
}