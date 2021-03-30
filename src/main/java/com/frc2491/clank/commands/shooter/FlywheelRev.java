package com.frc2491.clank.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.Settings.Variables;
import com.frc2491.clank.subsystems.Shooter;

public class FlywheelRev extends CommandBase {

	Shooter shooter;

	/**
	 * Creates a new FlywheelRev.
	 * Runs the flywheel / shooter at the variables shooter speed 
	 */
	public FlywheelRev(Shooter shooter) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.shooter = shooter;
		addRequirements(shooter);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		//Rev the Flywheel to an enum defined in constants
		shooter.runLeftShooterVelocity(Variables.Shooter.shooterSpeed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		shooter.runLeftShooterVelocity(Constants.ShooterSpeeds.stop.getSpeed());
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
