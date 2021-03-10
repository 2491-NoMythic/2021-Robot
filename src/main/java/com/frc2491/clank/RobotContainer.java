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
import com.frc2491.clank.commands.intake.AutoIntake;
import com.frc2491.clank.commands.ConnectorAndIndex;
import com.frc2491.clank.commands.FunnlerTest;
import com.frc2491.clank.commands.RunIndexer;
import com.frc2491.clank.commands.ShiftLol;
import com.frc2491.clank.commands.Simple3Ball;
import com.frc2491.clank.commands.funnelOnlyDefaultCommand;
import com.frc2491.clank.commands.climber.ClimbExtendControl;
import com.frc2491.clank.commands.climber.RobotUp;
import com.frc2491.clank.commands.shooter.FlywheelRev;
import com.frc2491.clank.commands.shooter.RunConnector;
import com.frc2491.clank.commands.shooter.RunShooterAtSpeedPID;
import com.frc2491.clank.commands.shooter.SetHoodPosition;
import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.HID.IDriveController;
import com.frc2491.clank.HID.IOperatorController;
import com.frc2491.clank.Settings.Constants;

import com.frc2491.clank.subsystems.Climber;
import com.frc2491.clank.subsystems.Drivetrain;
import com.frc2491.clank.subsystems.Shooter;

import com.frc2491.clank.subsystems.Indexer;
import com.frc2491.clank.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.frc2491.clank.commands.spindexer.ShootingRotation;
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
	private final Drivetrain m_drivetrain = new Drivetrain();
	private final Shooter m_Shooter = new Shooter();
	private final Intake m_Intake = new Intake();
	private final Climber m_Climber = new Climber();
	private final Spindexer m_Spindexer = new Spindexer();

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
	private final RunShooterAtSpeedPID shooterAtSpeedPID = new RunShooterAtSpeedPID(m_Shooter);
	private final RobotUp robotUp = new RobotUp(m_drivetrain, m_Climber);
	// private final AutonomousCommand autonomousCommand = new AutonomousCommand(m_drivetrain, m_Shooter, m_Indexer, Constants.Drivetrain.timeDriveSpeed, Constants.Drivetrain.timeDriveTime);

	/**
	 * The container for the robot.  Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();

		//Set the default command to grab controller axis
		m_drivetrain.setDefaultCommand(new Drive(m_drivetrain));
		m_Intake.setDefaultCommand(new AutoIntake(m_Intake));
	}

	/**
	 * We are configuring some of the button presses here. Some are also defined within subsystems.
	 */
	private void configureButtonBindings() {
		IOperatorController operatorController = currentHIDs.getOperatorController();
		IDriveController driveController = currentHIDs.getDriveController();

		SmartDashboard.putData(shooterAtSpeedPID);
		SmartDashboard.putData(new ShiftLol(m_Climber, m_drivetrain));
		SmartDashboard.putNumber("Axis", operatorController.getLeftClimbAxis());
		SmartDashboard.putData("TurnUp", new Rotate(m_drivetrain, 30));

		//Button assignments
		//.and is used to create the safteys. Note that in current form safteys are not neccesary for turining off the system.

		SmartDashboard.putData("TurnUp", new Rotate(m_drivetrain, 30));
		// operatorController.getShooterRevFlywheelButton().whenHeld(new FlywheelRev(m_Shooter, Variables.Shooter.shooterSpeed));

		operatorController.getActivateIntakeButton().whileHeld(new AutoIntake(m_Intake));
		operatorController.getActivateRobotUp().and(operatorController.getClimbCheck1()).and(operatorController.getClimbCheck2()).whenActive(robotUp);
		operatorController.getDisableRobotUp().cancelWhenPressed(robotUp);
		operatorController.getShooterButton().whileHeld(new SequentialCommandGroup(new ShootingRotation(m_Spindexer),shooterAtSpeedPID));
		
		
		operatorController.getShooterHoodPositionOneButton().whenPressed(new SetHoodPosition(m_Shooter, Constants.Shooter.hoodPositionOne));
		operatorController.getShooterHoodPositionTwoButton().whenPressed(new SetHoodPosition(m_Shooter, Constants.Shooter.hoodPositionTwo));
		operatorController.getShooterHoodPositionThreeButton().whenPressed(new SetHoodPosition(m_Shooter, Constants.Shooter.hoodPositionThree));

		driveController.getSlowDrive().whileHeld(new LineupDrive(m_drivetrain));
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	
}
