/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class PhotoSensor {

    private static PhotoSensor instance_;
	
	public static PhotoSensor getInstance() {
		if(instance_ == null) {
			instance_ = new PhotoSensor();
		}
		return instance_;
    }



    int tailPosition = 2;
    int cellCounter = 0;

    boolean ballDetect = false;
    boolean ballDetectShot = false;
    DigitalInput limitSwitchOne;
    DigitalInput limitSwitchTwo;

    public void photoSensorLimit() {
      
        limitSwitchOne = new DigitalInput(0);
        limitSwitchTwo = new DigitalInput(1);
    }

 public void PhotosensorCount(){

        if (limitSwitchOne.get()) {
            if (ballDetect) {
        
            } else {
              cellCounter++;
              ballDetect = true;
            }

          } else if (limitSwitchOne.get() == false) {
            ballDetect = false;
          }

          //Tracks the intake of balls
          
        
          if (limitSwitchTwo.get()) {
            if (ballDetectShot) {
        
            } else {
              cellCounter--;
              ballDetect = true;
            }
          }
        
          if (limitSwitchTwo.get() == false) {
            ballDetectShot = false;
          }
          //Tracks the # of balls shot
        
          limitSwitchOne.close();
          limitSwitchTwo.close();
        }
    }

