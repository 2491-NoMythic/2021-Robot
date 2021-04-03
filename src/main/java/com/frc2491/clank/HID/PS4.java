package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

import com.frc2491.clank.Settings.Constants;

/**
 * Add your docs here.
 */
public class PS4 implements IOperatorController {

	private final Joystick joystick;
	private JoystickButton activateIntakeButton, prepShooterButton, shooterLocation1Button, shooterLocation2Button, shooterLocation3Button, shooterLocation4Button, shootButton;

	public PS4() {
		joystick = new Joystick(Constants.Controller.opertatorControllerID);
		activateIntakeButton = new JoystickButton(joystick, Constants.Controller.PS4.activateIntakeButtonID);
		prepShooterButton = new JoystickButton(joystick, Constants.Controller.PS4.prepShooterButtonID);
		shooterLocation1Button = new JoystickButton(joystick, Constants.Controller.PS4.shooterLocation1ButtonID);
		shooterLocation2Button = new JoystickButton(joystick, Constants.Controller.PS4.shooterLocation2ButtonID);
		shooterLocation3Button = new JoystickButton(joystick, Constants.Controller.PS4.shooterLocation3ButtonID);
		shooterLocation4Button = new JoystickButton(joystick, Constants.Controller.PS4.shooterLocation4ButtonID);
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