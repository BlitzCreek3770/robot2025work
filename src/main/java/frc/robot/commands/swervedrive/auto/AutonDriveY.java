// BlitzCreek 3770 
// Auton routine to move (only in y-direction) to or away from a target distance
// (in meters).  Target-y is passed in.  Left is positive y and right is negative y.
// Orientation is field-relative.

package frc.robot.commands.swervedrive.auto;


import edu.wpi.first.wpilibj2.command.Command;

//import frc.robot.Constants;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Translation2d;

public class AutonDriveY extends Command
{
  private final SwerveSubsystem swerveDriveSystem;

  private double targetY;
  private boolean backingUp;
  
  // ----------------------------------------------------------------------------
  // Constructor: Accept drive system and target y-ccord
  public AutonDriveY(SwerveSubsystem swerve, double target)
  {
    swerveDriveSystem  = swerve;
    targetY = target;

    addRequirements(swerveDriveSystem);
  }

  // ----------------------------------------------------------------------------
  // Based on relationship of current x-coord with target y-coord, determine if
  // command will be moving positively forward or negatively in reverse.
  @Override
  public void initialize() 
  { 
    if (swerveDriveSystem.getPose().getY() > targetY)
      backingUp = true;
    else
      backingUp = false;   
  }
  
  // ----------------------------------------------------------------------------
  // Drive left (pos) or right (neg) depending on setting of backingUp variable.
  @Override
  public void execute()
  {
    double yVector = Constants.AUTON_MOVE_SPEED;

    if (backingUp)
      yVector = yVector * -1.0;

    swerveDriveSystem.drive(new Translation2d(0.0,yVector), 0.0, true);
  }

  // ----------------------------------------------------------------------------
  // Return true when target x-coord is reached - either by advancing or backinp up
  @Override
  public boolean isFinished() 
  {
    boolean returnValue = false;
    if ( backingUp && (swerveDriveSystem.getPose().getY() < targetY ))
      returnValue = true;
    if ( !backingUp && (swerveDriveSystem.getPose().getY() > targetY ))
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