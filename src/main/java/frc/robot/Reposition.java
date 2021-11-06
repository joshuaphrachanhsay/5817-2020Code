
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot;
 
/**
 * Add your docs here.
 */
public class Reposition {
    private static Reposition instance_;
 
    public static Reposition getInstance() {
        if (instance_ == null) {
            instance_ = new Reposition();
        }
        return instance_;
    }
 
    private LimeLight limelight = LimeLight.getInstance();
    private Drive drive = Drive.getInstance();
 
 
    double tx = limelight.getdegRotationToTarget();
    double ty = limelight.getdegVerticalToTarget();

 
    public void LimeLightReposition() {
      
        if (limelight.getdegRotationToTarget() < -1.5){
          drive.LeftDrive(-.15);
          drive.RightDrive(-.15);

        }else if(limelight.getdegRotationToTarget() > 1.5){
          drive.LeftDrive(.15);
          drive.RightDrive(.15);
        } else if (limelight.getdegRotationToTarget() > -1.5 && limelight.getdegRotationToTarget() < 1.5) {
          drive.LeftDrive(0);
          drive.RightDrive(0);
      
      }
      }
    }
        
   




    


    

