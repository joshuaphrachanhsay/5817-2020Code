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
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
 
/**
 * Add your docs here.
 */
public class Shooter {
 
    private static Shooter instance_;
    
    public static Shooter getInstance() {
        if(instance_ == null) {
            instance_ = new Shooter();
        }
        return instance_;
    }
 
    TalonFX leftShooter;
    TalonFX rightShooter;
    TalonSRX shooterAngle;
    int velocity=0;
 
    private int kTimeoutMs = 10;
 
    public Shooter(){
        leftShooter = new TalonFX(2);
        rightShooter = new TalonFX(3);
        shooterAngle = new TalonSRX(13);
    }
        public static final double kShooterKp = 1.0;
        public static final double kShooterKi = 0.0;
        public static final double kShooterKd = 2.0;
        public static final double kShooterKf = .15345;
        
      
 
   
    public void motionMagic(){
        int kTimeoutMs=10;
        shooterAngle.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
        shooterAngle.setSensorPhase(true);
        shooterAngle.setInverted(true);

		/* Set relevant frame periods to be at least as fast as periodic rate */
        shooterAngle.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
        shooterAngle.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* set the peak and nominal outputs */
        shooterAngle.configNominalOutputForward(0, kTimeoutMs);
        shooterAngle.configNominalOutputReverse(0, kTimeoutMs);
        shooterAngle.configPeakOutputForward(1, kTimeoutMs);
        shooterAngle.configPeakOutputReverse(-1, kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
        shooterAngle.selectProfileSlot(0, 0);
        shooterAngle.config_kF(0, 0.2481, kTimeoutMs);//0.2481
        shooterAngle.config_kP(0, 8, kTimeoutMs);//1.0
        shooterAngle.config_kI(0, 0, kTimeoutMs);//0.0001
        shooterAngle.config_kD(0, 0, kTimeoutMs);//1
		/* set acceleration and cruise velocity - see documentation */
        shooterAngle.configMotionCruiseVelocity(10000, kTimeoutMs);
        shooterAngle.configMotionAcceleration(10000, kTimeoutMs);
		/* zero the sensor */
        shooterAngle.setSelectedSensorPosition(0, 0, kTimeoutMs);
}
public void shooterControl(double value) {
    leftShooter.set(ControlMode.PercentOutput, -value);
    rightShooter.set(ControlMode.PercentOutput, value);
}

public void angle(double value) {
    shooterAngle.set(ControlMode.PercentOutput, -value);
}

public void setShooterAnglePostion(int value){
    shooterAngle.set(ControlMode.MotionMagic, value);
    }

public int getShooterAnglePosition(){
    return shooterAngle.getSelectedSensorPosition(0);
}

public void zero(){

    shooterAngle.setSelectedSensorPosition(0, 0, 10);
}

}

 // public void motionMagic(){
        /* first choose the sensor */
        //rightShooter.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, kTimeoutMs);
 
        /* Set relevant frame periods to be at least as fast as periodic rate */
        //rightShooter.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
        //rightShooter.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
 
        /* set the peak and nominal outputs */
        //rightShooter.configNominalOutputForward(0, kTimeoutMs);
        //rightShooter.configNominalOutputReverse(0, kTimeoutMs);
        //rightShooter.configPeakOutputForward(1, kTimeoutMs);
        //rightShooter.configPeakOutputReverse(-1, kTimeoutMs);
 
        /* set closed loop gains in slot0 - see documentation */
        //rightShooter.selectProfileSlot(0, 0);
        //rightShooter.config_kF(0, 0.5, kTimeoutMs);
        //rightShooter.config_kP(0, 0.01, kTimeoutMs);
        //rightShooter.config_kI(0, 0.0, kTimeoutMs);
        //rightShooter.config_kD(0, 0.0, kTimeoutMs);
        /* set acceleration and vcruise velocity - see documentation */
        //rightShooter.configMotionCruiseVelocity(2500, kTimeoutMs);
        //rightShooter.configMotionAcceleration(2500, kTimeoutMs);
        /* zero the sensor */
        //rightShooter.setSelectedSensorPosition(0, 0, kTimeoutMs);
        
        
 
        /* first choose the sensor */
        //leftShooter.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, kTimeoutMs);
        //leftShooter.setSensorPhase(true);
 
        /* Set relevant frame periods to be at least as fast as periodic rate */
        //leftShooter.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
        //leftShooter.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
 
        /* set the peak and nominal outputs */
        //leftShooter.configNominalOutputForward(0, kTimeoutMs);
        //leftShooter.configNominalOutputReverse(0, kTimeoutMs);
        //leftShooter.configPeakOutputForward(1, kTimeoutMs);
        //leftShooter.configPeakOutputReverse(-1, kTimeoutMs);
 
        /* set closed loop gains in slot0 - see documentation */
        //leftShooter.selectProfileSlot(0, 0);
        //leftShooter.config_kF(0, 0.5, kTimeoutMs);
        //leftShooter.config_kP(0, 0.01, kTimeoutMs);
        //leftShooter.config_kI(0, 0.0, kTimeoutMs);
        //leftShooter.config_kD(0, 0, kTimeoutMs);
        /* set acceleration and vcruise velocity - see documentation */
        //leftShooter.configMotionCruiseVelocity(2500, kTimeoutMs);
        //leftShooter.configMotionAcceleration(2500, kTimeoutMs);
        /* zero the sensor */
        //leftShooter.setSelectedSensorPosition(0, 0, kTimeoutMs);
    //}

/*  public void setRightSidePosition(int value){
        rightShooter.set(ControlMode.MotionMagic, value);
    }
    
    public void setLeftSidePosition(int value){
        leftShooter.set(ControlMode.MotionMagic, value);
    }
 
    public int getRightShooterVelocity(){
        return rightShooter.getSelectedSensorVelocity(0);
    }
    
    public int getLeftShooterVelocity(){
        return leftShooter.getSelectedSensorVelocity(0);
    }
 
    public int getRightShooterPosition(){
        return rightShooter.getSelectedSensorPosition(0);
    }
    
    public int getLeftShooterPosition(){
        return leftShooter.getSelectedSensorPosition(0);
    }

    public int maxVelocity(){
		if (leftShooter.getSelectedSensorVelocity()> velocity){
			velocity= leftShooter.getSelectedSensorVelocity();
		}else{
			velocity= velocity;
		}
		return velocity;

    }
*/