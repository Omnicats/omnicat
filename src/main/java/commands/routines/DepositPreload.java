package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class DepositPreload extends CommandGroup {
    public DepositPreload() {
        if(OI.preloadActive){
            addParallel(new SetPos(Robot.scoop, Constants.scoopDepositPreload));
            addSequential(new SetPos(Robot.kicker, Constants.kickerDepositPreload));
            OI.preloadActive = false;
        }
    }
}