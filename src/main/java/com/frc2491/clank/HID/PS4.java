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
import com.frc2491.clank.Settings.Variables;

/**
 * Add your docs here.
 */
public class PS4 implements IOperatorController {

	private final Joystick m_joystick;
	private JoystickButton activateIntakeButton, activateClimbButton, deactivateClimbButton, activateRobotUpButton,
			deactivateRobotUpButton, climbSaftey1, climbSaftey2, funnelerAndIndexer, shooterButton, runIndexer, backIndexer, 
			sleepyShotButton, setHoodPositionOne, setHoodPositionTwo, setHoodPositionThree;

	public PS4() {
		m_joystick = new Joystick(Constants.Controller.opertatorControllerID);
		activateIntakeButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateIntakeButtonID);
		activateClimbButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateClimberButtonID);
		deactivateClimbButton = new JoystickButton(m_joystick, Constants.Controller.PS4.deactivateClimbButtonID);
		activateRobotUpButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateRobotUpButtonID);
		deactivateRobotUpButton = new JoystickButton(m_joystick, Constants.Controller.PS4.deactivateRobotUpButtonID);
		climbSaftey1 = new JoystickButton(m_joystick, Constants.Controller.PS4.climbSaftey1ID);
		climbSaftey2 = new JoystickButton(m_joystick, Constants.Controller.PS4.climbSaftey2ID);
		funnelerAndIndexer = new JoystickButton(m_joystick, Constants.Controller.PS4.funnelerAndIndexerID);
		shooterButton = new JoystickButton(m_joystick, Constants.Controller.PS4.shooterButtonID);
		runIndexer = new JoystickButton(m_joystick, Constants.Controller.PS4.runIndexerButtonID);
		backIndexer = new JoystickButton(m_joystick, Constants.Controller.PS4.backIndexerButtonID);
		sleepyShotButton = new JoystickButton(m_joystick, 10);
		setHoodPositionOne = new JoystickButton(m_joystick, Constants.Controller.PS4.setHoodPositionOneID);
		setHoodPositionTwo = new JoystickButton(m_joystick, Constants.Controller.PS4.setHoodPositionTwoID);
		setHoodPositionThree = new JoystickButton(m_joystick, Constants.Controller.PS4.setHoodPositionThreeID);
	}

	@Override
	public JoystickButton getActivateIntakeButton() {
		return activateIntakeButton;
	}

	@Override
	public double getIntakeAxis() {
		return m_joystick.getRawAxis(Constants.Intake.intakeAxisID);
	}

	@Override
	public double getLeftClimbAxis() {
		return m_joystick.getRawAxis(Constants.Climber.rightAxisID);
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
	public JoystickButton getShooterButton() {
		return shooterButton;
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

	@Override
	public ShooterSpeeds getShooterSpeed() {
		// TODO Picking STOP as the default. Not sure what default should be. 
		// We should remove the if for the default after we know what it is.
		ShooterSpeeds speed = ShooterSpeeds.stop;
		if (m_joystick.getPOV() == 0){
			return ShooterSpeeds.lowSpeed;
		}else if (m_joystick.getPOV() == 270){
			Variables.Shooter.shooterSpeed = ShooterSpeeds.midSpeed;
		}else if (m_joystick.getPOV() == 180){
			Variables.Shooter.shooterSpeed = ShooterSpeeds.highSpeed;
		}else if (m_joystick.getPOV() == 90){
			Variables.Shooter.shooterSpeed = ShooterSpeeds.stop;   
		}else if (sleepyShotButton.get()){
			Variables.Shooter.shooterSpeed = ShooterSpeeds.sleepSpeed;
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