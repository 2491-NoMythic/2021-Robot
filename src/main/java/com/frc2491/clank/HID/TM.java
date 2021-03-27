package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import com.frc2491.clank.Settings.Constants;

/**
 * Add your docs here.
 */
public class TM implements IDriveController {

	private Joystick joystick;
	private JoystickButton shootButton;

	public TM() {
		joystick = new Joystick(Constants.Controller.driveControllerID);
		shootButton = new JoystickButton(joystick, Constants.Controller.TM.triggerShooterButtonID);
	}

	@Override
	public double getRawDriveAxis() {
		return joystick.getRawAxis(Constants.Drivetrain.driveMainAxis);
	}

	@Override
	public double getDriveAxisDeadzone() {
		return getAxisDeadzoned(joystick.getRawAxis(Constants.Drivetrain.driveMainAxis));
	}

	@Override
	public double getRawTurnAxis() {
		return joystick.getRawAxis(Constants.Drivetrain.driveTurnAxis);
	}

	@Override
	public double getHorizontalClimbAxis() {
		return joystick.getRawAxis(Constants.Drivetrain.driveHorizontalAxis);
	}

	private double getAxisDeadzoned(double value) {
		value = value * Math.abs(value);
		return Math.abs(value) > Constants.Drivetrain.deadzone ? value : 0;
	}

	@Override
	public JoystickButton getShootButton() {
		return shootButton;
	}
}