/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Add your docs here.
 */
public interface IOperatorController {

	public JoystickButton getActivateIntakeButton();

	public JoystickButton getShooterPrepButton();

	public JoystickButton getShooterLowButton();

	public JoystickButton getShooterHighButton();
}
