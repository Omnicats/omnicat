public class Cancel extends CommandGroup {
    public Cancel() {
    	addParallel(new Freeze(Robot.lift));
    	addParallel(new Freeze(Robot.climber));
    	addParallel(new Freeze(Robot.kicker));
    	addParallel(new Freeze(Robot.scoop));
    }
}
