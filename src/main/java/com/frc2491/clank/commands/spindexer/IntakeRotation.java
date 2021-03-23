package com.frc2491.clank.commands.spindexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Spindexer;

public class IntakeRotation extends CommandBase {

	private Spindexer spindexer;

	/**
	 * Creates a new IndakeRotate command.
	 * Spins the intake at Constants.Spindexer.intakeSpindexerSpeed
	 */
	public IntakeRotation(Spindexer spindexer) {
		this.spindexer = spindexer;
		addRequirements(spindexer);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		//Rotates the spindexer at a set speed to intake balls at a uniform rate
		spindexer.rotate(Constants.Spindexer.intakeSpindexerSpeed);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
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
