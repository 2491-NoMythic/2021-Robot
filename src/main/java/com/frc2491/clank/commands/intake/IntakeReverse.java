package com.frc2491.clank.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Intake;

/**
 * Creates the intake command.
 * This runs the intake at the constant intake autoIntakeSpeed
 */
public class IntakeReverse extends CommandBase {

  private Intake intake;

  public IntakeReverse(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.runIntakeMotor(Constants.Intake.intakeReverseSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.runIntakeMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
