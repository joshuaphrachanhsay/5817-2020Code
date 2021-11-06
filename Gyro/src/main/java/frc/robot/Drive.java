/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Drive {

    private static Drive instance_;
	
	public static Drive getInstance() {
		if(instance_ == null) {
			instance_ = new Drive();
		}
		return instance_;

}
public TalonSRX rightDriveOne;
public TalonSRX rightDriveTwo;
public TalonSRX leftDriveOne; 
public TalonSRX leftDriveTwo;


private Drive(){
    rightDriveOne = new TalonSRX(2);
    rightDriveTwo = new TalonSRX(3);
    leftDriveOne = new TalonSRX(0);
    leftDriveTwo = new TalonSRX(1); //Change this

}

public void rightDrive (double value){
    rightDriveOne.set(ControlMode.PercentOutput, value);
	rightDriveTwo.set(ControlMode.PercentOutput, value);
}

public void leftDrive (double value ){
    leftDriveOne.set(ControlMode.PercentOutput, value);
	leftDriveTwo.set(ControlMode.PercentOutput, value);
}


}