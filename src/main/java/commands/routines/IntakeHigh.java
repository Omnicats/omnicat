package commands.routines;

import org.usfirst.frc.team1452.robot.OI;
import commands.routines.IntakeCargoHigh;
import commands.routines.IntakeHatch;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeHigh extends CommandGroup {
    public IntakeHigh() {
        if(OI.hatchSwitch.get()){
            new IntakeHatch();
        }else{
            new IntakeCargoHigh();
        }
    }
}