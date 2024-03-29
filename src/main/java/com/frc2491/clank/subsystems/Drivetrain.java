package com.frc2491.clank.subsystems;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.Settings.Variables;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {
	private WPI_TalonFX driveLeftMotor1,driveLeftMotor2,driveRightMotor1,driveRightMotor2;
	private AHRS gyro;
	private DifferentialDriveOdometry odometry;

	public Drivetrain() {

		//creating motors
		driveLeftMotor1 = new WPI_TalonFX(Constants.Drivetrain.driveTalonLeftMotor1);
		driveLeftMotor2 = new WPI_TalonFX(Constants.Drivetrain.driveTalonLeftMotor2);
		driveRightMotor1 = new WPI_TalonFX(Constants.Drivetrain.driveTalonRightMotor1);
		driveRightMotor2 = new WPI_TalonFX(Constants.Drivetrain.driveTalonRightMotor2);

		driveRightMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
		driveLeftMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

		//Setting Followers
		driveLeftMotor2.follow(driveLeftMotor1);
		driveRightMotor2.follow(driveRightMotor1);

		//making right motors go right
		driveRightMotor1.setInverted(true);
		driveRightMotor2.setInverted(true);
		driveLeftMotor1.setInverted(false);
		driveLeftMotor2.setInverted(false);

		gyro = new AHRS(Port.kUSB);

		resetGyro();

		resetEncoders();
		odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(gyro.getAngle()));

		SmartDashboard.putNumber("Rotate P", Variables.Drivetrain.RotationCommand.kP);
		SmartDashboard.putNumber("Rotate I", Variables.Drivetrain.RotationCommand.kI);
		SmartDashboard.putNumber("Rotate D", Variables.Drivetrain.RotationCommand.kD);
	}

	//gyro tingz
	public void resetGyro(){
		gyro.reset();
	}

	public double getGyroAngle(){
		return(getRawGyroAngle() % 360 + 360) % 360;
	}

	public double getRawGyroAngle() {
		return gyro.getAngle();
	}

	public TalonFX[] getTalonFX(){
		return new TalonFX[]{driveLeftMotor1,driveLeftMotor2,driveRightMotor1,driveRightMotor2};
	}

	//pose tingz
	public Pose2d getPose() {
		return odometry.getPoseMeters();
	}

	//Returns wheel speeds of the robot
	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(getLeftEncoderRate(), getRightEncoderRate());
	}

	//Resets odometry to the specified pose
	public void resetOdometry(Pose2d pose) {
		resetEncoders();
		odometry.resetPosition(pose, Rotation2d.fromDegrees(gyro.getAngle()));
	}

	//creating percent output for both right and left
	public void drivePercentOutput(double speed){
		drivePercentOutput(speed, speed);
	}

	public void drivePercentOutput(double leftSpeed, double rightSpeed){
		driveLeftPercentOutput(leftSpeed);
		driveRightPercentOutput(rightSpeed);
	}

	public void driveLeftPercentOutput(double speed){
		driveLeftMotor1.set(ControlMode.PercentOutput, speed);
	}

	public void driveRightPercentOutput(double speed){
		driveRightMotor1.set(ControlMode.PercentOutput, speed);
	}
	

	//createing voltge output for both right and left.	
	public void driveVoltageOutput (double speed) {
		driveVoltageOutput(speed, speed);
	}

	public void driveVoltageOutput (double leftSpeed, double rightSpeed) {
		driveLeftVoltageOutput(leftSpeed);
		driveRightVoltageOutput(rightSpeed);
	}

	public void driveLeftVoltageOutput (double speed) {
		driveLeftMotor1.setVoltage(speed);
	}

	public void driveRightVoltageOutput (double speed) {
		driveRightMotor1.setVoltage(speed);
	}

	//creating drive velocity for both right and left
	public void driveVelocity(double speed){
		driveVelocity(speed, speed);
	}

	public void driveVelocity(double leftSpeed, double rightSpeed){
		driveLeftVelocity(leftSpeed);
		driveRightVelocity(rightSpeed);
	}

	public void driveLeftVelocity(double speed){
		driveLeftMotor1.set(ControlMode.Velocity, speed);
	}

	public void driveRightVelocity(double speed){
		driveRightMotor1.set(ControlMode.Velocity, speed);
	}

	public double getRightDriveSpeed() {
		return driveRightMotor1.get();
	}

	public double getLeftDriveSpeed(){
		return driveLeftMotor1.get();
	}

	//robot can stop
	public void stop(){
		drivePercentOutput(0, 0);
	}

	public void breakModeOn(Boolean b){
		if(b){
			driveLeftMotor1.setNeutralMode(NeutralMode.Brake);
			driveRightMotor1.setNeutralMode(NeutralMode.Brake);
		} else {
			driveLeftMotor1.setNeutralMode(NeutralMode.Coast);
			driveRightMotor1.setNeutralMode(NeutralMode.Coast);
		}
	}

	//getting encoder distance and rate
	public double getRightEncoderDistance() {
		return driveRightMotor1.getSelectedSensorPosition(0) * Constants.Drivetrain.driveEncoderToInches;
	}

	public double getLeftEncoderDistance() {
		return driveLeftMotor1.getSelectedSensorPosition(0) * Constants.Drivetrain.driveEncoderToInches;
	}

	public double getLeftEncoderDistanceRaw() {
		return driveLeftMotor1.getSelectedSensorPosition(0);
	}

	public double getRightEncoderDistanceRaw() {
		return driveRightMotor1.getSelectedSensorPosition(0);
	}

	public double getEncoderDistance() {
		return ((getLeftEncoderDistance() + getRightEncoderDistance()) / 2);
	}

	public double getLeftEncoderRate() {
		return driveLeftMotor1.getSelectedSensorVelocity(0) * Constants.Drivetrain.driveEncoderVelocityToRPS;
	}

	public double getRightEncoderRate() {
		return driveRightMotor1.getSelectedSensorVelocity(0) * Constants.Drivetrain.driveEncoderVelocityToRPS;
	}

	public double getEncoderRate() {
		return ((getRightEncoderRate() + getLeftEncoderRate()) / 2);
	}

	public void resetEncoders() {
		driveRightMotor1.setSelectedSensorPosition(0);
		driveLeftMotor1.setSelectedSensorPosition(0);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Angle", getGyroAngle());
		if ((SmartDashboard.getNumber("Rotate P", 0) != Variables.Drivetrain.RotationCommand.kP)) {
			Variables.Drivetrain.RotationCommand.kP = SmartDashboard.getNumber("Rotate P", 0);
		}
		if ((SmartDashboard.getNumber("Rotate I", 0) != Variables.Drivetrain.RotationCommand.kI)) {
			Variables.Drivetrain.RotationCommand.kI = SmartDashboard.getNumber("Rotate I", 0);
		}
		if ((SmartDashboard.getNumber("Rotate D", 0) != Variables.Drivetrain.RotationCommand.kD)) {
			Variables.Drivetrain.RotationCommand.kD = SmartDashboard.getNumber("Rotate D", 0);
		}
		// This method will be called once per scheduler run

		//Update the odometry in the periodic block
		odometry.update(Rotation2d.fromDegrees(gyro.getAngle()), getLeftEncoderDistance(), getRightEncoderDistance()); //TODO Test encoder distance method, check gearing ratio between distanceand encoder
	}
}
