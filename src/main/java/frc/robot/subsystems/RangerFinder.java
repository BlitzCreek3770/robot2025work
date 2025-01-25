// Delta College/Team 3770 Robotics Programming
// RangeFinder Subsystem
// Manages the CTRE CANrange rangefinder unit.

package frc.robot.subsystems;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANrange;

//import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.util.sendable.SendableBuilder;

public class RangerFinder extends SubsystemBase {

    private CANrange rangeFinder;
    SendableBuilder builder;

    // ----------------------------------------------------------------------------
    // Constructor - Instantiate unit
    public RangerFinder()
    {
        super();
        rangeFinder = new CANrange(Constants.CANrange_PORT);
    }

    // ----------------------------------------------------------------------------
    // Return range to surface in meters
    public double getRange()
    {
        System.out.println(rangeFinder.getDistance().getValue().baseUnitMagnitude());
        return rangeFinder.getDistance().getValue().baseUnitMagnitude();
    }

}
