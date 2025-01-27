package frc.robot.commands.swervedrive.auto;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoTesting extends SequentialCommandGroup
{
  

  public AutoTesting(SwerveSubsystem swerve)
  {
    addCommands(new AutonDriveXnew(swerve,1.5),
    new AutonDriveXnew(swerve,0.5)
        //new AutonDriveLateral(swerve, 1, 1.7)      
    ); // End of addCommands
  }
}