package com.frc2491.clank.subsystems;

import com.frc2491.clank.Settings.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spindexer extends SubsystemBase {

	CANSparkMax mainRotationMotor;

	public Spindexer() {
		mainRotationMotor = new CANSparkMax(Constants.Spindexer.mainMotorID, MotorType.kBrushless);
	}

	/**
	 * makes the main part of the spindexer spin
	 * 
	 * @param motorPercent is the speed of the motor
	 * 
	 */
	public void rotate(double motorPercent) {
		mainRotationMotor.set(motorPercent);
		SmartDashboard.putNumber("Spindexer %", motorPercent);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Spindexer Encoder", mainRotationMotor.getEncoder().getVelocity());
	}
}