/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.subsystems.Shooter;

public class RunShooterAtSpeedPID extends CommandBase {
	/**
	 * Creates a new RunShooterAtSpeedPID.
	 */
	private Shooter mShooter;

	public RunShooterAtSpeedPID(Shooter shooter) {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(shooter);
		mShooter = shooter;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		// this will currently only run the shooter when a button is held down
		mShooter.runLeftShooterVelocity(CurrentHIDs.getInstance().getOperatorController().getShooterSpeed().getSpeed());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		mShooter.runLeftShooterVelocity(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
