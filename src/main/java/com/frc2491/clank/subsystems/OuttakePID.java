package com.frc2491.clank.subsystems;

import com.frc2491.clank.Settings.Constants;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OuttakePID extends SubsystemBase {

	private CANSparkMax outtakeMotor;
	private CANPIDController pidController;
  	private CANEncoder encoder;

	public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;
	int iZone;


	public OuttakePID() {
		outtakeMotor = new CANSparkMax(Constants.Spindexer.outtakeMotorID, MotorType.kBrushless);
		
		// initialze PID controller and encoder objects
		pidController = outtakeMotor.getPIDController();
		encoder = outtakeMotor.getEncoder();
		
		// Smart Motion Coefficients
		maxVel = 2000; // rpm
		maxAcc = 1500;

		// set PID coefficients
		pidController.setP(kP);
		pidController.setI(kI);
		pidController.setD(kD);
		pidController.setIZone(kIz);
		pidController.setFF(kFF);
		pidController.setOutputRange(kMinOutput, kMaxOutput);

			/**
		 * Smart Motion coefficients are set on a CANPIDController object
		 * 
		 * - setSmartMotionMaxVelocity() will limit the velocity in RPM of
		 * the pid controller in Smart Motion mode
		 * - setSmartMotionMinOutputVelocity() will put a lower bound in
		 * RPM of the pid controller in Smart Motion mode
		 * - setSmartMotionMaxAccel() will limit the acceleration in RPM^2
		 * of the pid controller in Smart Motion mode
		 * - setSmartMotionAllowedClosedLoopError() will set the max allowed
		 * error for the pid controller in Smart Motion mode
		 */
		int smartMotionSlot = 0;
		pidController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
		pidController.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
		pidController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
		pidController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

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
		// display PID coefficients on SmartDashboard
		SmartDashboard.putNumber("P outtake", kP);
		SmartDashboard.putNumber("I outtake", kI);
		SmartDashboard.putNumber("D outtake", kD);
		SmartDashboard.putNumber("I Zone outtake", kIz);
		SmartDashboard.putNumber("Feed Forward outtake", kFF);
		SmartDashboard.putNumber("Max Output outtake", kMaxOutput);
		SmartDashboard.putNumber("Min Output outtake", kMinOutput);
	
		// display Smart Motion coefficients
		SmartDashboard.putNumber("Max Velocity outtake", maxVel);
		SmartDashboard.putNumber("Min Velocity outtake", minVel);
		SmartDashboard.putNumber("Max Acceleration outtake", maxAcc);
		SmartDashboard.putNumber("Allowed Closed Loop Error outtake", allowedErr);
		SmartDashboard.putNumber("Set Position outtake", 0);
		SmartDashboard.putNumber("Set Velocity outtake", 0);

		// button to toggle between velocity and smart motion modes
		SmartDashboard.putBoolean("Mode", true);

		SmartDashboard.putNumber("Outtake Encoder", outtakeMotor.getEncoder().getVelocity());

		// read PID coefficients from SmartDashboard
		double p = SmartDashboard.getNumber("P outtake", 0);
		double i = SmartDashboard.getNumber("I outtake", 0);
		double d = SmartDashboard.getNumber("D outtake", 0);
		double iz = SmartDashboard.getNumber("I Zone outtake", 0);
		double ff = SmartDashboard.getNumber("Feed Forward outtake", 0);
		double max = SmartDashboard.getNumber("Max Output outtake", 0);
		double min = SmartDashboard.getNumber("Min Output outtake", 0);
		double maxV = SmartDashboard.getNumber("Max Velocity outtake", 0);
		double minV = SmartDashboard.getNumber("Min Velocity outtake", 0);
		double maxA = SmartDashboard.getNumber("Max Acceleration outtake", 0);
		double allE = SmartDashboard.getNumber("Allowed Closed Loop Error outtake", 0);
	
		// if PID coefficients on SmartDashboard have changed, write new values to controller
		if((p != kP)) { pidController.setP(p); kP = p; }
		if((i != kI)) { pidController.setI(i); kI = i; }
		if((d != kD)) { pidController.setD(d); kD = d; }
		if((iz != kIz)) { pidController.setIZone(iz); kIz = iz; }
		if((ff != kFF)) { pidController.setFF(ff); kFF = ff; }
		if((max != kMaxOutput) || (min != kMinOutput)) { 
		  pidController.setOutputRange(min, max); 
		  kMinOutput = min; kMaxOutput = max; 
		}
		if((maxV != maxVel)) { pidController.setSmartMotionMaxVelocity(maxV,0); maxVel = maxV; }
		if((minV != minVel)) { pidController.setSmartMotionMinOutputVelocity(minV,0); minVel = minV; }
		if((maxA != maxAcc)) { pidController.setSmartMotionMaxAccel(maxA,0); maxAcc = maxA; }
		if((allE != allowedErr)) { pidController.setSmartMotionAllowedClosedLoopError(allE,0); allowedErr = allE; }

		double setPoint, processVariable;
		boolean mode = SmartDashboard.getBoolean("Mode", false);
		if(mode) {
		  setPoint = SmartDashboard.getNumber("Set Velocity", 0);
		  pidController.setReference(setPoint, ControlType.kVelocity);
		  processVariable = encoder.getVelocity();
		} else {
		  setPoint = SmartDashboard.getNumber("Set Position", 0);
		  /**
		   * As with other PID modes, Smart Motion is set by calling the
		   * setReference method on an existing pid object and setting
		   * the control type to kSmartMotion
		   */
		  pidController.setReference(setPoint, ControlType.kSmartMotion);
		  processVariable = encoder.getPosition();
		}
	}
}