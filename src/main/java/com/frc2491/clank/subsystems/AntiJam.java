package com.frc2491.clank.subsystems;

import com.frc2491.clank.Settings.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AntiJam extends SubsystemBase {

	CANSparkMax antiJamMotor;

	public AntiJam() {
		antiJamMotor = new CANSparkMax(Constants.Spindexer.antiJamMotorID, MotorType.kBrushless);
	}

	/**
	 * runs the anti jam motor at the given speed
	 * @param motorPercent is the speed of the motor
	 */
	public void runAntiJam(double motorPercent) {
		antiJamMotor.set(motorPercent);
		SmartDashboard.putNumber("AntiJam %", motorPercent);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("AntiJam Encoder", antiJamMotor.getEncoder().getVelocity());
	}
	
}