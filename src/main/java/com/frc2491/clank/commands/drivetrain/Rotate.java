package com.frc2491.clank.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Variables;
import com.frc2491.clank.subsystems.Drivetrain;

public class Rotate extends CommandBase {

	private Drivetrain drivetrain;
	private double degrees;
	private PIDController pid;

	/**
	 * Creates a new Rotate command.
	 */
	public Rotate(Drivetrain drivetrain, double degrees) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.drivetrain = drivetrain;
		addRequirements(drivetrain);
		this.degrees = degrees;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		pid = new PIDController(Variables.Drivetrain.RotationCommand.kP, Variables.Drivetrain.RotationCommand.kI, Variables.Drivetrain.RotationCommand.kD);
		pid.setTolerance(1,10);
		pid.setSetpoint(drivetrain.getRawGyroAngle() + degrees);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		double turnrate = pid.calculate(drivetrain.getRawGyroAngle());
		drivetrain.drivePercentOutput(turnrate*-1, turnrate);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		drivetrain.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return pid.atSetpoint();
	}
}
