
package com.frc2491.clank.commands.spindexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.AntiJam;

public class RunAntiJam extends CommandBase {

	private AntiJam antiJam;

	/**
	 * Creates a new Rotate command.
	 */
	public RunAntiJam(AntiJam antiJam) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.antiJam = antiJam;
		addRequirements(antiJam);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		antiJam.runAntiJam(Constants.Spindexer.antiJamIntakeSpeed);

	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		antiJam.runAntiJam(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}