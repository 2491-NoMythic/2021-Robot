package com.frc2491.clank.commands.spindexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Spindexer;

public class ShootingRotation extends CommandBase {

	private Spindexer spindexer;

	/**
	 * Creates a new Shooting Rotate command.
	 * Spins the spindexer at Constants.Spindexer.shootingSpindexerSpeed
	 */
	public ShootingRotation(Spindexer spindexer) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.spindexer = spindexer;
		addRequirements(spindexer);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		spindexer.rotate(Constants.Spindexer.shootingSpindexerSpeed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		spindexer.rotate(Constants.ShooterSpeeds.stop.getSpeed());
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}