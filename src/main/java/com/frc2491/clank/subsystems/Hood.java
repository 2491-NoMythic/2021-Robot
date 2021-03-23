/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.frc2491.clank.Settings.Constants;

public class Hood extends SubsystemBase {

	Servo servo1;
	Servo servo2;

	/**
	 * Creates a new Shooter.
	 */
	public Hood() {
		
		servo1 = new Servo(Constants.Shooter.servo1PwmID);
		servo2 = new Servo(Constants.Shooter.servo2PwmID);
	}

	/**
	 * Set hood position
	 * @param servoUnits (between 0-180 servo units)
	 * 0 is zero degrees and 180 is 270 degrees on servo (maximum supported hood position)
	 */
	public void setHoodPosition(double servoUnits){
		servo1.setAngle(180 - servoUnits);
		servo2.setAngle(servoUnits);
		SmartDashboard.putNumber("Hood servoUnits", servoUnits);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("servo1", servo1.getAngle());
		SmartDashboard.putNumber("servo2", servo2.getAngle());
	}
}
