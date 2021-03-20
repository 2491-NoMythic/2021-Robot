
package com.frc2491.clank.commands.spindexer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.subsystems.Spindexer;
import com.frc2491.clank.Settings.Variables;

public class SortRotation extends CommandBase {

	private Spindexer spindexer;
	
	private Timer timer;

	/**
	 * Creates a new Rotate command.
	 */
	public SortRotation(Spindexer spindexer) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.spindexer = spindexer;
		addRequirements(spindexer);
		
		timer = new Timer(); 
		
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {

		timer.reset();
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {

		
		double time = timer.get();

		spindexer.Rotate(Math.sin(time * Math.PI / Variables.Spindexer.sortModeReverseTime) * Variables.Spindexer.sortModeMaxPower );
		
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