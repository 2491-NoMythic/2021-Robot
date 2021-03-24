package com.frc2491.clank.Settings;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

	public final class Drivetrain {
		//TimeDrive Values
		public final static double timeDriveSpeed = .6;
		public final static double timeDriveTime = 2;
		
		public final static double deadzone = 0.05;

		// drive
		public final static boolean useLinerAcceleration = true;
		public final static double accelerationSpeed = .05;
		public final static int driveTurnAxis = 2;
		public final static int driveMainAxis = 1;
		public final static int driveHorizontalAxis = 0;
		public final static int driveTalonLeftMotor1 = 6;
		public final static int driveTalonLeftMotor2 = 1;
		public final static int driveTalonRightMotor1 = 2;
		public final static int driveTalonRightMotor2 = 3;

		// encoders
		public final static double driveEncoderTicks = 2048.0; // encoder ticks per wheel rotation is 2048
		public final static double driveWheelDiameter = 6.0; // inches
		public final static double driveEncoderToInches = driveWheelDiameter * Math.PI / driveEncoderTicks; // makes
		                                                                                                    // number
		                                                                                                    // inches
		public final static double speedModeRPSToTalonOutput = driveEncoderTicks / 10.0;
		public final static double driveEncoderVelocityToRPS = 1.0 / driveEncoderTicks * 10;
		public final static double driveMaxSpeedRPS = 8.0;

		//Distance
		public final class DistanceDrive {
			public final static double kP = 0;
			public final static double kI = 0;
			public final static double kD = 0;
		}

		public final class RotationCommand {
			public final static double kP = 0;
			public final static double kI = 0;
			public final static double kD = 0;
		}
	}

	public final class Controller {
		// make sure in driver station the drive controller is first, and operator controller is second
		public static final int driveControllerID = 0; 
		public static final int opertatorControllerID = 1;

		public final class ButtonBoard {
			public static final int activateIntakeButtonID = 2491;
			public static final int prepShooterButtonID = 2491;
			public static final int lowShooterButtonID = 2491;
			public static final int highShooterButtonID = 2491;
			public static final int shootButtonID = 2491;
		}

		public final class PS4 {
			public static final int activateIntakeButtonID = 6;
			public static final int prepShooterButtonID = 5;
			public static final int lowShooterButtonID = 4;
			public static final int highShooterButtonID = 2;
			public static final int shootButtonID = 1;
		}

		public final class TM {
			public static final int triggerShooterButtonID = 1;
		}
	}

	public final class Intake {
		public static final int intakeMotorID = 12;
		public static final double autoIntakeSpeed = 0.5;
	}

	public final class Spindexer {
		public static final int mainMotorID = 10;
		public static final int outtakeMotorID = 13;
		public static final int antiJamMotorID = 11;

		public static final double intakeSpindexerSpeed = -.3;
		public static final double shootingSpindexerSpeed = -.3;

		public static final double shootingOutTakeSpeed = 0.5;
		public static final double antiJamIntakeSpeed = .05;
	}

	public final class Shooter {
		// shooter
		public static final int shooterTalonLeftMotorID = 4;
		public static final int shooterTalonRightMotorID = 5;
		public static final int servo1PwmID = 2;
		public static final int servo2PwmID = 3;
		// encoders
		public final static double shooterEncoderTicks = 2048.0; // Encoder ticks per wheel rotation is 2048
		public final static double shooterWheelDiameter = 4.0; // Inches
		public final static double shooterEncoderToInches = shooterWheelDiameter * Math.PI / shooterEncoderTicks; // Makes number of inches
		public final static double shooterEncoderVelocityToRPS = 1.0 / shooterEncoderTicks * 10;
		// Speeds
		public static final double shootSpeedRpm = 20000; // Rpm
		public static final double shootSpeedRps = 19500;// TODO change for reality.
		// PID
		public final static int SlotIdx = 0;
		public final static int PIDLoopIdx = 0;
		public final static int TimeoutMs = 0;
		public final static double kP = 0.275;
		public final static double kI = 0.0006;
		public final static double kD = 0;
		public final static double kF = 0.0455;
		public final static int kIzone = 100;
		public final static double PeakOutput = 0;
	}

	public enum ShooterSpeeds {
		stop(0), lowSpeed(3000), highSpeed(8000);
		
		private double speed;

		private ShooterSpeeds(double speed) {
			this.speed = speed;
		}

		public double getSpeed() {
			return speed;
		}
	}

	public enum ShooterHoodPositions {
		collapsed(0), lowHood(90), highHood(170);

		private double angle;

		private ShooterHoodPositions(double angle) {
			this.angle = angle;
		}

		public double getAngle() {
			return angle;
		}
	}
}
