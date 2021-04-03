package com.frc2491.clank.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.Settings.Variables;

public class SetShooterSpeed extends CommandBase {

	private Constants.ShooterSpeeds speed;
	private Constants.ShooterHoodPositions position;

	/**
	 * Creates a new ShooterSpeed.
	 * Sets both flywheel speed and hood position
	 * This is kinda goofy doing this in a command, but it hooks up to the buttons easy
	 */
	public SetShooterSpeed(Constants.ShooterSpeeds speed, Constants.ShooterHoodPositions position) {
		this.speed = speed;
		this.position = position;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		Variables.Shooter.shooterSpeed = speed.getSpeed();
		Variables.Shooter.shooterHoodPosition = position.getAngle();
	}
}
