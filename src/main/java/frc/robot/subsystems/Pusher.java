// FRC 3770 2025 Pusher System
// Contains two motors, one to extend out and one to power wheels
// Two digital input optical sensors, one at top and one at bottom

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

public class Pusher extends SubsystemBase {


    private SparkMax extendMotor;
    private SparkMax powerMotor;
    private DigitalInput upSensor;
    private DigitalInput downSensor;

    public Pusher()
    {
        extendMotor = new SparkMax(Constants.PUSHER_OUT_CAN_ID, MotorType.kBrushed);
        powerMotor = new SparkMax(Constants.PUSHER_POWER_CAN_ID, MotorType.kBrushless);
        upSensor = new DigitalInput(Constants.PUSHER_UP_SENSOR_DIO_PORT);
        downSensor = new DigitalInput(Constants.PUSHER_DOWN_SENSOR_DIO_PORT);
    }

    public void extendOut() {
        powerMotor.set(Constants.PUSHER_MOTOR_SPEED);
        if (downSensor.get() == false)
            extendMotor.set(Constants.PUSHER_EXTEND_POWER);
        else   
            extendMotor.set(0);
    }

    public void extendIn(){
        powerMotor.set(0);
        if (upSensor.get() == false)
            extendMotor.set(-Constants.PUSHER_EXTEND_POWER);
        else
            extendMotor.set(0);
    }
}