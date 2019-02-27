package util;

public class Constants {
    //Lift
    public static final double liftDown = 0;
    public static final double liftHatchLow = 820;
    public static final double liftRocketLow_Cargo = 400;
    public static final double liftCargoShip_Cargo = 0;
    public static final double liftRocketMedium_Hatch = 2550;
    public static final double liftRocketMedium_Cargo = 2300;
    public static final double liftRocketHigh_Hatch = 3780;

    public static final double liftKP = 5;
    public static final double liftKI = 0.01;//0.01-0.04
    public static final double liftKD = 250;
    public static final double liftKF = 0.25;//0.3
    public static final int liftMaxV = 700;
    public static final int liftMaxA = 700;
    public static final int liftIZone = 200;
    public static final double liftPThreshold = 50;
    public static final double liftVThreshold = 10;

    //Scoop
    public static final double scoopUp = 0;
    public static final double scoopHoldCargo = -275;
    public static final double scoopDepositCargo = -900;
    public static final double scoopDown = -1300;

    public static final double scoopKP = 15;
    public static final double scoopKI = 0;
    public static final double scoopKD = 0;
    public static final double scoopKF = 0;
    public static final double scoopPThreshold = 25;
    public static final double scoopVThreshold = 10;

    //Kicker
    public static final double kickerUp = 0;
    public static final double kickerHoldHatch = 150;
    public static final double kickerHoldCargo = 330;
    public static final double kickerDepositHatch = 530;
    public static final double kickerDisengageHatch = 655;
    public static final double kickerDown = 810;
    public static final double kickerPanic = 1100;

    public static final double kickerKP = 10;
    public static final double kickerKI = 0;
    public static final double kickerKD = 0;
    public static final double kickerKF = 0;
    public static final int kickerMaxV = 100000;
    public static final int kickerMaxA = 1200;
    public static final double kickerPThreshold = 25;
    public static final double kickerVThreshold = 10;


    //Climber
    public static final double climberForward = 0;
    public static final double climberTest = 100000;
    public static final double climberDown = 235000;
    public static final double climberBack = 417500;

    public static final double climberKP = 1.6;
    public static final double climberKI = 0;
    public static final double climberKD = 0;
    public static final double climberKF = 0;
    public static final int climberMaxV = 2500;
    public static final int climberMaxA = 2500;
    public static final double climberPThreshold = 5000;
    public static final double climberVThreshold = 1000;

    //Drive
    public static final double driveWheelDiameter = 0;
}
