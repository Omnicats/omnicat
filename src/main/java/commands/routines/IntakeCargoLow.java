package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class IntakeCargoLow extends CommandGroup {
    public IntakeCargoLow() {
    	addParallel(new SetPos(Robot.lift, Constants.liftDown));
		addParallel(new SetPos(Robot.scoop, Constants.scoopDown));
		addParallel(new SetPos(Robot.kicker, Constants.kickerDown));
    	addSequential(new WaitForTrigger(OI.triggerButton, true));
		addSequential(new SetPos(Robot.scoop, Constants.scoopHoldCargo));
		addSequential(new SetPos(Robot.kicker, Constants.kickerHoldCargo));
    }
}