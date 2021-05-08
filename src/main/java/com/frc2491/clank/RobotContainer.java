package com.frc2491.clank;

import com.frc2491.clank.Settings.Constants;

import java.util.List;

import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.HID.IDriveController;
import com.frc2491.clank.HID.IOperatorController;
import com.frc2491.clank.commands.drivetrain.Drive;
import com.frc2491.clank.commands.drivetrain.DriveTrajectory;
import com.frc2491.clank.commands.intake.IntakeCommand;
import com.frc2491.clank.commands.intake.IntakeReverse;
import com.frc2491.clank.commands.shooter.OuttakeMotorShoot;
import com.frc2491.clank.commands.shooter.PrepareShooter;
import com.frc2491.clank.commands.shooter.SetShooterSpeed;
import com.frc2491.clank.commands.spindexer.RunAntiJam;
import com.frc2491.clank.commands.spindexer.ShootingRotation;
import com.frc2491.clank.commands.spindexer.SortRotation;
import com.frc2491.clank.subsystems.AntiJam;
import com.frc2491.clank.subsystems.Drivetrain;
import com.frc2491.clank.subsystems.Hood;
import com.frc2491.clank.subsystems.Intake;
import com.frc2491.clank.subsystems.Outtake;
import com.frc2491.clank.subsystems.OuttakePID;
import com.frc2491.clank.subsystems.PhotonCannon;
import com.frc2491.clank.subsystems.PositionButtonChecker;
import com.frc2491.clank.subsystems.Shooter;
import com.frc2491.clank.subsystems.Spindexer;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined.

	/**
	 * Here is where we create the instances of the subsystems. These static instances reflect the physical systems on the
	 * robot. When trying to interface with the physical systems of the robot, these need to be used.
	 */
	private final Drivetrain drivetrain = new Drivetrain();
	private final Shooter shooter = new Shooter();
	private final Intake intake = new Intake();
	private final Spindexer spindexer = new Spindexer();
	private final AntiJam antiJam = new AntiJam();
	private final Outtake outtake = new Outtake();
	private final Hood hood = new Hood();
	private final PhotonCannon photonCannon = new PhotonCannon();
	private final PositionButtonChecker buttonChecker = new PositionButtonChecker();

	// Don't use this at same time as Outtake
	// This PID version has a periodic method implemented to control from the dashboard
	// private final OuttakePID outtakePID = new OuttakePID();

	/**
	 * Here is where we get the current instace of the CurrentHIDs that we are using. There can only be one instance of
	 * the CurrentHIDs ever, so the control board in this class is a refrence to the singleton controlboard.
	 * This contains the current driver and operator controls.
	 */
	private final CurrentHIDs currentHIDs = CurrentHIDs.getInstance();

	/**
	 * Here is where we declare instances of the commands that we want to run. Notice that these will only run the
	 * instantiation of the class once. We only need these here if used more than once in class.
	 */

	/**
	 * The container for the robot.  Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();

		//Set the default command to grab controller axis
		drivetrain.setDefaultCommand(new Drive(drivetrain));

		// SmartDashboard.putData(shooter);
		// SmartDashboard.putData(intake);
		// SmartDashboard.putData(spindexer);
		// SmartDashboard.putData(antiJam);
		// SmartDashboard.putData(outtake);
		// SmartDashboard.putData(hood);
	}

	/**
	 * We are configuring some of the button presses here. Some are also defined within subsystems.
	 */
	private void configureButtonBindings() {
		IOperatorController operatorController = currentHIDs.getOperatorController();
		IDriveController driveController = currentHIDs.getDriveController();

		//Button assignments
		// operatorController.getShooter1Button().whenPressed(new SetShooterSpeed(Constants.ShooterSpeeds.speed1, Constants.ShooterHoodPositions.position1));
		// operatorController.getShooter2Button().whenPressed(new SetShooterSpeed(Constants.ShooterSpeeds.speed2, Constants.ShooterHoodPositions.position2));
		// operatorController.getShooter3Button().whenPressed(new SetShooterSpeed(Constants.ShooterSpeeds.speed3, Constants.ShooterHoodPositions.position3));
		// operatorController.getShooter4Button().whenPressed(new SetShooterSpeed(Constants.ShooterSpeeds.speed4, Constants.ShooterHoodPositions.position4));

		operatorController.getShooterPrepButton().whileHeld(new ParallelCommandGroup(new ShootingRotation(spindexer), new PrepareShooter(shooter, hood)));
		operatorController.getActivateIntakeButton().whileHeld(new ParallelCommandGroup(new SortRotation(spindexer), new IntakeCommand(intake), new RunAntiJam(antiJam)));
		operatorController.getReverseIntakeButton().whileHeld(new ParallelCommandGroup(new IntakeReverse(intake), new RunAntiJam(antiJam)));

		operatorController.getShootButton().whileHeld(new OuttakeMotorShoot(outtake));

		driveController.getShootButton().whileHeld(new OuttakeMotorShoot(outtake));
	}
	
	/**
	 * Method is public here so Robot.java can access it when it changes to teleop.
	 * This way we don't need to worry about the subsystem over there.
	 * Using InstantCommand so we don't even need to create commands.
	 * It will execute the method and immediately quit the command.
	 */
	public void firePhotonCannon() {
		// new InstantCommand(photonCannon::firePhotonCannon, photonCannon);
	}

	public void shutdownPhotonCannon() {
		// new InstantCommand(photonCannon::shutdownPhotonCannon, photonCannon);
	}

	/**
	 * Returns the autonomous command currently in use.
	 */
	public Command getAutonomousCommand() {
		//return new DriveTrajectory(drivetrain).andThen(() -> drivetrain.driveVoltageOutput(0, 0));

		DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(Constants.Drivetrain.trackWidthMeters);

		// Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint (
            new SimpleMotorFeedforward (
                Constants.Drivetrain.ksVolts,
                Constants.Drivetrain.kvVoltSecondsPerMeter,
                Constants.Drivetrain.kaVoltSecondsSquaredPerMeter),
            kDriveKinematics,
            10);

        // Create config for trajectory
        TrajectoryConfig config =
            new TrajectoryConfig(
                Constants.Autonomous.kMaxSpeedMetersPerSecond,
                Constants.Autonomous.kMaxAccelerationMetersPerSecondSquared)

                // Add kinematics to ensure max speed is actually obeyed
                .setKinematics(kDriveKinematics)
                // Apply volatge constraint
                .addConstraint(autoVoltageConstraint);

        // Create Trajectory
        Trajectory autoTrajectory = TrajectoryGenerator.generateTrajectory(
            //Define starting point
            new Pose2d(0,0, new Rotation2d(0)),
            //Pass through a list of defined points 
            List.of(
                new Translation2d(1,1),
                new Translation2d(2,-1)
            ),
            //Define Endpoint
            new Pose2d(3,0, new Rotation2d(0)),
            //Pass config
            config
        );

        RamseteCommand ramseteCommand = new RamseteCommand(
            autoTrajectory,
            drivetrain::getPose,
            new RamseteController(Constants.Autonomous.kRamseteB, Constants.Autonomous.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.Drivetrain.ksVolts,
                Constants.Drivetrain.kvVoltSecondsPerMeter,
                Constants.Drivetrain.kaVoltSecondsSquaredPerMeter),
            kDriveKinematics,
            drivetrain::getWheelSpeeds,
            new PIDController(Constants.Drivetrain.kPDriveVel, 0, 0),
            new PIDController(Constants.Drivetrain.kPDriveVel, 0, 0),
            //RamseteCommand passes volts to the callback
            drivetrain::driveVoltageOutput,
            drivetrain
        );

        //Reset odometry to the starting pose of the trajectory
        drivetrain.resetOdometry(autoTrajectory.getInitialPose());

		return ramseteCommand.andThen(() -> drivetrain.driveVoltageOutput(0, 0));
	}
}
