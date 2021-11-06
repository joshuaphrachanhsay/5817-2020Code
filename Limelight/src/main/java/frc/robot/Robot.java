/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ControlMode.LedMode;
import  edu.wpi.first.cameraserver.CameraServer;

 
 
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private LimeLight limelight = LimeLight.getInstance();
 private Drive drive = Drive.getInstance();
 private Controller controller = Controller.getInstance();
 private Reposition reposition = Reposition.getInstance();
 private double controllerJoystickDeadzone = 0.2;
 private double controllerTriggerDeadzone = 0.05;
 
 
 /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    CameraServer.getInstance().startAutomaticCapture();
    drive.motionMagic();

  }
 
  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
    SmartDashboard.putNumber("LimeLight X", limelight.getdegRotationToTarget());
    SmartDashboard.putNumber("LimeLight Y", limelight.getdegVerticalToTarget());
    SmartDashboard.putNumber("LimeLight Area", limelight.getTargetArea());
    SmartDashboard.putNumber("LimeLight Skew Rotation", limelight.getSkew_Rotation());
    SmartDashboard.putBoolean("LimeLight TargetFound", limelight.getIsTargetFound());
 
 
 
  }
 
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }
 
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
   // Drive for 2 seconds
      
   /* drive.RightLimeLightSteer(0.5);
   drive.LeftLimeLightSteer(-0.5); */
 
        break;
 
    
    
    
      }
  }
 
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
 
        
        
 
    if (controller.getYLeftDriver() > controllerJoystickDeadzone){
      drive.RightDrive(controller.getYLeftDriver() + (controller.getXRightDriver()*0.3));
      drive.LeftDrive(controller.getYLeftDriver() - (controller.getXRightDriver()*0.3));
    }else if(controller.getYLeftDriver() < controllerJoystickDeadzone * (-1) ){
      drive.RightDrive(controller.getYLeftDriver() + (controller.getXRightDriver()*0.3));
      drive.LeftDrive(controller.getYLeftDriver() - (controller.getXRightDriver()*0.3));
    }else if(controller.getXRightDriver() < controllerJoystickDeadzone *(-1) || controller.getXRightDriver() > controllerJoystickDeadzone){
      drive.RightDrive(controller.getXRightDriver()*(-1));
      drive.LeftDrive(controller.getXRightDriver());
    }
        else if(controller.getLeftTriggerDriver() >= 0.2){
 
          limelight.setLEDMode(LedMode.kforceOn);
          reposition.LimeLightReposition();
 
        } else {
          limelight.setLEDMode(LedMode.kforceOff);
          drive.LeftDrive(0);
          drive.RightDrive(0);
 
        } 
      
 
  }
 
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
