package org.usfirst.frc.team1452.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import mechanism.drive.CameraControl;
import mechanism.drive.DriverControl;
import mechanism.drive.DriverThrottleCameraTurn;
import commands.routines.*;


public class OI {
	public static boolean preloadActive = true;
	public static Joystick throttleJ = new Joystick(0);
	public static Joystick turnJ = new Joystick(1);  
	public static Joystick mechBoard = new Joystick(2);
	public static JoystickButton hatchSwitch = new JoystickButton(mechBoard, 9);
	JoystickButton rocketLowButton = new JoystickButton(mechBoard, 3);
	JoystickButton rocketMidButton = new JoystickButton(mechBoard, 2);
	JoystickButton rocketHighButton = new JoystickButton(mechBoard, 1);
	JoystickButton cargoShipButton = new JoystickButton(mechBoard, 5);
	JoystickButton intakeHighButton = new JoystickButton(mechBoard, 7);
	JoystickButton intakeLowButton = new JoystickButton(mechBoard, 8);
    public static JoystickButton triggerButton = new JoystickButton(mechBoard, 10);
    JoystickButton cancelButton = new JoystickButton(mechBoard, 11);
    JoystickButton neutralButton = new JoystickButton(mechBoard, 6);
	JoystickButton liftDownButton = new JoystickButton(mechBoard, 4);
	JoystickButton climbButton = new JoystickButton(Robot.turnJ, 11);
	public static JoystickButton climbTrigger = new JoystickButton(Robot.turnJ, 2);
	public static JoystickButton quickTurnButton = new JoystickButton(Robot.throttleJ, 1);
	JoystickButton visionProcessingButton = new JoystickButton(Robot.turnJ, 1);

	public static boolean acquiringCargo = false;

	
	public OI() {
		triggerButton.whenPressed(new DepositPreload());
		rocketLowButton.whenPressed(new RocketLow());
		rocketMidButton.whenPressed(new RocketMid());
		rocketHighButton.whenPressed(new RocketHigh());
		cargoShipButton.whenPressed(new DepositCargoShip());
		intakeHighButton.whenPressed(new IntakeHigh());
		intakeLowButton.whenPressed(new intakeLow());
		cancelButton.whenPressed(new Cancel()); //make a group command that switches based on switch
		neutralButton.whenPressed(new Neutral());
		liftDownButton.whenPressed(new NeutralWithGameObject());
		//climbButton.whenPressed(new Climb());
		visionProcessingButton.whenPressed(hatchSwitch.get() ? new DriverThrottleCameraTurn() : new CameraControl());
		visionProcessingButton.whenReleased(new DriverControl());
	}

	public static double throttleOutput(){
		double throttleOutput = -throttleJ.getY() * Math.abs(throttleJ.getY());
		if (Math.abs(throttleOutput) < 0.025) {
			throttleOutput = 0;
		}
		return throttleOutput;
	}

	public static double turnOutput(){
		double turnOutput = -throttleJ.getY() * Math.abs(throttleJ.getY());
		if (Math.abs(turnOutput) < 0.025) {
			turnOutput = 0;
		}
		return turnOutput;
	}

}
