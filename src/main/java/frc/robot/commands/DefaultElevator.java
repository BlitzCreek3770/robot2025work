// FRC 3770 2025 Default Elevator Command
// Runs in two modes. PID Mode using four button setpoints and manual mode using Joystick Input. 
// Second motor constantly runs using manual mode

package frc.robot.commands;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;

public class DefaultElevator extends Command
{
    Timer actionTimer;
    Elevator elevator;
    DoubleSupplier power, powerRotate;

    // ----------------------------------------------------------------------------
    public DefaultElevator(Elevator e, DoubleSupplier p, DoubleSupplier pR)
    {
        elevator = e;
        power = p;
        powerRotate = pR;
        addRequirements(elevator);
    }

    // ----------------------------------------------------------------------------
    public void initialize()
    {

    }

    // ----------------------------------------------------------------------------
    public void execute()
    {

        if (elevator.isInManualControl())  {
            elevator.setElevatorPower(power.getAsDouble());
            SmartDashboard.putNumber("Elevator Encoder", elevator.getElevatorEncoderValue());  
        }
        else  {
            elevator.goToSetPoint();
            SmartDashboard.putNumber("Elevator Encoder", elevator.getElevatorEncoderValue());  
        }
        elevator.setRotatePower(powerRotate.getAsDouble());
    }

    public boolean isFinished()
    {
        return false;
    }

}