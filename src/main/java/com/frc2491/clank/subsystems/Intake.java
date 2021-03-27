package com.frc2491.clank.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.frc2491.clank.Settings.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
	
	CANSparkMax intakeMotor;
	
	public Intake() {
		intakeMotor = new CANSparkMax(Constants.Intake.intakeMotorID, MotorType.kBrushless);
	}

	public void runIntakeMotor(double motorPercent){
		intakeMotor.set(motorPercent);
		SmartDashboard.putNumber("Intake %", motorPercent);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("AntiJam Encoder", intakeMotor.getEncoder().getVelocity());
	}
}
