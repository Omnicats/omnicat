package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import commands.routines.DepositCargoMid;
import commands.routines.DepositHatchMid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class RocketMid extends CommandGroup {
    public RocketMid() {
        if(OI.hatchSwitch.get()){
            new DepositHatchMid();
        }else{
            new DepositCargoMid();
        }
    }
}