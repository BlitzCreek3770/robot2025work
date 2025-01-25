// FRC Team 3770 - BlitzCreek - OLLE 20
// VisionSensor subsystem
// Manaages LimeLight vision interface with network table(s)

package frc.robot.subsystems;
//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSensor extends SubsystemBase 
{ 
	NetworkTable table;
	NetworkTableInstance tableData;
	NetworkTableEntry tx,ty,ta;
	boolean targetConditioningCheck;

	// ----------------------------------------------------------------------------
	// Constructor
	public VisionSensor()
	{
		super();
		tableData = NetworkTableInstance.getDefault();
        table = tableData.getTable("limelight-bcreek");
		//post to smart dashboard periodically
	}

	/*
	public Command TargetLightConditioning(){
		if (isTargetInView())
		targetConditioningCheck = true;
		else{
	    targetConditioningCheck = false;
		}
		if (targetConditioningCheck){
			return LEDon();
		}
		else
	}
	*/

	// ----------------------------------------------------------------------------
	// Returns true .........................
	public boolean isTargetInView()  {
		ta = table.getEntry("ta");
		double a = ta.getDouble(0.0);
		if (a > 0){
			return true;
		}
		else{
			return false;
		}
	}

    // ----------------------------------------------------------------------------
	// Green LEDs do turn on.  However, threshold and color changes for pipeline reset.
	public void LEDon()  {
		table.getEntry("ledMode").setNumber(0);
	}
	

	public void LEDoff()
	{
		table.getEntry("ledMode").setNumber(1);
	}
	
	/*public void runVision(){
		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		NetworkTableEntry tx = table.getEntry("tx");
		NetworkTableEntry ty = table.getEntry("ty");
		NetworkTableEntry ta = table.getEntry("ta");

		//read values periodically
		double x = tx.getDouble(0.0);
		double y = ty.getDouble(0.0);
		double area = ta.getDouble(0.0);

		//post to smart dashboard periodically
		SmartDashboard.putNumber("LimelightX", x);
		SmartDashboard.putNumber("LimelightY", y);
		SmartDashboard.putNumber("LimelightArea", area);
	}
*/
	// ----------------------------------------------------------------------------
	// Methord to retrieve the primary raw observations from the LimeLight and
	// network table (x-coordinate: tx; y-coordinate: ty; target area: ta).
	public double getVisionX()  {
		tx = table.getEntry("tx");
		double x;
		x = tx.getDouble(0);
		System.out.println(x);
		return x;
	}
	public double getVisionY()  {
		ty = table.getEntry("ty");
		double y;
		y = ty.getDouble(0);
		return y;
	}
	public double getVisionArea()  {
		ta = table.getEntry("ta");
		double area;
		area = ta.getDouble(0);
		return area;
	}

}