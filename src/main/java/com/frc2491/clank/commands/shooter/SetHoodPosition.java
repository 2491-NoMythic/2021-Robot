package com.frc2491.clank.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.Settings.Variables;
import com.frc2491.clank.subsystems.Hood;

public class SetHoodPosition extends CommandBase {

	Hood hood;

	/**
	 * Creates a new SetHoodPosition.
	 */
	public SetHoodPosition(Hood hood) {
		this.hood = hood;
		addRequirements(this.hood);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		//Set hood position to the a degree defined by an enum un constants
		hood.setHoodPosition(Variables.Shooter.shooterHoodPosition.getAngle());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		hood.setHoodPosition(Constants.ShooterHoodPositions.collapsed.getAngle());
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return true;
	}
}
