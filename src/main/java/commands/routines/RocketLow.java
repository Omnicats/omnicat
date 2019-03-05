package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import commands.routines.DepositCargoLow;
import commands.routines.DepositHatchLow;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class RocketLow extends CommandGroup {
    public RocketLow() {
        if(OI.hatchSwitch.get()){
            new DepositHatchLow();
        }else{
            new DepositCargoLow();
        }
    }
}