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
public class ColorWheel {

    private static ColorWheel instance_;
	
	public static ColorWheel getInstance() {
		if(instance_ == null) {
			instance_ = new ColorWheel();
		}
		return instance_;
    }

    TalonSRX wheelSpinner;

    public ColorWheel() {
        wheelSpinner = new TalonSRX(7);
    }

    public void wheelSpinnerControl(double value) {
        wheelSpinner.set(ControlMode.PercentOutput, value);
    }
}
