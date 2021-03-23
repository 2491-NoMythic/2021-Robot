// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc2491.clank.commands.shooter;

import com.frc2491.clank.Settings.Variables;
import com.frc2491.clank.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PrepareShooter extends CommandBase {

Shooter shooter;

  /** Creates a new PrepareShooter. */
  public PrepareShooter(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(this.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Combines the actions in FlywheelRev and SetHoodPosition into one command for joint execution
    shooter.setHoodPosition(Variables.Shooter.shooterHoodPosition.getAngle());
		shooter.runLeftShooterVelocity(Variables.Shooter.shooterSpeed.getSpeed());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
