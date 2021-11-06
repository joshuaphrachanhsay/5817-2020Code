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
  private Shooter shooter = Shooter.getInstance();
  private Controller controller = Controller.getInstance();
  private Conveyor conveyor = Conveyor.getInstance();
  private Tower tower = Tower.getInstance();
  private double controllerJoystickDeadzone = 0.2;
  private Drive drive = Drive.getInstance();
  private LimeLight limelight = LimeLight.getInstance();
  private Reposition reposition = Reposition.getInstance();
  private PhotoSensor photosensor = PhotoSensor.getInstance();
  private ColorWheel colorWheel = ColorWheel.getInstance();
  private ColorSensor colorSensor = ColorSensor.getInstance();
  private Tail tail = Tail.getInstance();
  private Position position = Position.LOW;
  private State state = State.DRIVE;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
 
    //shooter.motionMagic();
    SmartDashboard.putNumber("YLeft", controller.getYLeftDriver());
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
    SmartDashboard.putNumber("BallCount", photosensor.cellCounter);
    SmartDashboard.putNumber("Shooter Angle Position", shooter.getShooterAnglePosition());
    SmartDashboard.putNumber("YLeft", controller.getYLeftDriver());
    SmartDashboard.putNumber("XLeft", controller.getXLeftDriver());
    SmartDashboard.putNumber("YRight", controller.getYRightDriver());
    SmartDashboard.putNumber("XRight", controller.getXRightDriver());
    colorSensor.senseColor();
    //photosensor.PhotosensorCount();
  }
 
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
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
        // Put default auto code here
        //shooter.shooterControl(0.25);
 
        break;
 
    }
  }
 
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    
    
    if(controller.getRightTriggerDriver() > 0.1){
      shooter.shooterControl(0.35);
    } else {
      shooter.shooterControl(0);
    }

    if(controller.getButtonYCoDriver()){
      while(controller.getButtonYCoDriver() == true) {
        shooter.angle(0.2);
      }
    } else if (controller.getButtonACoDriver()) {
        while(controller.getButtonACoDriver() == true) {
          shooter.angle(-0.1);
        }
    } else {
      shooter.angle(0);
    }


    if (controller.getButtonYDriver()){
      position = Position.HIGH;
    } else if (controller.getButtonADriver()){
      position = Position.LOW;
    } else if(controller.getLeftTriggerDriver() < 0.9) {
      position = Position.MANUAL;
    }else{

    }

    if(controller.getBackButtonDriver()){
      shooter.zero();
    }
    
    switch(position){
      case HIGH:
      shooter.setShooterAnglePostion(668);
      break;
  
      case LOW:
      shooter.setShooterAnglePostion(0);
      break;

      case MANUAL:
      shooter.angle(controller.getYRightDriver());
    }

    switch(state){
      case DRIVE:
      if (controller.getYLeftDriver() > controllerJoystickDeadzone){
        drive.RightDrive(controller.getYLeftDriver() - (controller.getXRightDriver()*0.5));
        drive.LeftDrive(controller.getYLeftDriver() + (controller.getXRightDriver()*0.5));
      }else if(controller.getYLeftDriver() < controllerJoystickDeadzone * (-1) ){
        drive.RightDrive(controller.getYLeftDriver() - (controller.getXRightDriver()*0.5));
        drive.LeftDrive(controller.getYLeftDriver() + (controller.getXRightDriver()*0.5));
      }else if(controller.getXRightDriver() < controllerJoystickDeadzone *(-1) || controller.getXRightDriver() > controllerJoystickDeadzone){
        drive.RightDrive(controller.getXRightDriver()*(-1));
        drive.LeftDrive(controller.getXRightDriver());
       } else if(controller.getLeftBumperDriver()){
          limelight.setLEDMode(LedMode.kforceOn);
          reposition.LimeLightReposition();
      } else {
        limelight.setLEDMode(LedMode.kforceOff);
        drive.LeftDrive(0);
        drive.RightDrive(0);
      } 
      break;

      case AIM:
      if (controller.getYLeftDriver() > controllerJoystickDeadzone){
        drive.RightDrive((controller.getYLeftDriver() - (controller.getXRightDriver()*0.5))*0.5);
        drive.LeftDrive((controller.getYLeftDriver() + (controller.getXRightDriver()*0.5))*0.5);
      }else if(controller.getYLeftDriver() < controllerJoystickDeadzone * (-1) ){
        drive.RightDrive((controller.getYLeftDriver() - (controller.getXRightDriver()*0.5))*0.5);
        drive.LeftDrive((controller.getYLeftDriver() + (controller.getXRightDriver()*0.5))*0.5);
      }else if(controller.getXRightDriver() < controllerJoystickDeadzone *(-1) || controller.getXRightDriver() > controllerJoystickDeadzone){
        drive.RightDrive((controller.getXRightDriver()*(-1))*0.25);
        drive.LeftDrive((controller.getXRightDriver())*0.25);
       } else if(controller.getLeftBumperDriver()){
          limelight.setLEDMode(LedMode.kforceOn);
          reposition.LimeLightReposition();
      } else {
        limelight.setLEDMode(LedMode.kforceOff);
        drive.LeftDrive(0);
        drive.RightDrive(0);
      } 
      break;


    }
    /*if (controller.getLeftTriggerDriver() > 0.1){
      conveyor.conveyorControl(0.75);
    } else {
        conveyor.conveyorControl(0);
    }
    if (controller.getButtonADriver()){
      tower.TowerBeltControl(.25);
    }*/

    if(controller.getLeftTriggerDriver() < 0.9){
      state = State.DRIVE;
    }else{
      state = State.AIM;
    }

    


    if (controller.getLeftTriggerCoDriver() > 0.1) {
      tail.intake(0.75);
      tower.TowerBeltControl(0.8);
      conveyor.shooterConveyorControl(0.6);
    } else if (controller.getRightTriggerCoDriver() > 0.1) {
      tail.intake(-0.75);
      tower.TowerBeltControl(-0.8);
      conveyor.shooterConveyorControl(-0.6);
    } else {
      tail.intake(0);
      tower.TowerBeltControl(0);
      conveyor.shooterConveyorControl(0);
    }

    while (controller.getRightBumperCoDriver()) {
      colorWheel.wheelSpinnerControl(0.1);
    }

    if (controller.getButtonACoDriver()) {
      while (colorSensor.colorString != "Yellow") {
        colorSensor.senseColor();
        colorWheel.wheelSpinnerControl(0.1);

      } 
    } else if (controller.getButtonBCoDriver()) {
        while (colorSensor.colorString != "Blue") {
        colorSensor.senseColor();
        colorWheel.wheelSpinnerControl(0.1);
  
      }
    } else if (controller.getButtonXCoDriver()) {
        while (colorSensor.colorString != "Red") {
        colorSensor.senseColor();
        colorWheel.wheelSpinnerControl(0.1);

      }
    } else if (controller.getButtonYCoDriver()) {
        while (colorSensor.colorString != "Green") {
        colorSensor.senseColor();
        colorWheel.wheelSpinnerControl(0.1);

      } 
    } else {
        colorWheel.wheelSpinnerControl(0);
    }

    if(photosensor.cellCounter >= 5) {
      
      //tail.TailControl(0)
  

    } 

/*
     if (controller.getYLeftDriver() > controllerJoystickDeadzone){
      //drive.RightDrive(.75);
      drive.LeftDrive(.75);
    }else if(controller.getYLeftDriver() < controllerJoystickDeadzone * (-1) ){
      //drive.RightDrive(-0.75);
      drive.LeftDrive(-0.75);
    } else {
      drive.LeftDrive(0);
     // drive.RightDrive(0);
    } 

*/
    
  } 

  
 
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
 


