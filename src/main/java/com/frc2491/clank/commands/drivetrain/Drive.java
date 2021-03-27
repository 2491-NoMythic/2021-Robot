package com.frc2491.clank.commands.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.HID.IDriveController;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Drivetrain;

public class Drive extends CommandBase {

	private Drivetrain drivetrain;
	double turnSpeed, lastLeftSpeed, lastRightSpeed;
	double currentLeftSpeed = 0;
	double currentRightSpeed = 0;

	/**
	 * Creates a new Drive.
	 */
	public Drive(Drivetrain drivetrain) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.drivetrain = drivetrain;
		addRequirements(drivetrain);
	}

	/**
	 * Prevents acceleration faster than the limit in constants.
	 * @return an updated nextSpeed.
	 */
	private double limitAcceleration(double prevSpeed, double nextSpeed) {
		double acceleration = nextSpeed - prevSpeed;
		double signOfAcceleration = Math.signum(acceleration);
		if(Math.abs(acceleration) > Constants.Drivetrain.accelerationSpeed) {
			return prevSpeed + Constants.Drivetrain.accelerationSpeed * signOfAcceleration;
		}
		return nextSpeed;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		IDriveController driveController = CurrentHIDs.getInstance().getDriveController();
		
		turnSpeed = 0.5 * driveController.getRawTurnAxis();
		lastLeftSpeed = currentLeftSpeed;
		lastRightSpeed = currentRightSpeed;

		currentLeftSpeed = driveController.getDriveAxisDeadzone() - turnSpeed;
		currentRightSpeed = driveController.getDriveAxisDeadzone() + turnSpeed;

		if(SmartDashboard.getBoolean("Record?", false)){
			System.out.print("{ " + currentLeftSpeed + ", " + currentRightSpeed + " },");
		}

		if (Constants.Drivetrain.useLinerAcceleration) {
			currentLeftSpeed = limitAcceleration(lastLeftSpeed, currentLeftSpeed);
			currentRightSpeed = limitAcceleration(lastRightSpeed, currentRightSpeed);
		}

		drivetrain.drivePercentOutput(currentLeftSpeed, currentRightSpeed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		drivetrain.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
