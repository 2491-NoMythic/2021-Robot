/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.climber;
import com.frc2491.clank.subsystems.Drivetrain;
import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RobotUp extends CommandBase {

	private Drivetrain m_Drivetrain;
	private Climber m_Climber;
	private RobotUpState currentState;
	private double rightSpeed, leftSpeed;
	/**
	 * Creates a new RobotUp.
	 */
	private enum RobotUpState{
		Stopped, Moving, LeftDrive, RightDrive;
	}

	public RobotUp(Drivetrain drivetrain, Climber climber) {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(drivetrain);
		addRequirements(climber);
		m_Drivetrain = drivetrain;
		m_Climber = climber;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		m_Climber.setShifterOn();
		currentState = RobotUpState.Stopped;
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		rightSpeed = (CurrentHIDs.getInstance().getDriveController().getRawDriveAxis()/2) + (getRightStickScaled()/2);
		leftSpeed = (CurrentHIDs.getInstance().getDriveController().getRawDriveAxis()/2) + (getLeftStickScaled()/2);
		switch(currentState){
			case Moving:
				m_Climber.disengageLeftBreak();
				m_Climber.disengageRightBreak();
				m_Drivetrain.drivePercentOutput(leftSpeed, rightSpeed);
				changeState(rightSpeed, leftSpeed);
				break;
			case RightDrive:
				m_Climber.engageLeftBreak();
				m_Drivetrain.drivePercentOutput(0, rightSpeed);
				changeState(rightSpeed, leftSpeed);
				break;
			case LeftDrive:
				m_Climber.engageRightBreak();
				m_Drivetrain.drivePercentOutput(leftSpeed, 0);
				changeState(rightSpeed, leftSpeed);
				break;
			case Stopped:
				System.out.println("Stopped");
				m_Climber.engageLeftBreak();
				m_Climber.engageRightBreak();
				changeState(rightSpeed, leftSpeed);
				break;
		}
	}

	private void changeState(double leftSpeedLocal, double rightSpeedLocal){
		if(Math.abs(leftSpeedLocal) >= 0.05 && Math.abs(rightSpeedLocal) >= 0.05){
			currentState = RobotUpState.Moving;
		} else if (Math.abs(leftSpeedLocal) <= 0.05 && Math.abs(rightSpeedLocal) >= 0.05){
			currentState = RobotUpState.RightDrive;
		} else if (Math.abs(leftSpeedLocal) >= 0.05 && Math.abs(rightSpeedLocal) <= 0.05){
			currentState = RobotUpState.LeftDrive;
		} else {
			currentState = RobotUpState.Stopped;
		}

	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_Drivetrain.stop();
		m_Climber.setShifterOff();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}

	public double getLeftStickScaled()
	{
		if(CurrentHIDs.getInstance().getDriveController().getHorizontalClimbAxis() > 0){
			return 0;
		}else{
			return CurrentHIDs.getInstance().getDriveController().getHorizontalClimbAxis() * -1;
		}
	}

	public double getRightStickScaled()
	{
		if(CurrentHIDs.getInstance().getDriveController().getHorizontalClimbAxis() < 0){
			return 0;
		}else{
			return CurrentHIDs.getInstance().getDriveController().getHorizontalClimbAxis();
		}
	}
}
