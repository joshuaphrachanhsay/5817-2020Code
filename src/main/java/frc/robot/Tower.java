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
public class Tower {
    private static Tower instance_; 
    
    public static Tower getInstance() {
        if(instance_ == null) {
            instance_ = new Tower();
        }
        return instance_;
    }
 
    TalonSRX towerBelt;
    TalonSRX towerBelt1;


    public Tower() {
        towerBelt = new TalonSRX(10);
        towerBelt1 = new TalonSRX(5);

    }

    public void TowerBeltControl(double value){

        towerBelt.set(ControlMode.PercentOutput, value);
        towerBelt1.set(ControlMode.PercentOutput, -value);

    }
}

   
