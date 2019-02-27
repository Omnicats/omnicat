package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;


public class DepositHatchHigh extends CommandGroup {
    public DepositHatchHigh() {
    	addSequential(new SetPos(Robot.lift, Constants.liftRocketHigh_Hatch));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerHoldCargo));
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDepositHatch));
    }
}