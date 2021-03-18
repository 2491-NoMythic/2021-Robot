// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc2491.clank.commands.spindexer;

import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Spindexer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StoreBalls extends CommandBase {

Spindexer spindexer;

  /** Creates a new StoreBalls. */
  public StoreBalls(Spindexer spindexer) {
    this.spindexer = spindexer;
    addRequirements(spindexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Combines the IntakeRotation and RunAntiJam commands to sort and integrate balls into the spindexer
    spindexer.Rotate(Constants.Spindexer.intakeSpindexerSpeed);
    spindexer.RunOuttakeMotor(Constants.Spindexer.AntiJamOutTakeSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
