// FRC 3770 2025 Elevator Subsystem
// Driven by one Neo Vortex with Spark Flex and one Neo Vortex with Spark Max
// Flex functions to lift and lower Elevator system, Max rotates an arm to knock algae off reef
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;


import frc.robot.Constants;


public class Elevator extends SubsystemBase {
    
    private SparkFlex elevatorMotor;
    private RelativeEncoder elevatorEncoder;

    private SparkFlex rotateMotor;

    private SparkMax intakeRotate;
    private RelativeEncoder intakeRotateEncoder;
    private SparkMax intakePower;

    private DigitalInput stopSensor,rotateTopSensor,rotateBottomSensor;

    double currentSetPoint;

    boolean isManualControl = false;

    public Elevator()
    {
        super();

        elevatorMotor = new SparkFlex(Constants.ELEVATOR_SPARK_FLEX_CAN_ID, SparkLowLevel.MotorType.kBrushless);   
        elevatorEncoder = elevatorMotor.getEncoder();
        rotateMotor = new SparkFlex(Constants.SLAPPER_CAN_ID, SparkLowLevel.MotorType.kBrushless);
        intakeRotate = new SparkMax(Constants.INTAKE_ROTATE_CAN_ID, SparkLowLevel.MotorType.kBrushless);
        intakeRotateEncoder = intakeRotate.getEncoder();
        intakePower = new SparkMax(Constants.INTAKE_POWER_CAN_ID, SparkLowLevel.MotorType.kBrushless); 
        stopSensor = new DigitalInput(Constants.STOP_SENSOR_DIO_PORT);
        rotateTopSensor = new DigitalInput(Constants.ROTATE_SENSOR_TOP_DIO_PORT);
        rotateBottomSensor = new DigitalInput(Constants.ROTATE_SENSOR_BOTTOM_DIO_PORT);

        // Initialize encoder to zero at start position.  Make this first set point.
        resetElevatorEncoder();
        currentSetPoint = 0.0;
    }

    public void spinElevatorForward(){
        elevatorMotor.set(1.0);
    }

    public void spinElevatorReverse(){
        elevatorMotor.set(-1.0);
    }

    public void stopElevator(){
        elevatorMotor.set(0.0);
    }

    public void setIntakePower(double power){
        intakePower.set(power);
    }

    public void setIntakeRotatePower(double power){
        intakeRotate.set(power);
    }

    public void resetElevatorEncoder(){
        elevatorEncoder.setPosition(0.0);
    }

    public double getElevatorEncoderValue(){
        return elevatorEncoder.getPosition();
    }

    public boolean getIntakeOptical(){
        return !stopSensor.get();
    }

    public boolean intakeRotateTopOptical(){
        return !rotateTopSensor.get();
    }

    public boolean intakeRotateBottomOptical(){
        return !rotateBottomSensor.get();
    }

    public double getIntakeEncoderValue(){
        return intakeRotateEncoder.getPosition();
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
        double motorSpeed = getElevatorMotorSpeed(getElevatorEncoderValue());
        elevatorMotor.set(motorSpeed);
    }

    public boolean atElevatorSetPoint()
    {
        if (Math.abs(getElevatorEncoderValue() - currentSetPoint) > Constants.ELEVATOR_ENCODER_TOLERANCE)
            return false;
        else   
            return true;
    }

    // Simple setter to adjust setpoint
    public void setElevatorSetPoint(double newSetPoint)
    {
        currentSetPoint = newSetPoint;
    }

    // Go to designated position by setting the set point to the index of the
    // array value.
    public void goToElevatorPosition(int positionIndex)
    {
        // If position in range of array, process.  Otherwise, ignore
        // Sets manual control to false to allow command to run
        setManualControl(false);
        if (positionIndex >= 0 && positionIndex < Constants.ELEVATOR_POSITIONS.length)
        {
            setElevatorSetPoint(Constants.ELEVATOR_POSITIONS[positionIndex]);
        }
    }

    // This method receives a position and returns a speed.
    // It is a simplified control processing routine that only
    // uses 'P' in PID.
    private double getElevatorMotorSpeed(double encoderPosition)
    {
        double error = currentSetPoint - encoderPosition;
        double output = Constants.ELEVATOR_P * error;
        double final_output = Math.min(Math.max(output, 
                              Constants.MIN_ELEV_MOTOR_OUTPUT), Constants.MAX_ELEV_MOTOR_OUTPUT);

        return final_output;
    }
}