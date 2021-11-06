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
public class Conveyor {
    private static Conveyor instance_; 
    
    public static Conveyor getInstance() {
        if(instance_ == null) {
            instance_ = new Conveyor();
        }
        return instance_;
    }
 
    TalonSRX shooterConveyor1;
    TalonSRX shooterConveyor2;

    public Conveyor() {
        shooterConveyor1 = new TalonSRX(8);
        shooterConveyor2 = new TalonSRX(9);
    }

    public void shooterConveyorControl(double value) {
        shooterConveyor1.set(ControlMode.PercentOutput, value);
        shooterConveyor2.set(ControlMode.PercentOutput, -value - 0.1);
    }

}
