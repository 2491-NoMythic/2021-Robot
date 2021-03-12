/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.frc2491.clank.Settings.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
	
	CANSparkMax intakeMotor;
	
	public Intake() {
		intakeMotor = new CANSparkMax(Constants.Intake.intakeMotorPort, MotorType.kBrushless);
	}

	public void runIntakeMotor(double motorPercent){
		intakeMotor.set(motorPercent);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
