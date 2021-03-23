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
