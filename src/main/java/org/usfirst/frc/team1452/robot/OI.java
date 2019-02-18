package org.usfirst.frc.team1452.robot;

import edu.wpi.first.wpilibj.Joystick;
import util.Constants;
import Robot;


public class OI {
	public static Joystick mechBoard = new Joystick(2);
	public static JoystickButton hatchSwitch = new JoystickButton(mechBoard, 0);
	JoystickButton rocketLowButton = new JoystickButton(mechBoard, 0);
	JoystickButton rocketMidButton = new JoystickButton(mechBoard, 0);
	JoystickButton rocketHighButton = new JoystickButton(mechBoard, 0);
	JoystickButton cargoShipButton = new JoystickButton(mechBoard, 0);
	JoystickButton intakeHighButton = new JoystickButton(mechBoard, 0);
	JoystickButton intakeLowButton = new JoystickButton(mechBoard, 0);
    public static JoystickButton triggerButton = new JoystickButton(mechBoard, 0);
    JoystickButton cancelButton = new JoystickButton(mechBoard, 0);
    JoystickButton neutralButton = new JoystickButton(mechBoard, 0);
    JoystickButton liftDownButton = new JoystickButton(mechBoard, 0);
	
	public OI() {
		rocketLowButton.whenPressed(hatchSwitch.get() ? new DepositHatchLow() : new DepositCargoLow());
		rocketMidButton.whenPressed(hatchSwitch.get() ? new DepositHatchMid() : new DepositCargoMid());
		rocketHighButton.whenPressed(hatchSwitch.get() ? new DepositHatchHigh() : new Cancel());
		cargoShipButton.whenPressed(hatchSwitch.get() ? new DepositHatchLow() : new DepositCargoShip());
		intakeHighButton.whenPressed(hatchSwitch.get() ? new Cancel() : new IntakeCargoHigh());
		intakeLowButton.whenPressed(hatchSwitch.get() ? new IntakeHatchLow() : new IntakeCargoLow())
		cancelButton.whenPressed(new Cancel());
		neutralButton.whenPressed(new Neutral());
		liftDownButton.whenPressed(new NeutralWithGameObject());
	}
}
