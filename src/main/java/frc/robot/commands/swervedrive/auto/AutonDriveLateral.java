
// BlitzCreek 3770 - OLLE 2024
// Auton routine to move (only in x-direction) to or away from a target distance
// (in meters).  Target-x is passed in.  Robot will move forward or in reverse
// based on initial relationship to target.

package frc.robot.commands.swervedrive.auto;


import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants;

import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;

public class AutonDriveLateral extends Command
{
  private final SwerveSubsystem swerveDriveSystem;

  private double targetX, targetY;
  PIDController pidControlX, pidControlY;
  double xVector, yVector;

  double P_VALUE = 3.0;
  
  // ----------------------------------------------------------------------------
  // Constructor: Accept drive system and target x-ccord
  public AutonDriveLateral(SwerveSubsystem swerve, double x, double y)
  {
    swerveDriveSystem  = swerve;

    targetX = x;
    targetY = y;

    pidControlX = new PIDController(P_VALUE,0,0);
    pidControlY = new PIDController(P_VALUE, 0, 0);

    addRequirements(swerveDriveSystem);
  }

  // ----------------------------------------------------------------------------
  // Based on relationship of current x-coord with target x-coord, determine if
  // command will be moving positively forward or negatively in reverse.
  @Override
  public void initialize() 
  { 

    pidControlX.setSetpoint(targetX);
    pidControlY.setSetpoint(targetY);

    System.out.println("==>"+targetX + "|"+ targetY);

  }
  
  // ----------------------------------------------------------------------------
  // Drive forward or reverse depending on setting of backingUp variable.
  @Override
  public void execute()
  {
    xVector = pidControlX.calculate(swerveDriveSystem.getPose().getX());
    yVector = pidControlY.calculate(swerveDriveSystem.getPose().getY());
    swerveDriveSystem.drive(new Translation2d(xVector, yVector), 0.0, true);

    System.out.println(xVector +"|"+ swerveDriveSystem.getPose().getX() + "|"+ targetX);
    System.out.println(yVector +"|"+ swerveDriveSystem.getPose().getY() + "|"+ targetY);
    System.out.println();

  }

  // ----------------------------------------------------------------------------
  // Return true when target x-coord is reached - either by advancing or backinp up
  @Override
  public boolean isFinished() 
  {

    System.out.println("IN ISFINISHED");

    if (pidControlX.atSetpoint() && pidControlY.atSetpoint())
    {
        System.out.println("ENDING");
        return true;
    }
    else
        return false;
    /* 
    boolean returnValueX = false;
    boolean returnValueY = false;
    if (pidControlX.atSetpoint())  {
        swerveDriveSystem.drive(new Translation2d(xVector, yVector), 0.0, true);
        returnValueX = true;
    }
    if (pidControlY.atSetpoint())  {
        swerveDriveSystem.drive(new Translation2d(xVector, yVector), 0.0, true);
        returnValueY = true;
      }
    if (returnValueX == returnValueY && returnValueX != false)
        return true;
    else
        return false;
        */
  }

  // ----------------------------------------------------------------------------
  // Command shut-down actions
  @Override
  public void end(boolean interrupted) 
  {
    swerveDriveSystem.drive(new Translation2d(0.0, 0.0), 0.0, true);
  }

}