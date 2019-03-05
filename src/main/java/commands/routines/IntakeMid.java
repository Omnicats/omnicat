package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import commands.routines.IntakeCargoMid;
import commands.routines.IntakeHatch;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class IntakeMid extends CommandGroup {
    public IntakeMid() {
        if(OI.hatchSwitch.get()){
            new IntakeHatch();
        }else{
            new IntakeCargoMid();
        }
    }
}