/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.frc2491.clank.commands.drivetrain.Drive;
import com.frc2491.clank.commands.drivetrain.LineupDrive;
import com.frc2491.clank.commands.drivetrain.Rotate;
import com.frc2491.clank.commands.intake.IntakeCommand;
import com.frc2491.clank.commands.shooter.PrepareShooter;
import com.frc2491.clank.commands.shooter.RunShooterAtSpeedPID;
import com.frc2491.clank.commands.shooter.UpdateShooterParams;
import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.HID.IDriveController;
import com.frc2491.clank.HID.IOperatorController;
import com.frc2491.clank.Settings.Constants;

import com.frc2491.clank.subsystems.Drivetrain;
import com.frc2491.clank.subsystems.Shooter;

import com.frc2491.clank.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
//import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.frc2491.clank.commands.spindexer.OuttakeMotorShoot;
import com.frc2491.clank.commands.spindexer.ShootingRotation;
import com.frc2491.clank.commands.spindexer.StoreBalls;
import com.frc2491.clank.subsystems.Spindexer;

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
	private final RunShooterAtSpeedPID shooterAtSpeedPID = new RunShooterAtSpeedPID(shooter);
	// private final AutonomousCommand autonomousCommand = new AutonomousCommand(m_drivetrain, m_Shooter, m_Indexer, Constants.Drivetrain.timeDriveSpeed, Constants.Drivetrain.timeDriveTime);

	/**
	 * The container for the robot.  Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();

		//Set the default command to grab controller axis
		drivetrain.setDefaultCommand(new Drive(drivetrain));
		intake.setDefaultCommand(new IntakeCommand(intake));
		shooter.setDefaultCommand(new UpdateShooterParams(shooter));
	}

	/**
	 * We are configuring some of the button presses here. Some are also defined within subsystems.
	 */
	private void configureButtonBindings() {
		IOperatorController operatorController = currentHIDs.getOperatorController();
		IDriveController driveController = currentHIDs.getDriveController();

		SmartDashboard.putData(shooterAtSpeedPID);
		SmartDashboard.putNumber("Axis", operatorController.getLeftClimbAxis());
		SmartDashboard.putData("TurnUp", new Rotate(drivetrain, 30));

		//Button assignments
		//.and is used to create the safteys. Note that in current form safteys are not neccesary for turining off the system.

		SmartDashboard.putData("TurnUp", new Rotate(drivetrain, 30));
		//operatorController.getShooterRevFlywheelButton().whenHeld(new FlywheelRev(m_Shooter, Variables.Shooter.shooterSpeed));

		operatorController.getShooterPrepButton().whileHeld(new ParallelCommandGroup(new ShootingRotation(spindexer), new PrepareShooter(shooter)));
		operatorController.getActivateIntakeButton().whileHeld(new ParallelCommandGroup(new StoreBalls(spindexer), new IntakeCommand(intake)));
		
		driveController.getSlowDriveButton().whileHeld(new LineupDrive(drivetrain));
		driveController.getShootButton().whileHeld(new OuttakeMotorShoot(spindexer));
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	
}
