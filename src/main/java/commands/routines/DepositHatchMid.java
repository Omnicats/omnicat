public class DepositHatchMid extends CommandGroup {
    public DepositHatchMid() {
    	addSequential(new SetPos(Robot.lift, Constants.liftRocketMedium_Hatch));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDepositHatch));
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerDisengageHatch));
    }
}