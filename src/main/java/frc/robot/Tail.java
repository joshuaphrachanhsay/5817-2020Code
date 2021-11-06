/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
/**
 * Add your docs here.
 */
public class Tail {
    private static Tail instance_; 
    
    public static Tail getInstance() {
        if(instance_ == null) {
            instance_ = new Tail();
        }
        return instance_;
    }

TalonSRX tailone;
//TalonSRX tailtwo;

public Tail(){
    //change the talon innput
tailone = new TalonSRX(11);
//tailtwo = new TalonSRX(1);

}public void intake(double value){
tailone.set(ControlMode.PercentOutput, value);

}

}




