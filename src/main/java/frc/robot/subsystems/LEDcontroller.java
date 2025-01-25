// Delta College/Team 3770 Robotics Programming
// LEDcontroller Subsystem
// Manages the LED strip(s), on/off, colors, etc.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDcontroller extends SubsystemBase 
{
    private AddressableLED led;
    private AddressableLEDBuffer  ledBuffer;
    
	private final int LED_PWM_PORT       = 2;
	private final int NUMBER_LEDs        = 60;

    // ----------------------------------------------------------------------------
    // Constructor - Instantiate main LED object and associate with the
    // required LED buffer.
	public LEDcontroller() 
	{
        led = new AddressableLED(LED_PWM_PORT);  // Set PWM port

       // Set up buffer
       ledBuffer = new AddressableLEDBuffer(NUMBER_LEDs); 
       led.setLength(ledBuffer.getLength());  
        

       led.setData(ledBuffer);
       led.start();
	}

    // Walk through LEDs individually and set to intended R-G-B color
	private void setLEDsToRGB(int r, int g, int b)
	{
		for (var i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, r, g, b);
        }
        led.setData(ledBuffer);
        led.start();
   
	}

    public void setLEDsOFF()
    {
        setLEDsToRGB(0,0,0);
    }

    public void setLEDred()
    {
        setLEDsToRGB(255,0,0);
    }

    public void setLEDblue()
    {
        setLEDsToRGB(0,0,255);
    }

    public void setLEDcustom(int r, int g, int b){
        setLEDsToRGB(r, g, b);
    }
}