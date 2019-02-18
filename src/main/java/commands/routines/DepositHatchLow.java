public class DepositHatchLow extends CommandGroup {
    public DepositHatchLow() {
    	addSequential(new SetPos(Robot.lift, Constants.liftHatchLow));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDepositHatch));
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDisengageHatch));
    }
}