package com.frc2491.clank.Settings;

import com.frc2491.clank.Settings.Constants.ShooterHoodPositions;
import com.frc2491.clank.Settings.Constants.ShooterSpeeds;

/**
 * Add your docs here.
 */
public class Variables {

	public static class Indexer {
		public static int ballsLoaded = 0;
		public static boolean finalBallLoaded = false;
	}

	public static class Shooter{
		public static ShooterSpeeds shooterSpeed = ShooterSpeeds.lowSpeed;
		public static ShooterHoodPositions shooterHoodPosition = ShooterHoodPositions.lowHood;
	}

	public static class Drivetrain{
		public static class RotationCommand{
			public static double kP = 0.015;
			public static double kI = 0.028;
			public static double kD = 0;
		}
	}


	public static class Spindexer{
		//this is the amount of time it will take for the spindexer to reverse in sort mode
		public static double sortModeReverseTime = 2; 
		public static double sortModeMaxPower = 0.5;
	}
}
