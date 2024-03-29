// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc2491.clank.commands.drivetrain;

import java.util.List;

import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public class DriveTrajectory extends CommandBase {

    private Drivetrain drivetrain;

    public final static DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(Constants.Drivetrain.trackWidthMeters);

    /** Creates a new DriveTrajectory. */
    public DriveTrajectory(Drivetrain drivetrain) {
        drivetrain = this.drivetrain;
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        // Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint (
            new SimpleMotorFeedforward (
                Constants.Drivetrain.ksVolts,
                Constants.Drivetrain.kvVoltSecondsPerMeter,
                Constants.Drivetrain.kaVoltSecondsSquaredPerMeter),
            kDriveKinematics,
            10);

        // Create config for trajectory
        TrajectoryConfig config =
            new TrajectoryConfig(
                Constants.Autonomous.kMaxSpeedMetersPerSecond,
                Constants.Autonomous.kMaxAccelerationMetersPerSecondSquared)

                // Add kinematics to ensure max speed is actually obeyed
                .setKinematics(kDriveKinematics)
                // Apply volatge constraint
                .addConstraint(autoVoltageConstraint);

        // Create Trajectory
        Trajectory autoTrajectory = TrajectoryGenerator.generateTrajectory(
            //Define starting point
            new Pose2d(0,0, new Rotation2d(0)),
            //Pass through a list of defined points 
            List.of(
                new Translation2d(1,1),
                new Translation2d(2,-1)
            ),
            //Define Endpoint
            new Pose2d(3,0, new Rotation2d(0)),
            //Pass config
            config
        );

        RamseteCommand ramseteCommand = new RamseteCommand(
            autoTrajectory,
            drivetrain::getPose,
            new RamseteController(Constants.Autonomous.kRamseteB, Constants.Autonomous.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.Drivetrain.ksVolts,
                Constants.Drivetrain.kvVoltSecondsPerMeter,
                Constants.Drivetrain.kaVoltSecondsSquaredPerMeter),
            kDriveKinematics,
            drivetrain::getWheelSpeeds,
            new PIDController(Constants.Drivetrain.kPDriveVel, 0, 0),
            new PIDController(Constants.Drivetrain.kPDriveVel, 0, 0),
            //RamseteCommand passes volts to the callback
            drivetrain::driveVoltageOutput,
            drivetrain
        );

        //Reset odometry to the starting pose of the trajectory
        drivetrain.resetOdometry(autoTrajectory.getInitialPose());
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
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
