public class NeutralWithGameObject extends CommandGroup {
    public NeutralWithGameObject() {
    	addParallel(new SetPos(Robot.lift, 0));
    	addParallel(new SetPos(Robot.climber, 0));
    	addParallel(new SetPos(Robot.kicker, OI.hatchSwitch.get() ? Constants.kickerHoldHatch : Constants.kickerUp));
    	addParallel(new SetPos(Robot.scoop, OI.hatchSwitch.get() ? Constants.scoopUp : Constants.scoopHoldCargo));
    }
}