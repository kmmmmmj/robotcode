package kr.robomation.peripheral;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public final class SmartPen
{
	public static final int CODE = 0x0206;
	public static final String ID = "kr.robomation.peripheral.smartpen";
	
	public static final int SIGNAL_STRENGTH = 0x00600000;
	public static final int BATTERY = 0x00600001;
	public static final int OID = 0x00600002;
	public static final int BUTTON = 0x00600003;
	
	private SmartPen()
	{
	}
}
