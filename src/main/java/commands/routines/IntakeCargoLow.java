package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetAcquiringCargo;
import commands.global.SetPos;
import commands.global.WaitForTrigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class IntakeCargoLow extends CommandGroup {
    public IntakeCargoLow() {
		addParallel(new SetAcquiringCargo(true));
    	addParallel(new SetPos(Robot.lift, Constants.liftDown));
		addParallel(new SetPos(Robot.scoop, Constants.scoopDown));
		addParallel(new SetPos(Robot.kicker, Constants.kickerDown));
    	addSequential(new WaitForTrigger(OI.triggerButton));
		addSequential(new SetPos(Robot.scoop, Constants.scoopHoldCargo));
		addParallel(new SetAcquiringCargo(false));
		addSequential(new SetPos(Robot.kicker, Constants.kickerHoldCargo));
	}
	
	public void interrupted(){
		OI.acquiringCargo = false;
	}
}