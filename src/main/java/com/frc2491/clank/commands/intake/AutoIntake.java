/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.intake;

import com.frc2491.clank.subsystems.Intake;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.frc2491.clank.HID.CurrentHIDs;

public class AutoIntake extends CommandBase {
	/**
	 * Creates a new AutoIntake.
	 */
	private Intake m_Intake;

	public AutoIntake(Intake intake) {
		// Use addRequirements() here to declare subsystem dependencies.
		m_Intake = intake;
		addRequirements(intake);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		
		SmartDashboard.putBoolean("intake", true);
		//new RunIndexer(m_Indexer).schedule();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		m_Intake.startIntakeMotor(CurrentHIDs.getInstance().getOperatorController().getIntakeAxis());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_Intake.stopIntakeMotor();
		SmartDashboard.putBoolean("intake", false);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
