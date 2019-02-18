public class Neutral extends CommandGroup {
    public Neutral() {
    	addParallel(new SetPos(Robot.lift, 0));
    	addParallel(new SetPos(Robot.climber, 0));
    	addParallel(new SetPos(Robot.kicker, 0));
    	addParallel(new SetPos(Robot.scoop, 0));
    }
}