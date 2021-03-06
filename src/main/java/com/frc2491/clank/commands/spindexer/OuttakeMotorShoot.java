

package com.frc2491.clank.commands.spindexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Spindexer;

public class OuttakeMotorShoot extends CommandBase {

	private Spindexer spindexer;

	/**
	 * Creates a new Rotate command.
	 */
	public OuttakeMotorShoot(Spindexer spindexer) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.spindexer = spindexer;
		addRequirements(spindexer);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		spindexer.RunOuttakeMotor(Constants.Spindexer.shootingOutTakeSpeed);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		spindexer.RunOuttakeMotor(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}