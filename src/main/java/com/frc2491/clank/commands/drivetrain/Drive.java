/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Controllers.IDriveController;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Drivetrain;

public class Drive extends CommandBase {

  private Drivetrain drivetrain;
  private IDriveController m_ControlBoard;
  double turnSpeed, lastLeftSpeed, lastRightSpeed;
  double currentLeftSpeed = 0;
  double currentRightSpeed = 0;

  /**
   * Creates a new Drive.
   */
  public Drive(IDriveController controlBoard, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    m_ControlBoard = controlBoard;
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

    turnSpeed = 0.5 * m_ControlBoard.getRawTurnAxis();
		
		lastLeftSpeed = currentLeftSpeed;
		lastRightSpeed = currentRightSpeed;
		
		currentLeftSpeed = m_ControlBoard.getDriveAxisDeadzone() - turnSpeed;
    currentRightSpeed = m_ControlBoard.getDriveAxisDeadzone() + turnSpeed;
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
