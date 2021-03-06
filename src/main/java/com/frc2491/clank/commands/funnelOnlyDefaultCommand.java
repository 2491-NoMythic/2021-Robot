/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.HID.IOperatorController;
import com.frc2491.clank.subsystems.Indexer;

public class funnelOnlyDefaultCommand extends CommandBase {
	/**
	 * Creates a new funnelOnlyDefaultCommand.
	 */
	Indexer m_Indexer;

	public funnelOnlyDefaultCommand(Indexer indexer) {
		// Use addRequirements() here to declare subsystem dependencies.
		m_Indexer = indexer;
		addRequirements(indexer);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		IOperatorController operatorController = CurrentHIDs.getInstance().getOperatorController();
		if(operatorController.getLeftClimbAxis() < -0.5){
			m_Indexer.runFunnelMotorLeft(-.5);
			m_Indexer.runFunnelMotorRight(-.5);
		} else if(operatorController.getLeftClimbAxis() > 0.5) {
			m_Indexer.runFunnelMotorLeft(.75);
			m_Indexer.runFunnelMotorRight(1);
		} else {
			m_Indexer.runFunnelMotorLeft(0);
			m_Indexer.runFunnelMotorRight(0);
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
