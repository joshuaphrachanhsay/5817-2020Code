/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;
import java.util.List;

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

    List<TalonSRX> motors;

    public Drive() {
        leftDrive1 = new TalonSRX(0);
        leftDrive2 = new TalonSRX(1);
        rightDrive1 = new TalonSRX(14);
        rightDrive2 = new TalonSRX(15);
        
        rightDrive2.follow(rightDrive1);
        leftDrive2.follow(leftDrive1);

        //leftDrive1.configVoltageCompSaturation(12.0, 10);
        //leftDrive1.enableVoltageCompensation(true);

        //configureTalon(leftDrive1, true, true);
        //motors = Arrays.asList(leftDrive1, leftDrive2, rightDrive1, rightDrive2);
        /*
        for (TalonSRX m : motors) {
            m.configFactoryDefault();
        }
        */
    }
/*
    private void configureTalon(TalonSRX talonSRX, boolean left, boolean master) {
        talonSRX.setInverted(!left);
        talonSRX.enableVoltageCompensation(true);
    }
*/

    public void RightDrive(double input){
        rightDrive2.set(ControlMode.PercentOutput, input * 0.8);
        rightDrive1.set(ControlMode.PercentOutput, input * 0.8);
    }
    
    public void LeftDrive (double input) {
        leftDrive1.set (ControlMode.PercentOutput, -input * 0.8);
        leftDrive2.set(ControlMode.PercentOutput, -input * 0.8);
    }

     
}
