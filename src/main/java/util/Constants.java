package util;

public class Constants {
    //Lift
    public static final double liftDown = 0;
    public static final double liftHatchLow = 500;
    public static final double liftRocketLow_Cargo = 0;
    public static final double liftCargoShip_Cargo = 0;
    public static final double liftRocketMedium_Hatch = 1000;
    public static final double liftRocketMedium_Cargo = 0;
    public static final double liftRocketHigh_Hatch = 1500;

    public static final double liftKP = 5;
    public static final double liftKI = 0.006;
    public static final double liftKD = 250;
    public static final double liftKF = 0.3;
    public static final int liftMaxV = 0;
    public static final int liftMaxA = 0;
    public static final int liftIZone = 200;

    //Scoop
    public static final double scoopUp = 0;
    public static final double scoopHoldCargo = -275;
    public static final double scoopDown = -1300;

    public static final double scoopKP = 15;
    public static final double scoopKI = 0;
    public static final double scoopKD = 0;
    public static final double scoopKF = 0;

    //Kicker
    public static final double kickerUp = 0;
    public static final double kickerHoldHatch = 0;
    public static final double kickerDown = 0;

    public static final double kickerKP = 0;
    public static final double kickerKI = 0;
    public static final double kickerKD = 0;
    public static final double kickerKF = 0;

    //Climber
    public static final double climberForward = 0;
    public static final double climberDown = 235000;
    public static final double climberBack = 417500;

    public static final double climberKP = 0.01;
    public static final double climberKI = 0;
    public static final double climberKD = 0;
    public static final double climberKF = 0;
}
