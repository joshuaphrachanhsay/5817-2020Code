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

    private Conveyor conveyor = Conveyor.getInstance();
    private Controller controller = Controller.getInstance();

    int tailPosition = 2;
    int cellCounter = 0;

    boolean ballDetect = false;
    boolean ballDetectShot = false;
    DigitalInput PhotoSensorOne;
   // DigitalInput PhotoSensorTwo;

    public void photoSensorLimit() {
      
        PhotoSensorOne = new DigitalInput(0);
       // PhotoSensorTwo = new DigitalInput(1);
    }

 public void PhotosensorCount(){
 
  
 /* if (PhotoSensorOne.get() == true){
      ballDetect = true;
    }

  if (controller.getRightTriggerDriver() > 0.2){
    ballDetect = false;

  } */

  /*if (controller.getLeftTriggerCoDriver() > 0.2){
     if (PhotoSensorOne.get() == true) {
      conveyor.shooterConveyorControl(0);
    } else {
      conveyor.shooterConveyorControl(0.6);

    }

  } else {
    conveyor.shooterConveyorControl(0);
  } */

 
        if (PhotoSensorOne.get()) {
            if (ballDetect) {
        
            } else {
              cellCounter++;
              ballDetect = true;
            }

          } else if (PhotoSensorOne.get() == false) {
            ballDetect = false;
          }






          //Tracks the intake of balls
          
        /*
          if (PhotoSensorTwo.get()) {
            if (ballDetectShot) {
        
            } else {
              cellCounter--;
              ballDetect = true;
            }
          }
        
          if (PhotoSensorTwo.get() == false) {
            ballDetectShot = false;
          }
          //Tracks the # of balls shot
        */
          PhotoSensorOne.close();
          //PhotoSensorTwo.close();
        } 
    }
  
  

