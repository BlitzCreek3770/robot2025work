package frc.robot.commands;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class SpitOut extends Command {
    Elevator elevator;
    boolean shooterEndAdjust = false;

    public SpitOut(Elevator e){
        elevator = e;
        addRequirements(elevator);
    }
    
    public void initialize(){

    }

    public void execute(){
        elevator.setIntakePower(-Constants.INTAKE_POWER);
    }

    public boolean isFinished(){
        return !elevator.getIntakeOptical();
    }

    public void end (boolean interrupted){
       elevator.setIntakePower(0);
    }

}
