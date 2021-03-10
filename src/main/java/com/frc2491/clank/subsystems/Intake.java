/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.commands.intake.AutoIntake;

public class Intake extends SubsystemBase {
	CANSparkMax coreIntakeMotor;
	/**
	 * Creates a new Intake.
	 */
	public Intake() {
		coreIntakeMotor = new CANSparkMax(Constants.Intake.intakeMotorPort, MotorType.kBrushless);
		
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new AutoIntake(this));
    }

	public void startIntakeMotor(double motorPercent) {
		coreIntakeMotor.set(motorPercent);
	}

	public void stopIntakeMotor() {
		coreIntakeMotor.set(0);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}