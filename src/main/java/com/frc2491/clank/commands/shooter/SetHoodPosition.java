/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.frc2491.clank.Settings.Variables;
import com.frc2491.clank.subsystems.Shooter;

public class SetHoodPosition extends CommandBase {
	Shooter shooter;
	/**
	 * Creates a new SetHoodPosition.
	 */
	public SetHoodPosition(Shooter shooter) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.shooter = shooter;
		addRequirements(this.shooter);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		//Set hood position to the a degree defined by an enum un constants
		shooter.setHoodPosition(Variables.Shooter.shooterHoodPosition.getAngle());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		shooter.setHoodPosition(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return true;
	}
}
