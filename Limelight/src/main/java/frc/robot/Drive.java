 
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot;
 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
 
 
public class Drive {
    
    private static Drive instance_;
    
    public static Drive getInstance() {
        if(instance_ == null) {
            instance_ = new Drive();
        }
        return instance_;
    }
    
    TalonSRX rightDriveOne;
    TalonSRX rightDriveTwo;
    TalonSRX leftDriveOne;
    TalonSRX leftDriveTwo;
   
    public int kTimeoutMs = 10;
    
    private Drive(){
        rightDriveOne = new TalonSRX(10);
        rightDriveTwo = new TalonSRX(7);
        leftDriveOne = new TalonSRX(3);
        leftDriveTwo = new TalonSRX(2);
 
    }
    
    public void motionMagic(){
        /* first choose the sensor */
        rightDriveOne.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
 
        /* Set relevant frame periods to be at least as fast as periodic rate */
        rightDriveOne.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
        rightDriveOne.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
 
        /* set the peak and nominal outputs */
        rightDriveOne.configNominalOutputForward(0, kTimeoutMs);
        rightDriveOne.configNominalOutputReverse(0, kTimeoutMs);
        rightDriveOne.configPeakOutputForward(1, kTimeoutMs);
        rightDriveOne.configPeakOutputReverse(-1, kTimeoutMs);
 
        /* set closed loop gains in slot0 - see documentation */
        rightDriveOne.selectProfileSlot(0, 0);
        rightDriveOne.config_kF(0, 0, kTimeoutMs);
        rightDriveOne.config_kP(0, 1.69, kTimeoutMs);
        rightDriveOne.config_kI(0, .17, kTimeoutMs);
        rightDriveOne.config_kD(0, 0.92, kTimeoutMs);
        /* set acceleration and vcruise velocity - see documentation */
        rightDriveOne.configMotionCruiseVelocity(2500, kTimeoutMs);
        rightDriveOne.configMotionAcceleration(2500, kTimeoutMs);
        /* zero the sensor */
        rightDriveOne.setSelectedSensorPosition(0, 0, kTimeoutMs);
        
        
 
        /* first choose the sensor */
        leftDriveOne.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
        leftDriveOne.setSensorPhase(true);
 
        /* Set relevant frame periods to be at least as fast as periodic rate */
        leftDriveOne.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
        leftDriveOne.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
 
        /* set the peak and nominal outputs */
        leftDriveOne.configNominalOutputForward(0, kTimeoutMs);
        leftDriveOne.configNominalOutputReverse(0, kTimeoutMs);
        leftDriveOne.configPeakOutputForward(1, kTimeoutMs);
        leftDriveOne.configPeakOutputReverse(-1, kTimeoutMs);
 
        /* set closed loop gains in slot0 - see documentation */
        leftDriveOne.selectProfileSlot(0, 0);
        leftDriveOne.config_kF(0, 0, kTimeoutMs);
        leftDriveOne.config_kP(0, 1.69, kTimeoutMs);
        leftDriveOne.config_kI(0, .17, kTimeoutMs);
        leftDriveOne.config_kD(0, 0.92, kTimeoutMs);
        /* set acceleration and vcruise velocity - see documentation */
        leftDriveOne.configMotionCruiseVelocity(2500, kTimeoutMs);
        leftDriveOne.configMotionAcceleration(2500, kTimeoutMs);
        /* zero the sensor */
        leftDriveOne.setSelectedSensorPosition(0, 0, kTimeoutMs);
            
            }
 
    public void RightDrive(double value){
        rightDriveTwo.follow(rightDriveOne);
        rightDriveOne.set(ControlMode.PercentOutput, value);
    }
    
    public void LeftDrive (double value) {
        leftDriveTwo.follow(leftDriveOne);
        leftDriveOne.set (ControlMode.PercentOutput, value);
 
    }
 
    
 
}
 
