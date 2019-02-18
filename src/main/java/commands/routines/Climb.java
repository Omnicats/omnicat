public class Climb extends CommandGroup {
    public Climb() {
    	addSequential(new SetPos(Robot.climber, Constants.climberDown);
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.climber, Constants.climberBack);
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.climber, Constants.climberDown);
    	addSequential(new WaitForTrigger(OI.triggerButton));
    	addSequential(new SetPos(Robot.climber, Constants.climberBack);
    }
}