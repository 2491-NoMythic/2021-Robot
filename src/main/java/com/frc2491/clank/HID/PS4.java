package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

import com.frc2491.clank.Settings.Constants;

/**
 * Add your docs here.
 */
public class PS4 implements IOperatorController {

	private final Joystick joystick;
	private JoystickButton activateIntakeButton, prepShooterButton, lowShooterButton, highShooterButton, shootButton;

	public PS4() {
		joystick = new Joystick(Constants.Controller.opertatorControllerID);
		activateIntakeButton = new JoystickButton(joystick, Constants.Controller.PS4.activateIntakeButtonID);
		prepShooterButton = new JoystickButton(joystick, Constants.Controller.PS4.prepShooterButtonID);
		lowShooterButton = new JoystickButton(joystick, Constants.Controller.PS4.lowShooterButtonID);
		highShooterButton = new JoystickButton(joystick, Constants.Controller.PS4.highShooterButtonID);
		shootButton = new JoystickButton(joystick, Constants.Controller.PS4.shootButtonID);
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

	@Override
	public JoystickButton getShootButton() {
		return shootButton;
	}

	

}