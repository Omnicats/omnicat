package commands.routines;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;
import org.usfirst.frc.team1452.robot.OI;

public class Climb extends CommandGroup {
    public Climb() {
    	addSequential(new SetPos(Robot.climber, Constants.climberDown));
    	addSequential(new WaitForTrigger(OI.climbTrigger));
    	addSequential(new SetPos(Robot.climber, Constants.climberBack));
    	addSequential(new WaitForTrigger(OI.climbTrigger));
    	addSequential(new SetPos(Robot.climber, Constants.climberDown));
    	addSequential(new WaitForTrigger(OI.climbTrigger));
    	addSequential(new SetPos(Robot.climber, Constants.climberBack));
    }
}