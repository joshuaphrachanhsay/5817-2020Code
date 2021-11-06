
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
    private Controller controller = Controller.getInstance();
    private Drive drive = Drive.getInstance();
 
 
    double tx = limelight.getdegRotationToTarget();
    double ty = limelight.getdegVerticalToTarget();

 
    public void LimeLightReposition() {
 
       // drive.LeftDrive(limelight.getdegRotationToTarget());
       // drive.RightDrive(limelight.getdegVerticalToTarget());


       /* if (limelight.getdegRotationToTarget() < -4){
            drive.LeftDrive(.25);
            drive.RightDrive(.25);

          }else if(limelight.getdegRotationToTarget() > 4){
            drive.LeftDrive(-.25);
            drive.RightDrive(-.25);

          }else if(limelight.getdegRotationToTarget() > -4 && limelight.getdegRotationToTarget() < 4){
            if (limelight.getdegRotationToTarget() < -2){
              drive.LeftDrive(.2);
              drive.RightDrive(.2);
  
            }else if(limelight.getdegRotationToTarget() > 2){
              drive.LeftDrive(-.2);
              drive.RightDrive(-.2);
            } else if (limelight.getdegRotationToTarget() > -2 && limelight.getdegRotationToTarget() < 2) {
              drive.LeftDrive(0);
              drive.RightDrive(0);
          
          }
        }  */

        if (limelight.getdegRotationToTarget() < -1.5){
          drive.LeftDrive(.15);
          drive.RightDrive(.15);

        }else if(limelight.getdegRotationToTarget() > 1.5){
          drive.LeftDrive(-.15);
          drive.RightDrive(-.15);
        } else if (limelight.getdegRotationToTarget() > -1.5 && limelight.getdegRotationToTarget() < 1.5) {
          drive.LeftDrive(0);
          drive.RightDrive(0);
      
      }
      }
    }
        
   




    


    

