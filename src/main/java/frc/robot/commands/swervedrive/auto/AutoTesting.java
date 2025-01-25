package frc.robot.commands.swervedrive.auto;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoTesting extends SequentialCommandGroup
{
  

  public AutoTesting(SwerveSubsystem swerve)
  {
    addCommands(
      new AutonDriveY(swerve,0),
      new AutonDriveX(swerve,3)
      
    ); // End of addCommands
  }
}