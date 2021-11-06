/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * Add your docs here.
 */
public class ColorSensor {

    private static ColorSensor instance_;
	
	public static ColorSensor getInstance() {
		if(instance_ == null) {
			instance_ = new ColorSensor();
		}
		return instance_;
    }

    I2C.Port i2cPort = I2C.Port.kOnboard;

    ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    ColorMatch m_colorMatcher = new ColorMatch();
    
    String colorString;

    Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    VictorSP getSwolChang = new VictorSP(1);

    public ColorSensor() {
        
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }
    

    public void senseColor() {
        Color detectedColor = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
            colorString = "Blue";
            getSwolChang.set(0.83);
        } else if (match.color == kRedTarget) {
            colorString = "Red";
            getSwolChang.set(0.61);
        } else if (match.color == kGreenTarget) {
            colorString = "Green";
            getSwolChang.set(0.75);
        } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
            getSwolChang.set(0.69);
        } else {
            colorString = "Unknown";
            getSwolChang.set(0.0);
        }



        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
    }


}
