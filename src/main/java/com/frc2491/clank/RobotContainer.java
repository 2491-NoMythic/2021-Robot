package com.frc2491.clank;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.HID.IDriveController;
import com.frc2491.clank.HID.IOperatorController;
import com.frc2491.clank.commands.drivetrain.Drive;
import com.frc2491.clank.commands.intake.IntakeCommand;
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
import com.frc2491.clank.subsystems.Shooter;
import com.frc2491.clank.subsystems.Spindexer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

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
		operatorController.getShooter1Button().whenPressed(new SetShooterSpeed(Constants.ShooterSpeeds.speed1, Constants.ShooterHoodPositions.position1));
		operatorController.getShooter2Button().whenPressed(new SetShooterSpeed(Constants.ShooterSpeeds.speed2, Constants.ShooterHoodPositions.position2));
		operatorController.getShooter3Button().whenPressed(new SetShooterSpeed(Constants.ShooterSpeeds.speed3, Constants.ShooterHoodPositions.position3));
		operatorController.getShooter4Button().whenPressed(new SetShooterSpeed(Constants.ShooterSpeeds.speed4, Constants.ShooterHoodPositions.position4));

		operatorController.getShooterPrepButton().whileHeld(new ParallelCommandGroup(new ShootingRotation(spindexer), new PrepareShooter(shooter, hood)));
		operatorController.getActivateIntakeButton().whileHeld(new ParallelCommandGroup(new SortRotation(spindexer), new IntakeCommand(intake), new RunAntiJam(antiJam)));
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
		new InstantCommand(photonCannon::firePhotonCannon, photonCannon);
	}

	public void shutdownPhotonCannon() {
		new InstantCommand(photonCannon::shutdownPhotonCannon, photonCannon);
	}
}
