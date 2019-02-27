package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import edu.wpi.first.wpilibj.command.CommandGroup;
import mechanism.drive.DriverControl;
import util.Constants;

public class NeutralWithGameObject extends CommandGroup {
    public NeutralWithGameObject() {
		addParallel(new DriverControl());
    	addParallel(new SetPos(Robot.lift, 0));
    	addParallel(new SetPos(Robot.climber, 0));
    	addParallel(new SetPos(Robot.kicker, OI.hatchSwitch.get() ? Constants.kickerHoldHatch : Constants.kickerDown));
    	addParallel(new SetPos(Robot.scoop, OI.hatchSwitch.get() ? Constants.scoopUp : Constants.scoopHoldCargo));
    }
}