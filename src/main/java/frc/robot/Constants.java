// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
  public static final double MAX_SPEED  = Units.feetToMeters(14.5);
  // Maximum speed of the robot in meters per second, used to limit acceleration.

  public static final double AUTON_MOVE_SPEED = 1;

  public static final class DrivebaseConstants
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double DEADBAND        = 0.1;
    public static final double LEFT_Y_DEADBAND = 0.1;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final double TURN_CONSTANT    = 6;

  }
  
  public static final int CANrange_CAN_ID = 47;
  public static final int ELEVATOR_SPARK_FLEX_CAN_ID = 14;
  public static final int SLAPPER_CAN_ID = 15;
  public static final int INTAKE_ROTATE_CAN_ID = 16;
  public static final int INTAKE_POWER_CAN_ID = 17;
  public static final int STOP_SENSOR_DIO_PORT = 0;
  public static final int ROTATE_SENSOR_TOP_DIO_PORT = 0;
  public static final int ROTATE_SENSOR_BOTTOM_DIO_PORT = 0;
  public static final double INTAKE_POWER = .1;

  public static final int PUSHER_OUT_CAN_ID = 26;
  public static final int PUSHER_POWER_CAN_ID = 27;
  public static final double PUSHER_MOTOR_SPEED = .1;
  public static final double PUSHER_EXTEND_POWER = .1;
  public static final int PUSHER_DOWN_SENSOR_DIO_PORT = 0;
  public static final int PUSHER_UP_SENSOR_DIO_PORT = 0;

  public static final int ELEVATOR_ENCODER_TOLERANCE = 10000;
  public static final double ELEVATOR_P = .1;
  public static final double MIN_ELEV_MOTOR_OUTPUT = -1;
  public static final double MAX_ELEV_MOTOR_OUTPUT = 1;
  public static final double[] ELEVATOR_POSITIONS = {
    0,
    100,
    200,
    300,
    400
  };
}
