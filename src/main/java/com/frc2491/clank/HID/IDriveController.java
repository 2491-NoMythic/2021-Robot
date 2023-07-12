package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Add your docs here.
 */
public interface IDriveController {

	public JoystickButton getShootButton();

	public double getRawDriveAxis();

	public double getDriveAxisDeadzone();

	public double getRawTurnAxis();

	public double getHorizontalClimbAxis();

	public double getSliderAxis();
}
