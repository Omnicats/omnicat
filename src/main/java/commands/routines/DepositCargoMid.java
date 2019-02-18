public class DepositCargoMid extends CommandGroup {
    public DepositCargoMid() {
    	addSequential(new SetPos(Robot.lift, Constants.liftRocketMedium_Cargo));
    	addSequential(new SetPos(Robot.scoop, Constants.scoopDown));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerUp));
    }
}