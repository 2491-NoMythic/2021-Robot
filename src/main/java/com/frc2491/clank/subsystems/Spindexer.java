package com.frc2491.clank.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frc2491.clank.Settings.Constants;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spindexer extends SubsystemBase {

	Spark MainRotationMotor;
	Spark AntiJamMotor;
	TalonSRX OuttakeMotor;

	public Spindexer() {
		MainRotationMotor = new Spark(Constants.Spindexer.MainMotor);
		AntiJamMotor = new Spark(Constants.Spindexer.AntiJamMotor);
		OuttakeMotor = new TalonSRX(Constants.Spindexer.OuttakeMotor);

	}

	/**
	 * makes the main part of the spindexer spin
	 * 
	 * @param motorPercent is the speed of the motor
	 * 
	 */
	public void Rotate(double motorPercent) {
		
		MainRotationMotor.set(motorPercent);
		
	}

	/**
	 * runs the anti jam motor at the given speed
	 * @param motorPercent is the speed of the motor
	 */
	public void RunAntiJam(double motorPercent) {
		AntiJamMotor.set(motorPercent);
	}

	/**
	 * runs outtake motor
	 * @param motorPercent is the speed of the motor
	 */
	public void RunOuttakeMotor(double motorPercent) {
		OuttakeMotor.set(ControlMode.PercentOutput, motorPercent);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}