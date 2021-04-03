package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import com.frc2491.clank.Settings.Constants;

/**
 * Add your docs here.
 */
public class ButtonBoard implements IOperatorController {

	private final Joystick joystick;
	private JoystickButton activateIntakeButton, prepShooterButton, shooterLocation1Button, shooterLocation2Button, shooterLocation3Button, shooterLocation4Button, shootButton;

	public ButtonBoard() {
		joystick = new Joystick(Constants.Controller.opertatorControllerID);
		activateIntakeButton = new JoystickButton(joystick, Constants.Controller.ButtonBoard.activateIntakeButtonID);
		prepShooterButton = new JoystickButton(joystick, Constants.Controller.ButtonBoard.prepShooterButtonID);
		shooterLocation1Button = new JoystickButton(joystick, Constants.Controller.ButtonBoard.shooterLocation1ButtonID);
		shooterLocation2Button = new JoystickButton(joystick, Constants.Controller.ButtonBoard.shooterLocation2ButtonID);
		shooterLocation3Button = new JoystickButton(joystick, Constants.Controller.ButtonBoard.shooterLocation3ButtonID);
		shooterLocation4Button = new JoystickButton(joystick, Constants.Controller.ButtonBoard.shooterLocation4ButtonID);
		shootButton = new JoystickButton(joystick, Constants.Controller.ButtonBoard.shootButtonID);
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
	public JoystickButton getShooter1Button() {
		return shooterLocation1Button;
	}

	@Override
	public JoystickButton getShooter2Button() {
		return shooterLocation2Button;
	}

	@Override
	public JoystickButton getShooter3Button() {
		return shooterLocation3Button;
	}

	@Override
	public JoystickButton getShooter4Button() {
		return shooterLocation4Button;
	}

	@Override
	public JoystickButton getShootButton() {
		return shootButton;
	}

}