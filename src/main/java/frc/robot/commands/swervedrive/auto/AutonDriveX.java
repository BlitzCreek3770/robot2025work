
// BlitzCreek 3770 - OLLE 2024
// Auton routine to move (only in x-direction) to or away from a target distance
// (in meters).  Target-x is passed in.  Robot will move forward or in reverse
// based on initial relationship to target.

package frc.robot.commands.swervedrive.auto;


import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants;

import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import edu.wpi.first.math.geometry.Translation2d;

public class AutonDriveX extends Command
{
  private final SwerveSubsystem swerveDriveSystem;

  private double targetX;
  private boolean backingUp;
  
  // ----------------------------------------------------------------------------
  // Constructor: Accept drive system and target x-ccord
  public AutonDriveX(SwerveSubsystem swerve, double target)
  {
    swerveDriveSystem  = swerve;
    targetX = target;

    addRequirements(swerveDriveSystem);
  }

  // ----------------------------------------------------------------------------
  // Based on relationship of current x-coord with target x-coord, determine if
  // command will be moving positively forward or negatively in reverse.
  @Override
  public void initialize() 
  { 
    if (swerveDriveSystem.getPose().getX() > targetX)
      backingUp = true;
    else
      backingUp = false;   
  }
  
  // ----------------------------------------------------------------------------
  // Drive forward or reverse depending on setting of backingUp variable.
  @Override
  public void execute()
  {
    double xVector = Constants.AUTON_MOVE_SPEED;

    if (backingUp)
      xVector = xVector * -1.0;

    swerveDriveSystem.drive(new Translation2d(xVector, 0.0), 0.0, true);
  }

  // ----------------------------------------------------------------------------
  // Return true when target x-coord is reached - either by advancing or backinp up
  @Override
  public boolean isFinished() 
  {
   


    boolean returnValue = false;
    if ( backingUp && (swerveDriveSystem.getPose().getX() < targetX ))
      returnValue = true;
    if ( !backingUp && (swerveDriveSystem.getPose().getX() > targetX ))
      returnValue = true;

   // System.out.println(swerveDriveSystem.getPose().getX() + "|" + targetX+ "|" + returnValue);

    return returnValue;
  }

  // ----------------------------------------------------------------------------
  // Command shut-down actions
  @Override
  public void end(boolean interrupted) 
  {
    swerveDriveSystem.drive(new Translation2d(0.0, 0.0), 0.0, true);
  }

}