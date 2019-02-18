public class DepositCargoLow extends CommandGroup {
    public DepositCargoLow() {
    	addSequential(new SetPos(Robot.lift, Constants.liftRocketLow_Cargo));
    	addSequential(new SetPos(Robot.scoop, Constants.scoopDown));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerUp));
    }
}