package com.frc2491.clank.subsystems;

import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.HID.IOperatorController;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.Settings.Variables;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PositionButtonChecker extends SubsystemBase {

    private final CurrentHIDs currentHIDs = CurrentHIDs.getInstance();
    
	public PositionButtonChecker() {
	}

    @Override
    public void periodic() {
        IOperatorController operatorController = currentHIDs.getOperatorController();
        boolean button1 = operatorController.getShooter1Button().get();
        boolean button2 = operatorController.getShooter2Button().get();
        boolean button3 = operatorController.getShooter3Button().get();
        boolean button4 = operatorController.getShooter4Button().get();

        if (button1) {
            Variables.Shooter.shooterHoodPosition = Constants.ShooterHoodPositions.position1.getAngle();
            Variables.Shooter.shooterSpeed = Constants.ShooterSpeeds.speed1.getSpeed();
            SmartDashboard.putString("button", "1");
        } else if (button2) {
            Variables.Shooter.shooterHoodPosition = Constants.ShooterHoodPositions.position2.getAngle();
            Variables.Shooter.shooterSpeed = Constants.ShooterSpeeds.speed2.getSpeed();
            SmartDashboard.putString("button", "2");
        } else if (button3) {
            Variables.Shooter.shooterHoodPosition = Constants.ShooterHoodPositions.position3.getAngle();
            Variables.Shooter.shooterSpeed = Constants.ShooterSpeeds.speed3.getSpeed();
            SmartDashboard.putString("button", "3");
        } else if (button4) {
            Variables.Shooter.shooterHoodPosition = Constants.ShooterHoodPositions.position4.getAngle();
            Variables.Shooter.shooterSpeed = Constants.ShooterSpeeds.speed4.getSpeed();
            SmartDashboard.putString("button", "4");
        } else {
            SmartDashboard.putString("button", "none");
        }

    }
	
}