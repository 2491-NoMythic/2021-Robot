/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.spindexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Spindexer;

public class IntakeRotation extends CommandBase {

	private Spindexer spindexer;

	/**
	 * Creates a new Rotate command.
	 */
	public IntakeRotation(Spindexer spindexer) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.spindexer = spindexer;
		addRequirements(spindexer);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		//Rotates the spindexer at a set speed to intake balls at a uniform rate
		spindexer.Rotate(Constants.Spindexer.intakeSpindexerSpeed);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		spindexer.Rotate(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
