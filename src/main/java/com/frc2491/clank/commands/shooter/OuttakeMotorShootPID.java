package com.frc2491.clank.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.OuttakePID;

public class OuttakeMotorShootPID extends CommandBase {

	private OuttakePID outtake;
	/**
	 * Creates a new Outtake Motor Shoot command.
	 * Turns on the outtake motor to shoot balls based on constant shooter speeds.
	 */
	public OuttakeMotorShootPID(OuttakePID outtake) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.outtake = outtake;
		addRequirements(outtake);
	}

	// Called when the command is initially scheduled.
	@Override
	public void execute() {
		outtake.runOuttakeMotor(Constants.Spindexer.shootingOutTakeSpeed);
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		outtake.runOuttakeMotor(Constants.ShooterSpeeds.stop.getSpeed());
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}