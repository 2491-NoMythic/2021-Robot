/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.HID;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import com.frc2491.clank.Settings.Constants.ShooterSpeeds;
import com.frc2491.clank.Settings.Constants;

/**
 * Add your docs here.
 */
public class PS4 implements IOperatorController {

	private final Joystick joystick;
	private JoystickButton activateIntakeButton, activateClimbButton, deactivateClimbButton, activateRobotUpButton,
			deactivateRobotUpButton, climbSaftey1, climbSaftey2, funnelerAndIndexer, shooterPrepButton, runIndexer, backIndexer, 
			setHoodPositionOne, setHoodPositionTwo, setHoodPositionThree;

	public PS4() {
		joystick = new Joystick(Constants.Controller.opertatorControllerID);
		activateIntakeButton = new JoystickButton(joystick, Constants.Controller.PS4.activateIntakeButtonID);
		activateClimbButton = new JoystickButton(joystick, Constants.Controller.PS4.activateClimberButtonID);
		deactivateClimbButton = new JoystickButton(joystick, Constants.Controller.PS4.deactivateClimbButtonID);
		activateRobotUpButton = new JoystickButton(joystick, Constants.Controller.PS4.activateRobotUpButtonID);
		deactivateRobotUpButton = new JoystickButton(joystick, Constants.Controller.PS4.deactivateRobotUpButtonID);
		climbSaftey1 = new JoystickButton(joystick, Constants.Controller.PS4.climbSaftey1ID);
		climbSaftey2 = new JoystickButton(joystick, Constants.Controller.PS4.climbSaftey2ID);
		funnelerAndIndexer = new JoystickButton(joystick, Constants.Controller.PS4.funnelerAndIndexerID);
		shooterPrepButton = new JoystickButton(joystick, Constants.Controller.PS4.shooterButtonID);
		runIndexer = new JoystickButton(joystick, Constants.Controller.PS4.runIndexerButtonID);
		backIndexer = new JoystickButton(joystick, Constants.Controller.PS4.backIndexerButtonID);
		setHoodPositionOne = new JoystickButton(joystick, Constants.Controller.PS4.setHoodPositionOneID);
		setHoodPositionTwo = new JoystickButton(joystick, Constants.Controller.PS4.setHoodPositionTwoID);
		setHoodPositionThree = new JoystickButton(joystick, Constants.Controller.PS4.setHoodPositionThreeID);
	}

	@Override
	public JoystickButton getActivateIntakeButton() {
		return activateIntakeButton;
	}

	@Override
	public double getIntakeAxis() {
		return joystick.getRawAxis(Constants.Intake.intakeAxisID);
	}

	@Override
	public double getLeftClimbAxis() {
		return joystick.getRawAxis(Constants.Climber.rightAxisID);
	}

	@Override
	public JoystickButton getActivateLiftButton() {
		return activateClimbButton;
	}

	@Override
	public JoystickButton getActivateRobotUp() {
		return activateRobotUpButton;
	}

	@Override
	public JoystickButton getDisableRobotUp() {
		return deactivateRobotUpButton;
	}

	@Override
	public boolean climbSaftey() {
		return climbSaftey1.get() && climbSaftey2.get();
	}

	@Override
	public JoystickButton getDeactivateLiftButton() {
		return deactivateClimbButton;
	}

	@Override
	public JoystickButton getClimbCheck1() {
		return climbSaftey1;
	}

	@Override
	public JoystickButton getClimbCheck2() {
		return climbSaftey2;
	}

	@Override
	public JoystickButton getShooterPrepButton() {
		return shooterPrepButton;
	}

	@Override
	public JoystickButton getFunnelerAndIndexer() {
		return funnelerAndIndexer;
	}

	@Override
	public JoystickButton runIndexer() {
		return runIndexer;
	}

	@Override
	public JoystickButton backIndexer() {
		return backIndexer;
	}

	public ShooterSpeeds getShooterSpeed() {
		// TODO Picking STOP as the default. Not sure what default should be. 
		// We should remove the if for the default after we know what it is.
		ShooterSpeeds speed = ShooterSpeeds.stop;
		if (joystick.getPOV() == 0) {
			speed = ShooterSpeeds.lowSpeed;
		}else if (joystick.getPOV() == 270) {
			speed = ShooterSpeeds.midSpeed;
		}else if (joystick.getPOV() == 180) {
			speed = ShooterSpeeds.highSpeed;
		}else if (joystick.getPOV() == 90) {
			speed = ShooterSpeeds.stop;
		}
		return speed;
	}

	@Override
	public JoystickButton getShooterHoodPositionOneButton() {
		return setHoodPositionOne;
	}

	@Override
	public JoystickButton getShooterHoodPositionTwoButton() {
		return setHoodPositionTwo;
	}

	@Override
	public JoystickButton getShooterHoodPositionThreeButton() {
		return setHoodPositionThree;
	}

}