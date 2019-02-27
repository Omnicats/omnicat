package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class DepositCargoShip extends CommandGroup {
    public DepositCargoShip() {
		addSequential(new SetPos(Robot.lift, Constants.liftCargoShip_Cargo));
		addParallel(new SetPos(Robot.kicker, Constants.kickerDown));
		addSequential(new SetPos(Robot.scoop, Constants.scoopDepositCargo));
		addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerUp));
    }
}