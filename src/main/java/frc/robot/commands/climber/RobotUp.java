/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;
import frc.robot.ControlBoard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RobotUp extends CommandBase {

  private Drivetrain m_Drivetrain;
  private Climber m_Climber;
  private ControlBoard m_ControlBoard;
  /**
   * Creates a new RobotUp.
   */
  public RobotUp(Drivetrain drivetrain, Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (!m_Climber.shifterCheck()) {
      m_Climber.setShifterOn();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      double climbLeftSpeed, climbRightSpeed;

     climbLeftSpeed = m_ControlBoard.getLeftClimbAxis();
     climbRightSpeed = m_ControlBoard.getRightClimbAxis();

    m_Drivetrain.driveLeftPercentOutput(climbLeftSpeed); 
    m_Drivetrain.driveRightPercentOutput(climbRightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
