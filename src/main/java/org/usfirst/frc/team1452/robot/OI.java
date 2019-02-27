package org.usfirst.frc.team1452.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import mechanism.drive.CameraControl;
import mechanism.drive.DriverControl;
import mechanism.drive.DriverThrottleCameraTurn;
import commands.routines.*;


public class OI {
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

	
	public OI() {
		rocketLowButton.whenPressed(hatchSwitch.get() ? new DepositHatchLow() : new DepositCargoLow());
		rocketMidButton.whenPressed(hatchSwitch.get() ? new DepositHatchMid() : new DepositCargoMid());
		rocketHighButton.whenPressed(hatchSwitch.get() ? new DepositHatchHigh() : new Cancel());
		cargoShipButton.whenPressed(hatchSwitch.get() ? new DepositHatchLow() : new DepositCargoShip());
		intakeHighButton.whenPressed(hatchSwitch.get() ? new Cancel() : new IntakeCargoHigh());
		intakeLowButton.whenPressed(hatchSwitch.get() ? new IntakeHatch() : new IntakeCargoLow());
		cancelButton.whenPressed(new Cancel()); //make a group command that switches based on switch
		neutralButton.whenPressed(new Neutral());
		liftDownButton.whenPressed(new NeutralWithGameObject());
		//climbButton.whenPressed(new Climb());
		visionProcessingButton.whenPressed(hatchSwitch.get() ? new DriverThrottleCameraTurn() : new CameraControl());
		visionProcessingButton.whenReleased(new DriverControl());
	}
}
