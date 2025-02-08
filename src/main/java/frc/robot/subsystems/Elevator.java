// FRC 3770 2025 Elevator Subsystem
// Driven by one Neo Vortex with Spark Flex and one Neo Vortex with Spark Max
// Flex functions to lift and lower Elevator system, Max rotates an arm to knock algae off reef
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;

import frc.robot.Constants;


public class Elevator extends SubsystemBase {
    
    private SparkFlex elevatorMotor;
    private RelativeEncoder elevatorEncoder;

    private SparkFlex rotateMotor;

    double currentSetPoint;

    boolean isManualControl = false;

    public Elevator()
    {
        super();

        elevatorMotor = new SparkFlex(Constants.ELEVATOR_SPARK_FLEX_CAN_ID, SparkLowLevel.MotorType.kBrushless);   
        elevatorEncoder = elevatorMotor.getEncoder();
        rotateMotor = new SparkFlex(Constants.ALGAE_ARM_CAN_ID, SparkLowLevel.MotorType.kBrushless);

        // Initialize encoder to zero at start position.  Make this first set point.
        resetEncoder();
        currentSetPoint = 0.0;

    }

    public void spinForward()
    {
        elevatorMotor.set(1.0);
    }

    public void spinReverse()
    {
        elevatorMotor.set(-1.0);
    }

    public void stop()
    {
        elevatorMotor.set(0.0);
    }

    public void resetEncoder()
    {
        elevatorEncoder.setPosition(0.0);
    }

    public double getEncoderValue()
    {
        return elevatorEncoder.getPosition();
    }

    public void setManualControl(boolean state)
    {
        isManualControl = state;
    }

    public boolean isInManualControl()
    {
        return isManualControl;
    }

 

    public void setElevatorPower(double motorSpeed)
    {
        if (isManualControl == true)
            elevatorMotor.set(motorSpeed);
    }
    public void setRotatePower(double motorSpeed){
        rotateMotor.set(motorSpeed);
    }
    // Calculate motor speed as a function of the desired set point.  
    public void goToSetPoint()
    {
        double motorSpeed = getMotorSpeed(getEncoderValue());
        elevatorMotor.set(motorSpeed);
    }

    public boolean atSetPoint()
    {
        if (Math.abs(getEncoderValue() - currentSetPoint) > Constants.ELEVATOR_ENCODER_TOLERANCE)
            return false;
        else   
            return true;
    }

    // Simple setter to adjust setpoint
    public void setSetPoint(double newSetPoint)
    {
        currentSetPoint = newSetPoint;
    }

    // Go to designated position by setting the set point to the index of the
    // array value.
    public void goToPosition(int positionIndex)
    {
        // int positionIndex = 2;
        // If position in range of array, process.  Otherwise, ignore
        // Sets manual control to false to allow command to run
        setManualControl(false);
        if (positionIndex >= 0 && positionIndex < Constants.ELEVATOR_POSITIONS.length)
        {
            setSetPoint(Constants.ELEVATOR_POSITIONS[positionIndex]);
        }
    }

    // This method receives a position and returns a speed.
    // It is a simplified control processing routine that only
    // uses 'P' in PID.
    private double getMotorSpeed(double encoderPosition)
    {
        double error = currentSetPoint - encoderPosition;
        double output = Constants.ELEVATOR_P * error;
        double final_output = Math.min(Math.max(output, 
                              Constants.MIN_ELEV_MOTOR_OUTPUT), Constants.MAX_ELEV_MOTOR_OUTPUT);

        return final_output;
    }
}