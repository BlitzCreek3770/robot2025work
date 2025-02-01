package frc.robot.commands.swervedrive.auto;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoTesting extends SequentialCommandGroup
{
  

  public AutoTesting(SwerveSubsystem swerve)
  {
    addCommands(//new AutonDriveYnew(swerve,1)
    new AutonDriveLateral(swerve, 0.4, 0.2)
    ); // End of addCommands
  }
}