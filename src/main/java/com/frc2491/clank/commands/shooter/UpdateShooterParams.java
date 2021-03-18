// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc2491.clank.commands.shooter;

import com.frc2491.clank.HID.CurrentHIDs;
import com.frc2491.clank.subsystems.Shooter;
import com.frc2491.clank.Settings.Variables;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class UpdateShooterParams extends CommandBase {

  Shooter shooter;

  public UpdateShooterParams(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Listends for updates to the shooter hood and speed variables through the two methods defined in the operator controller
    CurrentHIDs.getInstance().getOperatorController().getShooterSpeed();
    CurrentHIDs.getInstance().getOperatorController().getShooterHoodPositions();
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
