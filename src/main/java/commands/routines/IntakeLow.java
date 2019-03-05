package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import org.usfirst.frc.team1452.robot.Robot;

import commands.global.SetPos;
import commands.global.WaitForTrigger;
import commands.routines.IntakeCargoLow;
import commands.routines.IntakeHatch;
import edu.wpi.first.wpilibj.command.CommandGroup;
import util.Constants;

public class IntakeLow extends CommandGroup {
    public IntakeLow() {
        if(OI.hatchSwitch.get()){
            new IntakeHatch();
        }else{
            new IntakeCargoLow();
        }
    }
}