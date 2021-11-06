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

    TalonSRX leftDrive1;
    TalonSRX leftDrive2;
    TalonSRX rightDrive1;
    TalonSRX rightDrive2;

    public Drive() {
        leftDrive1 = new TalonSRX(0);
        leftDrive2 = new TalonSRX(1);
        rightDrive1 = new TalonSRX(14);
        rightDrive2 = new TalonSRX(15);
    }

    public void RightDrive(double input){
        rightDrive2.follow(rightDrive1);
        //rightDrive2.set(ControlMode.PercentOutput, input);
        rightDrive1.set(ControlMode.PercentOutput, input);
    }
    
    public void LeftDrive (double input) {
        leftDrive2.follow(leftDrive1);
        leftDrive1.set (ControlMode.PercentOutput, -input);
        //leftDrive2.set(ControlMode.PercentOutput, input);
    }

     
}
