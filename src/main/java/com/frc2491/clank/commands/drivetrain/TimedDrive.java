package com.frc2491.clank.commands.drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.subsystems.Drivetrain;

public class TimedDrive extends CommandBase {
	/**
	 * Creates a new timeDrive.
	 */
	Drivetrain drivetrain;
	double speedOfDrive;
	double timeOfDrive;
	Timer activeTimer;
	boolean timeDriveFinished;
	public TimedDrive(Drivetrain driveTransfer, double speedDriveTransfer, double timeTransfer) {
		drivetrain = driveTransfer;
		addRequirements(drivetrain);
		speedOfDrive = speedDriveTransfer;
		timeOfDrive = timeTransfer;
		activeTimer = new Timer();
		// Use addRequirements() here to declare subsystem dependencies.
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		drivetrain.drivePercentOutput(speedOfDrive);
		activeTimer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}
	// I have no idea what's parameter should be in end(???)
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		drivetrain.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return timeOfDrive <= activeTimer.get();
	}
}
