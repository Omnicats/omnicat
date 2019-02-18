public class DepositCargoShip extends CommandGroup {
    public DepositCargoShip() {
    	addSequential(new SetPos(Robot.lift, Constants.liftCargoShip_Cargo));
    	addSequential(new SetPos(Robot.scoop, Constants.scoopDown));
    	addSequential(new SetPos(Robot.kicker, Constants.kickerUp));
    }
}