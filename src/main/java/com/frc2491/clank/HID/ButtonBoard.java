package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import com.frc2491.clank.Settings.Constants;

/**
 * Add your docs here.
 */
public class ButtonBoard implements IOperatorController {

	private final Joystick joystick;
	private JoystickButton activateIntakeButton, prepShooterButton, lowShooterButton, highShooterButton;

	public ButtonBoard() {
		joystick = new Joystick(Constants.Controller.opertatorControllerID);
		activateIntakeButton = new JoystickButton(joystick, Constants.Controller.ButtonBoard.activateIntakeButtonID);
		prepShooterButton = new JoystickButton(joystick, Constants.Controller.ButtonBoard.prepShooterButtonID);
		lowShooterButton = new JoystickButton(joystick, Constants.Controller.ButtonBoard.lowShooterButtonID);
		highShooterButton = new JoystickButton(joystick, Constants.Controller.ButtonBoard.highShooterButtonID);
	}

	@Override
	public JoystickButton getActivateIntakeButton() {
		return activateIntakeButton;
	}

	@Override
	public JoystickButton getShooterPrepButton() {
		return prepShooterButton;
	}

	@Override
	public JoystickButton getShooterLowButton() {
		return lowShooterButton;
	}

	@Override
	public JoystickButton getShooterHighButton() {
		return highShooterButton;
	}

}