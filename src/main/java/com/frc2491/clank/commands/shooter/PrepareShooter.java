package com.frc2491.clank.commands.shooter;

import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.Settings.Variables;
import com.frc2491.clank.subsystems.Hood;
import com.frc2491.clank.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PrepareShooter extends CommandBase {

private Shooter shooter;
private Hood hood;

  /**
   * Creates a new PrepareShooter.
   * Sets the hood position and shooter velocity from variables
   */
  public PrepareShooter(Shooter shooter, Hood hood) {
    this.shooter = shooter;
    this.hood = hood;
    addRequirements(this.shooter, this.hood);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Combines the actions in FlywheelRev and SetHoodPosition into one command for joint execution
    hood.setHoodPosition(Variables.Shooter.shooterHoodPosition.getAngle());
		shooter.runLeftShooterVelocity(Variables.Shooter.shooterSpeed.getSpeed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.setHoodPosition(Constants.ShooterHoodPositions.collapsed.getAngle());
    shooter.runLeftShooterVelocity(Constants.ShooterSpeeds.stop.getSpeed());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
