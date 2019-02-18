public class IntakeCargoLow extends CommandGroup {
    public IntakeCargoLow() {
    	addSequential(new SetPos(Robot.lift, Constants.liftDown));
    	addSequential(new SetPos(Robot.scoop, Constants.scoopDown));
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.scoop, Constants.scoopHoldCargo));
    }
}