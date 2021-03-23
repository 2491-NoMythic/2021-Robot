package com.frc2491.clank.commands.spindexer;

import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.AntiJam;
import com.frc2491.clank.subsystems.Spindexer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StoreBalls extends CommandBase {

private Spindexer spindexer;
private AntiJam antiJam;

  /**
   * Creates a new StoreBalls.
   * Runs spindexer at intake speed.
   * Runs antiJam at intake speed.
   */
  public StoreBalls(Spindexer spindexer, AntiJam antiJam) {
    this.spindexer = spindexer;
    this.antiJam = antiJam;
    addRequirements(spindexer, antiJam);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Combines the IntakeRotation and RunAntiJam commands to sort and integrate balls into the spindexer
    spindexer.rotate(Constants.Spindexer.intakeSpindexerSpeed);
    antiJam.runAntiJam(Constants.Spindexer.antiJamIntakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spindexer.rotate(0);
    antiJam.runAntiJam(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
