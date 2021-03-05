package com.frc2491.clank.HID;

/**
 * This contains the current HIDs that are set up for the driver station. 
 * They start with two defaults. Those can be changed here in the constructor.
 * By having this a singleton, it can be accessed in subsystems/commands and always
 * return the correct controls.
 */
public class CurrentHIDs {

	private static CurrentHIDs mInstance = null;

	// Creates a new control board
	public static CurrentHIDs getInstance() {
		if (mInstance == null) {
			mInstance = new CurrentHIDs();
		}
		return mInstance;
	}

	// Creates controller interfaces
	private IDriveController mDriveController;
	private IOperatorController mOperatorController;

	private CurrentHIDs() {
		mDriveController = new TM();
		mOperatorController = new PS4();
	}

	public IDriveController getDriveController() {
		return mDriveController;
	}

	public void setDriveController(IDriveController driveController) {
		this.mDriveController = driveController;
	}

	public IOperatorController getOperatorController() {
		return mOperatorController;
	}

	public void setOperatorController(IOperatorController operatorController) {
		this.mOperatorController = operatorController;
	}

}