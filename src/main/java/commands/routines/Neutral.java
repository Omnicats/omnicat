package commands.routines;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import edu.wpi.first.wpilibj.command.CommandGroup;
import mechanism.drive.DriverControl;

public class Neutral extends CommandGroup {
    public Neutral() {
		addParallel(new DriverControl());
    	addParallel(new SetPos(Robot.lift, 0));
    	addParallel(new SetPos(Robot.climber, 0));
    	addParallel(new SetPos(Robot.kicker, 0));
    	addParallel(new SetPos(Robot.scoop, 0));
    }
}