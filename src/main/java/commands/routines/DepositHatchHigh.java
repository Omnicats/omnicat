public class DepositHatchHigh extends CommandGroup {
    public DepositHatchHigh() {
    	addSequential(new SetPos(Robot.lift, Constants.liftRocketHigh_Hatch));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDepositHatch));
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDisengageHatch));
    }
}