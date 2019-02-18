public class IntakeHatch extends CommandGroup {
    public IntakeHatch() {
    	addSequential(new SetPos(Robot.lift, Constants.liftHatchLow));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDisengageHatch));
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerHoldHatch));
    }
}