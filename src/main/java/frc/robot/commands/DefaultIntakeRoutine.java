/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Variables;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class DefaultIntakeRoutine extends CommandBase {

  Indexer m_Indexer;
  indexState currentState;

  private enum indexState
  {
    newBall, inTransit, fullState;
  }

  public DefaultIntakeRoutine(Indexer index) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Indexer = index;
    addRequirements(index);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    currentState = indexState.newBall;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(currentState){
      case newBall:
        m_Indexer.runIndexMotor(0);
        m_Indexer.runFunnelMotorLeft(Constants.Indexer.leftFunnelSpeed);
        m_Indexer.runFunnelMotorRight(Constants.Indexer.rightFunnelSpeed);
        if(m_Indexer.getSensorBallEnter()){
          currentState = indexState.inTransit;
        }
        break;
      case inTransit:
        m_Indexer.runFunnelMotorLeft(0);
        m_Indexer.runFunnelMotorRight(0);
        m_Indexer.runIndexMotor(1);
        if(m_Indexer.getSensorBallLeave()){
          currentState = indexState.fullState;
        } else if (m_Indexer.getSensorPositionOne()){
          currentState = indexState.newBall;
        }
      case fullState:
        m_Indexer.runFunnelMotorLeft(0);
        m_Indexer.runFunnelMotorRight(0);
        m_Indexer.runIndexMotor(0);
        if(!m_Indexer.getSensorBallLeave()){
          currentState = indexState.newBall;
        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
