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
public interface IDriveController {

	public JoystickButton getShootButton();

	public JoystickButton getSlowDriveButton();

	public double getRawDriveAxis();

	public double getDriveAxisDeadzone();

	public double getRawTurnAxis();

	public double getHorizontalClimbAxis();
}
