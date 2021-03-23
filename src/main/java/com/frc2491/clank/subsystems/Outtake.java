package com.frc2491.clank.subsystems;

import com.frc2491.clank.Settings.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Outtake extends SubsystemBase {

	CANSparkMax outtakeMotor;

	public Outtake() {
		outtakeMotor = new CANSparkMax(Constants.Spindexer.outtakeMotorID, MotorType.kBrushless);
	}

	/**
	 * runs outtake motor
	 * @param motorPercent is the speed of the motor
	 */
	public void runOuttakeMotor(double motorPercent) {
		outtakeMotor.set(motorPercent);
		SmartDashboard.putNumber("Outtake %", motorPercent);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Outtake Encoder", outtakeMotor.getEncoder().getVelocity());
	}
	
}