package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Add your docs here.
 */
public interface IOperatorController {

	public JoystickButton getActivateIntakeButton();

	public JoystickButton getReverseIntakeButton();

	public JoystickButton getShooterPrepButton();

	public JoystickButton getShooter1Button();

	public JoystickButton getShooter2Button();

	public JoystickButton getShooter3Button();

	public JoystickButton getShooter4Button();

	public JoystickButton getShootButton();
}
