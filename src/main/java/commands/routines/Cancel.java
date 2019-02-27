package commands.routines;

import org.usfirst.frc.team1452.robot.Robot;

import commands.global.Freeze;
import commands.global.Shutoff;
import edu.wpi.first.wpilibj.command.CommandGroup;
import mechanism.drive.DriverControl;

public class Cancel extends CommandGroup {
    public Cancel() {
		addParallel(new DriverControl());
    	addParallel(new Shutoff(Robot.lift));
    	addParallel(new Shutoff(Robot.climber));
    	addParallel(new Shutoff(Robot.kicker));
		addParallel(new Shutoff(Robot.scoop));
    }
}
